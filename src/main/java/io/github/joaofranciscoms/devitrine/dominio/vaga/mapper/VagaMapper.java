package io.github.joaofranciscoms.devitrine.dominio.vaga.mapper;

import io.github.joaofranciscoms.devitrine.dominio.vaga.dto.VagaDTO;
import io.github.joaofranciscoms.devitrine.dominio.vaga.model.Vaga;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VagaMapper {
    Vaga toEntity(VagaDTO vagaDTO);
}
