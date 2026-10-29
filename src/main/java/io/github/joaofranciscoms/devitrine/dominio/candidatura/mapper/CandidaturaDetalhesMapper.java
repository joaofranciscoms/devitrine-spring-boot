package io.github.joaofranciscoms.devitrine.dominio.candidatura.mapper;

import io.github.joaofranciscoms.devitrine.dominio.candidatura.dto.CandidaturaDetalhesDTO;
import io.github.joaofranciscoms.devitrine.dominio.candidatura.model.Candidatura;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CandidaturaDetalhesMapper {

    @Mapping(source = "dataCriacao", target = "dataPostagem")
    @Mapping(source = "autor.nome", target = "nomeCandidato")
    @Mapping(source = "vaga.autor.nome", target = "nomeEmpresa")
    CandidaturaDetalhesDTO toDTO(Candidatura candidatura);
}
