package io.github.joaofranciscoms.devitrine;

import io.github.joaofranciscoms.devitrine.dominio.candidatura.controller.CandidaturaController;
import io.github.joaofranciscoms.devitrine.dominio.candidatura.service.CandidaturaService;
import io.github.joaofranciscoms.devitrine.dominio.projeto.controller.ProjetoController;
import io.github.joaofranciscoms.devitrine.dominio.projeto.mapper.ProjetoDetalhesMapper;
import io.github.joaofranciscoms.devitrine.dominio.projeto.mapper.ProjetoMapper;
import io.github.joaofranciscoms.devitrine.dominio.projeto.model.Projeto;
import io.github.joaofranciscoms.devitrine.dominio.projeto.repository.ProjetoRepository;
import io.github.joaofranciscoms.devitrine.dominio.projeto.service.ProjetoService;
import io.github.joaofranciscoms.devitrine.dominio.tecnologia.service.TecnologiaService;
import io.github.joaofranciscoms.devitrine.dominio.usuario.model.TipoUsuario;
import io.github.joaofranciscoms.devitrine.dominio.usuario.model.Usuario;
import io.github.joaofranciscoms.devitrine.infra.security.AuthInterceptor;
import io.github.joaofranciscoms.devitrine.infra.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PermissoesPublicacaoTests {

    private final JwtService jwtService = mock(JwtService.class);
    private MockMvc mvc;

    @BeforeEach
    void configurarApi() {
        var projetoMapper = mock(ProjetoMapper.class);
        when(projetoMapper.toEntity(any())).thenReturn(new Projeto());
        var projetoService = new ProjetoService(mock(ProjetoRepository.class), mock(TecnologiaService.class),
                projetoMapper, mock(ProjetoDetalhesMapper.class));

        var interceptor = new AuthInterceptor();
        ReflectionTestUtils.setField(interceptor, "jwtService", jwtService);
        when(jwtService.isTokenValido("teste")).thenReturn(true);

        mvc = MockMvcBuilders.standaloneSetup(new ProjetoController(projetoService),
                        new CandidaturaController(mock(CandidaturaService.class)))
                .addInterceptors(interceptor)
                .build();
    }

    @ParameterizedTest
    @CsvSource({
            "EMPRESA, PROJETO_PESSOAL, 403",
            "PESQUISADOR, PROJETO_PESSOAL, 403",
            "DESENVOLVEDOR, PROJETO_PESSOAL, 201",
            "EMPRESA, PROJETO_PESQUISA, 403",
            "PESQUISADOR, PROJETO_PESQUISA, 201",
            "DESENVOLVEDOR, PROJETO_PESQUISA, 403",
            "EMPRESA, ARTIGO_CIENTIFICO, 403",
            "PESQUISADOR, ARTIGO_CIENTIFICO, 201",
            "DESENVOLVEDOR, ARTIGO_CIENTIFICO, 403"
    })
    void aplicaPermissaoPorTipoDeProjeto(TipoUsuario perfil, String tipoProjeto, int statusEsperado) throws Exception {
        autenticar(perfil);

        mvc.perform(post("/api/projetos")
                        .header("Authorization", "Bearer teste")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"titulo":"Projeto", "descricao":"Descrição", "tipo":"%s", "tecnologias":[]}
                                """.formatted(tipoProjeto)))
                .andExpect(status().is(statusEsperado));
    }

    @ParameterizedTest
    @CsvSource({"EMPRESA, 403", "PESQUISADOR, 403", "DESENVOLVEDOR, 201"})
    void aplicaPermissaoDeCandidatura(TipoUsuario perfil, int statusEsperado) throws Exception {
        autenticar(perfil);

        mvc.perform(post("/api/candidaturas")
                        .header("Authorization", "Bearer teste")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"vagaId":"f001f3d2-3e65-42ba-a177-ab053fb9d5b9"}
                                """))
                .andExpect(status().is(statusEsperado));
    }

    private void autenticar(TipoUsuario perfil) {
        var usuario = new Usuario();
        usuario.setTipo(perfil);
        when(jwtService.getUsuario("teste")).thenReturn(usuario);
    }
}
