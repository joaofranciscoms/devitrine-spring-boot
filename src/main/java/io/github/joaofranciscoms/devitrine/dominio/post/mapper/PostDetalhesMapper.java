package io.github.joaofranciscoms.devitrine.dominio.post.mapper;

import io.github.joaofranciscoms.devitrine.dominio.post.dto.PostDetalhesDTO;
import io.github.joaofranciscoms.devitrine.dominio.post.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostDetalhesMapper {

    @Mapping(source = "autor.nome", target = "autorNome")
    PostDetalhesDTO toDTO(Post post);
}
