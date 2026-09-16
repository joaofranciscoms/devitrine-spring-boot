package io.github.joaofranciscoms.devitrine.dominio.comentario.mapper;

import io.github.joaofranciscoms.devitrine.dominio.comentario.dto.ComentarioDTO;
import io.github.joaofranciscoms.devitrine.dominio.comentario.model.Comentario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ComentarioMapper {
    Comentario toEntity(ComentarioDTO comentarioDTO);
}
