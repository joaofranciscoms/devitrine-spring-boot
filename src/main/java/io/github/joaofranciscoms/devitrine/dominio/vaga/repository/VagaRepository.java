package io.github.joaofranciscoms.devitrine.dominio.vaga.repository;

import io.github.joaofranciscoms.devitrine.dominio.vaga.model.Vaga;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VagaRepository extends JpaRepository<Vaga, UUID> {

    @Override
    @EntityGraph(attributePaths = "autor")
    List<Vaga> findAll();
}
