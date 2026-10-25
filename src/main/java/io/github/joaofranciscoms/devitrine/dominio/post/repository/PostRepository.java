package io.github.joaofranciscoms.devitrine.dominio.post.repository;

import io.github.joaofranciscoms.devitrine.dominio.post.model.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    @Override
    @EntityGraph(attributePaths = "autor")
    List<Post> findAll();
}
