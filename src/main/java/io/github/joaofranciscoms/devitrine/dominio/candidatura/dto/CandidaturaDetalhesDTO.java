package io.github.joaofranciscoms.devitrine.dominio.candidatura.dto;

import java.time.LocalDateTime;

public record CandidaturaDetalhesDTO(
        String id,
        LocalDateTime dataPostagem,
        String nomeCandidato,
        String nomeEmpresa) {
}
