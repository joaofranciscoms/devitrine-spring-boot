package io.github.joaofranciscoms.devitrine.dominio.comentario.mapper;

import io.github.joaofranciscoms.devitrine.dominio.comentario.dto.ComentarioDTO;
import io.github.joaofranciscoms.devitrine.dominio.comentario.model.Comentario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComentarioMapper {

    @Mapping(target = "post", ignore = true)
    Comentario toEntity(ComentarioDTO comentarioDTO);
}
