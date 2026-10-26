package io.github.joaofranciscoms.devitrine.dominio.candidatura.dto;

import java.time.LocalDateTime;

public record CandidaturaDetalhesDTO(
        String id,
        String mensagem,
        String conteudo,
        LocalDateTime dataPostagem,
        String nomeCandidato,
        String nomeEmpresa) {
}
