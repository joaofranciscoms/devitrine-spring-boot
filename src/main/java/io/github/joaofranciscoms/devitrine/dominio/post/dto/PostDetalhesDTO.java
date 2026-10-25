package io.github.joaofranciscoms.devitrine.dominio.post.dto;

import java.time.LocalDateTime;

public record PostDetalhesDTO(
        String id,
        String titulo,
        String conteudo,
        LocalDateTime dataPostagem,
        LocalDateTime dataAtualizacao,
        String autorNome
) {
}
