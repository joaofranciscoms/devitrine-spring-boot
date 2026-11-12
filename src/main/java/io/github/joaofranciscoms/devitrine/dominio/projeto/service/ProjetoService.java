package io.github.joaofranciscoms.devitrine.dominio.projeto.service;

import io.github.joaofranciscoms.devitrine.common.exceptions.ProjetoNaoEncontradoException;
import io.github.joaofranciscoms.devitrine.dominio.projeto.dto.ProjetoDTO;
import io.github.joaofranciscoms.devitrine.dominio.projeto.dto.ProjetoDetalhesDTO;
import io.github.joaofranciscoms.devitrine.dominio.projeto.mapper.ProjetoDetalhesMapper;
import io.github.joaofranciscoms.devitrine.dominio.projeto.mapper.ProjetoMapper;
import io.github.joaofranciscoms.devitrine.dominio.projeto.model.Projeto;
import io.github.joaofranciscoms.devitrine.dominio.projeto.model.TipoProjeto;
import io.github.joaofranciscoms.devitrine.dominio.projeto.repository.ProjetoRepository;
import io.github.joaofranciscoms.devitrine.dominio.tecnologia.service.TecnologiaService;
import io.github.joaofranciscoms.devitrine.dominio.usuario.model.TipoUsuario;
import io.github.joaofranciscoms.devitrine.dominio.usuario.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjetoService {

    private final ProjetoRepository repository;
    private final TecnologiaService tecnologiaService;
    private final ProjetoMapper projetoMapper;
    private final ProjetoDetalhesMapper projetoDetalhesMapper;

    public ProjetoDetalhesDTO save(ProjetoDTO dto, Usuario autor) {
        TipoUsuario perfilPermitido = switch (dto.tipo()) {
            case PROJETO_PESSOAL -> TipoUsuario.DESENVOLVEDOR;
            case PROJETO_PESQUISA, ARTIGO_CIENTIFICO -> TipoUsuario.PESQUISADOR;
            case null -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Informe o tipo de publicação.");
        };
        if (autor.getTipo() != perfilPermitido) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Seu perfil não pode criar este tipo de publicação.");
        }

        Projeto entity = projetoMapper.toEntity(dto);
        entity.setAutor(autor);
        entity.setTecnologias(tecnologiaService.obterOuCriar(dto.tecnologias()));
        Projeto salvo = repository.save(entity);
        return projetoDetalhesMapper.toDTO(salvo);
    }

    public ProjetoDetalhesDTO findById(UUID id){
        return repository.findById(id).map(projetoDetalhesMapper::toDTO).orElseThrow(() -> new ProjetoNaoEncontradoException());
    }

    public Page<ProjetoDetalhesDTO> list(TipoProjeto tipo, String tecnologia, PageRequest pageRequest){
        boolean filtrarTecnologia = tecnologia != null && !tecnologia.isBlank();

        Page<Projeto> projetos;
        if(tipo != null && filtrarTecnologia){
            projetos = repository.findDistinctByTipoAndTecnologiasNomeIgnoreCase(tipo, tecnologia.trim(), pageRequest);
        } else if(tipo != null){
            projetos = repository.findByTipo(tipo, pageRequest);
        } else if(filtrarTecnologia){
            projetos = repository.findDistinctByTecnologiasNomeIgnoreCase(tecnologia.trim(), pageRequest);
        } else {
            projetos = repository.findAll(pageRequest);
        }

        return projetos.map(projetoDetalhesMapper::toDTO);
    }
}
