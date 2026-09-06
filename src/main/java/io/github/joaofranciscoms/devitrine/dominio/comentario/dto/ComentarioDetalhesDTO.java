package io.github.joaofranciscoms.devitrine.dominio.comentario.dto;

import java.time.LocalDateTime;

public record ComentarioDetalhesDTO(
        String id,
        String conteudo,
        LocalDateTime dataPostagem,
        LocalDateTime dataAtualizacao
) {
}
