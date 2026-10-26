package io.github.joaofranciscoms.devitrine.dominio.candidatura.mapper;

import io.github.joaofranciscoms.devitrine.dominio.candidatura.dto.CandidaturaDTO;
import io.github.joaofranciscoms.devitrine.dominio.candidatura.model.Candidatura;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CandidaturaMapper {
    Candidatura toEntity(CandidaturaDTO candidaturaDTO);
}
