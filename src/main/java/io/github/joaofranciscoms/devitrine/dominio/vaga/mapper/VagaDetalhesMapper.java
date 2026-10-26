package io.github.joaofranciscoms.devitrine.dominio.vaga.mapper;

import io.github.joaofranciscoms.devitrine.dominio.vaga.dto.VagaDetalhesDTO;
import io.github.joaofranciscoms.devitrine.dominio.vaga.model.Vaga;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VagaDetalhesMapper {

    @Mapping(source = "autor.nome", target = "autorNome")
    VagaDetalhesDTO toDTO(Vaga vaga);
}
