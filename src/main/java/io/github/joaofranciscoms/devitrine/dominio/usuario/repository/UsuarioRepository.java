package io.github.joaofranciscoms.devitrine.dominio.usuario.repository;

import io.github.joaofranciscoms.devitrine.dominio.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByLogin(String login);
    boolean existsByLogin(String login);
}
