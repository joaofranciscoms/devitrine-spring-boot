package io.github.joaofranciscoms.devitrine.dominio.ideia.repository;

import io.github.joaofranciscoms.devitrine.dominio.ideia.model.Ideia;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IdeiaRepository extends JpaRepository<Ideia, UUID> {

    @Override
    @EntityGraph(attributePaths = "autor")
    List<Ideia> findAll();
}
