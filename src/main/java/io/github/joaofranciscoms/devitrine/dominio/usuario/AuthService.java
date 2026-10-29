package io.github.joaofranciscoms.devitrine.dominio.usuario;

import io.github.joaofranciscoms.devitrine.common.exceptions.UnauthorizedException;
import io.github.joaofranciscoms.devitrine.common.exceptions.ValidationException;
import io.github.joaofranciscoms.devitrine.common.validation.CampoInvalido;
import io.github.joaofranciscoms.devitrine.dominio.usuario.dto.AuthResponse;
import io.github.joaofranciscoms.devitrine.dominio.usuario.dto.LoginForm;
import io.github.joaofranciscoms.devitrine.dominio.usuario.dto.UsuarioCadastroForm;
import io.github.joaofranciscoms.devitrine.dominio.usuario.model.Usuario;
import io.github.joaofranciscoms.devitrine.dominio.usuario.repository.UsuarioRepository;
import io.github.joaofranciscoms.devitrine.infra.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private JwtService jwtService;

    public AuthResponse cadastrar(UsuarioCadastroForm form){

        if(usuarioRepository.existsByLogin(form.login())){
            throw new ValidationException(List.of(new CampoInvalido("login", "Login já cadastrado.")));
        }

        String senhaCriptografada = encoder.encode(form.senha());

        Usuario usuario = new Usuario();
        usuario.setTipo(form.tipo());
        usuario.setNome(form.nome());
        usuario.setLogin(form.login());
        usuario.setSenha(senhaCriptografada);

        usuarioRepository.save(usuario);

        return toAuthResponse(usuario);
    }

    public AuthResponse autenticar(LoginForm form){
        final String mensagem = "Login ou senha inválidos.";
        Usuario usuario = usuarioRepository.findByLogin(form.login())
                .orElseThrow(() -> new UnauthorizedException(mensagem));

        boolean senhasBatem = encoder.matches(form.senha(), usuario.getSenha());

        if(!senhasBatem){
            throw new UnauthorizedException(mensagem);
        }

        return toAuthResponse(usuario);
    }

    private AuthResponse toAuthResponse(Usuario usuario){
        var token = jwtService.gerarToken(usuario);
        var expiraEm = jwtService.getExpirationSeconds();

        return new AuthResponse(
                token,
                "Bearer",
                expiraEm,
                usuario.getNome()
        );
    }
}
