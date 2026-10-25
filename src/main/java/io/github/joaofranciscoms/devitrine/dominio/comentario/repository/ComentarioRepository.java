package io.github.joaofranciscoms.devitrine.dominio.comentario.repository;

import io.github.joaofranciscoms.devitrine.dominio.comentario.model.Comentario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ComentarioRepository extends JpaRepository<Comentario, UUID> {
    Page<Comentario> findByPostId(UUID postId, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = "autor")
    List<Comentario> findAll();
}
