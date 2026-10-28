package io.github.joaofranciscoms.devitrine.dominio.projeto.dto;

import io.github.joaofranciscoms.devitrine.dominio.projeto.model.TipoProjeto;

import java.time.LocalDateTime;
import java.util.List;

public record ProjetoDetalhesDTO(
        String id,
        String titulo,
        String descricao,
        TipoProjeto tipo,
        String link,
        List<String> tecnologias,
        LocalDateTime dataPostagem,
        LocalDateTime dataAtualizacao,
        String autorNome
) {
}
