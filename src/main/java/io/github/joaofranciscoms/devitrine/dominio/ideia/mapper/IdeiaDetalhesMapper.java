package io.github.joaofranciscoms.devitrine.dominio.ideia.mapper;

import io.github.joaofranciscoms.devitrine.dominio.ideia.dto.IdeiaDetalhesDTO;
import io.github.joaofranciscoms.devitrine.dominio.ideia.model.Ideia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IdeiaDetalhesMapper {

    @Mapping(source = "autor.nome", target = "autorNome")
    IdeiaDetalhesDTO toDTO(Ideia ideia);
}
