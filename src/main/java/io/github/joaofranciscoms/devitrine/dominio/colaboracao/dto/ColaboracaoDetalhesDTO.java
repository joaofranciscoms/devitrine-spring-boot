package io.github.joaofranciscoms.devitrine.dominio.colaboracao.dto;

import java.time.LocalDateTime;

public record ColaboracaoDetalhesDTO(
        String id,
        String conteudo,
        LocalDateTime dataPostagem,
        LocalDateTime dataAtualizacao,
        String autorNome
) {
}
