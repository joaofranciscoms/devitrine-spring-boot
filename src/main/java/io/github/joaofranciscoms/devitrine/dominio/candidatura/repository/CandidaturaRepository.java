package io.github.joaofranciscoms.devitrine.dominio.candidatura.repository;

import io.github.joaofranciscoms.devitrine.dominio.candidatura.model.Candidatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CandidaturaRepository extends JpaRepository<Candidatura, UUID> {
}
