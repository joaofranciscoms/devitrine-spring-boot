package io.github.joaofranciscoms.devitrine.infra.security;

import io.github.joaofranciscoms.devitrine.dominio.usuario.model.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        if("OPTIONS".equalsIgnoreCase(request.getMethod())){
            return true;
        }

        String authorization = request.getHeader("Authorization");
        if(authorization == null || !authorization.startsWith("Bearer ")){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        String token = authorization.substring(7);
        if(!jwtService.isTokenValido(token)){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        Usuario usuario = jwtService.getUsuario(token);

        if (handler instanceof HandlerMethod metodo) {
            ApenasTipo restricao = metodo.getMethodAnnotation(ApenasTipo.class);
            if (restricao != null && !List.of(restricao.value()).contains(usuario.getTipo())) {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                return false;
            }
        }

        request.setAttribute("usuarioAutenticado", usuario);
        return true;
    }
}
