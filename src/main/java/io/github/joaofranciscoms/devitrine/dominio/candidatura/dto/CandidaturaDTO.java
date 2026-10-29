package io.github.joaofranciscoms.devitrine.dominio.candidatura.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CandidaturaDTO(@NotNull(message = "Informe a vaga") UUID vagaId) {
}
