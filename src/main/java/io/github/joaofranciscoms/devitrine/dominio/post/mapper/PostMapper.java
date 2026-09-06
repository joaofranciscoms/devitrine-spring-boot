package io.github.joaofranciscoms.devitrine.dominio.post.mapper;

import io.github.joaofranciscoms.devitrine.dominio.post.dto.PostDTO;
import io.github.joaofranciscoms.devitrine.dominio.post.model.PostEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostEntity toEntity(PostDTO postDTO);
}
