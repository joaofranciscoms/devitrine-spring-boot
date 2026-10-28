package io.github.joaofranciscoms.devitrine.dominio.ideia.mapper;

import io.github.joaofranciscoms.devitrine.dominio.ideia.dto.IdeiaDTO;
import io.github.joaofranciscoms.devitrine.dominio.ideia.model.Ideia;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IdeiaMapper {
    Ideia toEntity(IdeiaDTO ideiaDTO);
}
