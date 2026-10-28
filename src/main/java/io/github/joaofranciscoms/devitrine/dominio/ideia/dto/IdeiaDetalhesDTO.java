package io.github.joaofranciscoms.devitrine.dominio.ideia.dto;

import java.time.LocalDateTime;

public record IdeiaDetalhesDTO(
        String id,
        String titulo,
        String descricao,
        LocalDateTime dataPostagem,
        LocalDateTime dataAtualizacao,
        String autorNome
) {
}
