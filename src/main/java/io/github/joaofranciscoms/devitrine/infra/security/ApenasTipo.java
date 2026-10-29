package io.github.joaofranciscoms.devitrine.infra.security;

import io.github.joaofranciscoms.devitrine.dominio.usuario.model.TipoUsuario;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApenasTipo {
    TipoUsuario[] value();
}
