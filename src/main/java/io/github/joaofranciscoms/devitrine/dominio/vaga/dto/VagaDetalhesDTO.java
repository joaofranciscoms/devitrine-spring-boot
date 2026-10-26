package io.github.joaofranciscoms.devitrine.dominio.vaga.dto;

import java.time.LocalDateTime;

public record VagaDetalhesDTO(
        String id,
        String titulo,
        String conteudo,
        String requisitos,
        LocalDateTime dataPostagem,
        LocalDateTime dataAtualizacao,
        String autorNome) {
}
