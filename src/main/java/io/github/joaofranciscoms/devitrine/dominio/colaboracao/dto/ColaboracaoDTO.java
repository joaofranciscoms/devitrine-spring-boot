package io.github.joaofranciscoms.devitrine.dominio.colaboracao.dto;

import java.util.UUID;

public record ColaboracaoDTO(
        String conteudo, UUID idIdeia
) {
}
