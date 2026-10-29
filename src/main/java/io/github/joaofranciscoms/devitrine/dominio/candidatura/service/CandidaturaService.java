package io.github.joaofranciscoms.devitrine.dominio.candidatura.service;

import io.github.joaofranciscoms.devitrine.dominio.candidatura.dto.CandidaturaDTO;
import io.github.joaofranciscoms.devitrine.dominio.candidatura.dto.CandidaturaDetalhesDTO;
import io.github.joaofranciscoms.devitrine.dominio.candidatura.mapper.CandidaturaDetalhesMapper;
import io.github.joaofranciscoms.devitrine.dominio.candidatura.mapper.CandidaturaMapper;
import io.github.joaofranciscoms.devitrine.dominio.candidatura.model.Candidatura;
import io.github.joaofranciscoms.devitrine.dominio.candidatura.repository.CandidaturaRepository;
import io.github.joaofranciscoms.devitrine.dominio.usuario.model.Usuario;
import io.github.joaofranciscoms.devitrine.dominio.vaga.model.Vaga;
import io.github.joaofranciscoms.devitrine.dominio.vaga.repository.VagaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CandidaturaService {

    private final CandidaturaRepository repository;
    private final CandidaturaMapper candidaturaMapper;
    private final CandidaturaDetalhesMapper candidaturaDetalhesMapper;
    private final VagaRepository vagaRepository;

    @Transactional
    public CandidaturaDetalhesDTO save(CandidaturaDTO dto, Usuario autor) {
        Vaga vaga = vagaRepository.findById(dto.vagaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga não encontrada"));

        if (repository.existsByVagaIdAndAutorId(vaga.getId(), autor.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Você já se candidatou a esta vaga");
        }

        Candidatura entity = new Candidatura();
        entity.setAutor(autor);
        entity.setVaga(vaga);

        return candidaturaDetalhesMapper.toDTO(repository.save(entity));
    }

    public Page<CandidaturaDetalhesDTO> list(PageRequest pageRequest){
        return repository.findAll(pageRequest).map(candidaturaDetalhesMapper::toDTO);
    }
}
