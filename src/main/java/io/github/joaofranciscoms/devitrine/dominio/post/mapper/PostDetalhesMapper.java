package io.github.joaofranciscoms.devitrine.dominio.post.mapper;

import io.github.joaofranciscoms.devitrine.dominio.post.dto.PostDetalhesDTO;
import io.github.joaofranciscoms.devitrine.dominio.post.model.PostEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostDetalhesMapper {
    PostDetalhesDTO toDTO(PostEntity postEntity);
}
