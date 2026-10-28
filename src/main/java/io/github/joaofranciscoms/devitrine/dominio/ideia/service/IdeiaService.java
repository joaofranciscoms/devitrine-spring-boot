package io.github.joaofranciscoms.devitrine.dominio.ideia.service;

import io.github.joaofranciscoms.devitrine.common.exceptions.IdeiaNaoEncontradaException;
import io.github.joaofranciscoms.devitrine.dominio.ideia.dto.IdeiaDTO;
import io.github.joaofranciscoms.devitrine.dominio.ideia.dto.IdeiaDetalhesDTO;
import io.github.joaofranciscoms.devitrine.dominio.ideia.mapper.IdeiaDetalhesMapper;
import io.github.joaofranciscoms.devitrine.dominio.ideia.mapper.IdeiaMapper;
import io.github.joaofranciscoms.devitrine.dominio.ideia.model.Ideia;
import io.github.joaofranciscoms.devitrine.dominio.ideia.repository.IdeiaRepository;
import io.github.joaofranciscoms.devitrine.dominio.usuario.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IdeiaService {

    private final IdeiaRepository repository;
    private final IdeiaMapper ideiaMapper;
    private final IdeiaDetalhesMapper ideiaDetalhesMapper;

    public IdeiaDetalhesDTO save(IdeiaDTO dto, Usuario autor) {
        Ideia entity = ideiaMapper.toEntity(dto);
        entity.setAutor(autor);
        Ideia salvo = repository.save(entity);
        return ideiaDetalhesMapper.toDTO(salvo);
    }

    public IdeiaDetalhesDTO findById(UUID id){
        return repository.findById(id).map(ideiaDetalhesMapper::toDTO).orElseThrow(() -> new IdeiaNaoEncontradaException());
    }

    public Page<IdeiaDetalhesDTO> list(PageRequest pageRequest){
        return repository.findAll(pageRequest).map(ideiaDetalhesMapper::toDTO);
    }
}
