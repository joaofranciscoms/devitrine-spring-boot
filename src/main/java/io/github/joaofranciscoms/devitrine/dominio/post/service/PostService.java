package io.github.joaofranciscoms.devitrine.dominio.post.service;

import io.github.joaofranciscoms.devitrine.common.exceptions.PostNaoEncontradoException;
import io.github.joaofranciscoms.devitrine.dominio.post.dto.PostDTO;
import io.github.joaofranciscoms.devitrine.dominio.post.dto.PostDetalhesDTO;
import io.github.joaofranciscoms.devitrine.dominio.post.mapper.PostDetalhesMapper;
import io.github.joaofranciscoms.devitrine.dominio.post.mapper.PostMapper;
import io.github.joaofranciscoms.devitrine.dominio.post.model.Post;
import io.github.joaofranciscoms.devitrine.dominio.post.repository.PostRepository;
import io.github.joaofranciscoms.devitrine.dominio.usuario.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repository;
    private final PostMapper postMapper;
    private final PostDetalhesMapper postDetalhesMapper;

    public PostDetalhesDTO save(PostDTO dto, Usuario autor) {
        Post entity = postMapper.toEntity(dto);
        entity.setAutor(autor);
        Post salvo = repository.save(entity);
        return postDetalhesMapper.toDTO(salvo);
    }

    public PostDetalhesDTO findById(UUID id){
        return repository.findById(id).map(postDetalhesMapper::toDTO).orElseThrow(() -> new PostNaoEncontradoException());
    }

    public void update(UUID id, PostDTO dto){
        var entity = postMapper.toEntity(dto);
        entity.setTitulo(dto.titulo());
        entity.setConteudo(dto.conteudo());
        repository.save(entity);
    }

    public Page<PostDetalhesDTO> list(PageRequest pageRequest){
        return repository.findAll(pageRequest).map(postDetalhesMapper::toDTO);
    }

    public void delete(UUID id){
        Post entity = repository.findById(id).orElseThrow(() -> new PostNaoEncontradoException());
        repository.delete(entity);
    }
}
