package io.github.joaofranciscoms.devitrine.dominio.comentario.mapper;

import io.github.joaofranciscoms.devitrine.dominio.comentario.dto.ComentarioDetalhesDTO;
import io.github.joaofranciscoms.devitrine.dominio.comentario.model.Comentario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComentarioDetalhesMapper {

    @Mapping(source = "autor.nome", target = "autorNome")
    ComentarioDetalhesDTO toDTO(Comentario comentario);
}
