package io.github.joaofranciscoms.devitrine.dominio.projeto.mapper;

import io.github.joaofranciscoms.devitrine.dominio.projeto.dto.ProjetoDTO;
import io.github.joaofranciscoms.devitrine.dominio.projeto.model.Projeto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjetoMapper {

    @Mapping(target = "tecnologias", ignore = true)
    Projeto toEntity(ProjetoDTO projetoDTO);
}
