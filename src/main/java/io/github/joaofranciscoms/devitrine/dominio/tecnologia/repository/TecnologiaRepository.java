package io.github.joaofranciscoms.devitrine.dominio.tecnologia.repository;

import io.github.joaofranciscoms.devitrine.dominio.tecnologia.model.Tecnologia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TecnologiaRepository extends JpaRepository<Tecnologia, UUID> {
    Optional<Tecnologia> findByNomeIgnoreCase(String nome);
    List<Tecnologia> findAllByOrderByNomeAsc();
}
