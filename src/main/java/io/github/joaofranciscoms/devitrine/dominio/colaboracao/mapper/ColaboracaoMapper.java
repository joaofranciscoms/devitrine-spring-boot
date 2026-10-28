package io.github.joaofranciscoms.devitrine.dominio.colaboracao.mapper;

import io.github.joaofranciscoms.devitrine.dominio.colaboracao.dto.ColaboracaoDTO;
import io.github.joaofranciscoms.devitrine.dominio.colaboracao.model.Colaboracao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ColaboracaoMapper {

    @Mapping(target = "ideia", ignore = true)
    Colaboracao toEntity(ColaboracaoDTO colaboracaoDTO);
}
