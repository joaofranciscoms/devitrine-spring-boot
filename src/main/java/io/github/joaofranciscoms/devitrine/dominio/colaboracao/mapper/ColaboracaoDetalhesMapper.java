package io.github.joaofranciscoms.devitrine.dominio.colaboracao.mapper;

import io.github.joaofranciscoms.devitrine.dominio.colaboracao.dto.ColaboracaoDetalhesDTO;
import io.github.joaofranciscoms.devitrine.dominio.colaboracao.model.Colaboracao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ColaboracaoDetalhesMapper {

    @Mapping(source = "autor.nome", target = "autorNome")
    ColaboracaoDetalhesDTO toDTO(Colaboracao colaboracao);
}
