package io.github.joaofranciscoms.devitrine.dominio.comentario.repository;

import io.github.joaofranciscoms.devitrine.dominio.comentario.model.ComentarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ComentarioRepository extends JpaRepository<ComentarioEntity, UUID> {
}
