package io.github.joaofranciscoms.devitrine.dominio.comentario.service;

import io.github.joaofranciscoms.devitrine.common.ComentarioNaoEncontradoException;
import io.github.joaofranciscoms.devitrine.dominio.comentario.dto.ComentarioDTO;
import io.github.joaofranciscoms.devitrine.dominio.comentario.dto.ComentarioDetalhesDTO;
import io.github.joaofranciscoms.devitrine.dominio.comentario.mapper.ComentarioDetalhesMapper;
import io.github.joaofranciscoms.devitrine.dominio.comentario.mapper.ComentarioMapper;
import io.github.joaofranciscoms.devitrine.dominio.comentario.model.Comentario;
import io.github.joaofranciscoms.devitrine.dominio.comentario.repository.ComentarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    private final ComentarioRepository repository;
    private final ComentarioMapper comentarioMapper;
    private final ComentarioDetalhesMapper comentarioDetalhesMapper;

    public ComentarioDetalhesDTO save(ComentarioDTO dto){
        Comentario entity = comentarioMapper.toEntity(dto);
        repository.save(entity);
        return comentarioDetalhesMapper.toDTO(entity);
    }

    public ComentarioDetalhesDTO findById(UUID id){
        return repository.findById(id).map(comentarioDetalhesMapper::toDTO).orElseThrow(() -> new ComentarioNaoEncontradoException());
    }

    public void update(UUID id, ComentarioDTO dto){
        var entity = comentarioMapper.toEntity(dto);
        entity.setConteudo(dto.conteudo());
        repository.save(entity);
    }

    public Page<ComentarioDetalhesDTO> list(PageRequest pageRequest){
        return repository.findAll(pageRequest).map(comentarioDetalhesMapper::toDTO);
    }

    public void delete(UUID id){
        Comentario entity = repository.findById(id).orElseThrow(() -> new ComentarioNaoEncontradoException());
        repository.delete(entity);
    }
}
