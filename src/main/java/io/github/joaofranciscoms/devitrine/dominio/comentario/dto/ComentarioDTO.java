package io.github.joaofranciscoms.devitrine.dominio.comentario.dto;

import java.util.UUID;

public record ComentarioDTO(
        String conteudo, UUID idPost
) {
}
