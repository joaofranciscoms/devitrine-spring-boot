package io.github.joaofranciscoms.devitrine.dominio.candidatura.service;

import io.github.joaofranciscoms.devitrine.dominio.candidatura.dto.CandidaturaDTO;
import io.github.joaofranciscoms.devitrine.dominio.candidatura.dto.CandidaturaDetalhesDTO;
import io.github.joaofranciscoms.devitrine.dominio.candidatura.mapper.CandidaturaDetalhesMapper;
import io.github.joaofranciscoms.devitrine.dominio.candidatura.mapper.CandidaturaMapper;
import io.github.joaofranciscoms.devitrine.dominio.candidatura.model.Candidatura;
import io.github.joaofranciscoms.devitrine.dominio.candidatura.repository.CandidaturaRepository;
import io.github.joaofranciscoms.devitrine.dominio.usuario.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidaturaService {

    private final CandidaturaRepository repository;
    private final CandidaturaMapper candidaturaMapper;
    private final CandidaturaDetalhesMapper candidaturaDetalhesMapper;

    public CandidaturaDetalhesDTO save(CandidaturaDTO dto, Usuario autor) {
        Candidatura entity = candidaturaMapper.toEntity(dto);
        entity.setAutor(autor);
        Candidatura salvo = repository.save(entity);
        return candidaturaDetalhesMapper.toDTO(salvo);
    }

    public Page<CandidaturaDetalhesDTO> list(PageRequest pageRequest){
        return repository.findAll(pageRequest).map(candidaturaDetalhesMapper::toDTO);
    }
}
