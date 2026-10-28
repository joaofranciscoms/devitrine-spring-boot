package io.github.joaofranciscoms.devitrine.dominio.projeto.dto;

import io.github.joaofranciscoms.devitrine.dominio.projeto.model.TipoProjeto;

import java.util.List;

public record ProjetoDTO(String titulo, String descricao, TipoProjeto tipo, String link, List<String> tecnologias) {
}
