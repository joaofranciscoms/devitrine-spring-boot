package io.github.joaofranciscoms.devitrine.dominio.projeto.repository;

import io.github.joaofranciscoms.devitrine.dominio.projeto.model.Projeto;
import io.github.joaofranciscoms.devitrine.dominio.projeto.model.TipoProjeto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjetoRepository extends JpaRepository<Projeto, UUID> {

    Page<Projeto> findByTipo(TipoProjeto tipo, Pageable pageable);

    Page<Projeto> findDistinctByTecnologiasNomeIgnoreCase(String tecnologia, Pageable pageable);

    Page<Projeto> findDistinctByTipoAndTecnologiasNomeIgnoreCase(TipoProjeto tipo, String tecnologia, Pageable pageable);
}
