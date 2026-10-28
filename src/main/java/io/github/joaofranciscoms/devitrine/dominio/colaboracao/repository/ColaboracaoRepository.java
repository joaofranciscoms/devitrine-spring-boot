package io.github.joaofranciscoms.devitrine.dominio.colaboracao.repository;

import io.github.joaofranciscoms.devitrine.dominio.colaboracao.model.Colaboracao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ColaboracaoRepository extends JpaRepository<Colaboracao, UUID> {
    Page<Colaboracao> findByIdeiaId(UUID ideiaId, Pageable pageable);
}
