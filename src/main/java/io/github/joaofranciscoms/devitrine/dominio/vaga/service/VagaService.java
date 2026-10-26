package io.github.joaofranciscoms.devitrine.dominio.vaga.service;

import io.github.joaofranciscoms.devitrine.common.exceptions.PostNaoEncontradoException;
import io.github.joaofranciscoms.devitrine.common.exceptions.VagaNaoEncontradaException;
import io.github.joaofranciscoms.devitrine.dominio.post.dto.PostDTO;
import io.github.joaofranciscoms.devitrine.dominio.post.dto.PostDetalhesDTO;
import io.github.joaofranciscoms.devitrine.dominio.post.mapper.PostDetalhesMapper;
import io.github.joaofranciscoms.devitrine.dominio.post.mapper.PostMapper;
import io.github.joaofranciscoms.devitrine.dominio.post.model.Post;
import io.github.joaofranciscoms.devitrine.dominio.post.repository.PostRepository;
import io.github.joaofranciscoms.devitrine.dominio.usuario.model.Usuario;
import io.github.joaofranciscoms.devitrine.dominio.vaga.dto.VagaDTO;
import io.github.joaofranciscoms.devitrine.dominio.vaga.dto.VagaDetalhesDTO;
import io.github.joaofranciscoms.devitrine.dominio.vaga.mapper.VagaDetalhesMapper;
import io.github.joaofranciscoms.devitrine.dominio.vaga.mapper.VagaMapper;
import io.github.joaofranciscoms.devitrine.dominio.vaga.model.Vaga;
import io.github.joaofranciscoms.devitrine.dominio.vaga.repository.VagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VagaService {

    private final VagaRepository repository;
    private final VagaMapper vagaMapper;
    private final VagaDetalhesMapper vagaDetalhesMapper;

    public VagaDetalhesDTO save(VagaDTO dto, Usuario autor) {
        Vaga entity = vagaMapper.toEntity(dto);
        entity.setAutor(autor);
        Vaga salvo = repository.save(entity);
        return vagaDetalhesMapper.toDTO(salvo);
    }

    public VagaDetalhesDTO findById(UUID id){
        return repository.findById(id).map(vagaDetalhesMapper::toDTO).orElseThrow(() -> new VagaNaoEncontradaException());
    }

    public Page<VagaDetalhesDTO> list(PageRequest pageRequest){
        return repository.findAll(pageRequest).map(vagaDetalhesMapper::toDTO);
    }
}
