package io.github.joaofranciscoms.devitrine.dominio.post.repository;

import io.github.joaofranciscoms.devitrine.dominio.post.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<PostEntity, UUID> {
}
