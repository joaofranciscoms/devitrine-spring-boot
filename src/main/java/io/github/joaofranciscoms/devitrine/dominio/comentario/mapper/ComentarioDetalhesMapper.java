package io.github.joaofranciscoms.devitrine.dominio.comentario.mapper;

import io.github.joaofranciscoms.devitrine.dominio.comentario.dto.ComentarioDetalhesDTO;
import io.github.joaofranciscoms.devitrine.dominio.comentario.model.ComentarioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ComentarioDetalhesMapper {
    ComentarioDetalhesDTO toDTO(ComentarioEntity comentarioEntity);
}
