package io.github.joaofranciscoms.devitrine.dominio.comentario.service;

import io.github.joaofranciscoms.devitrine.common.exceptions.ComentarioNaoEncontradoException;
import io.github.joaofranciscoms.devitrine.common.exceptions.PostNaoEncontradoException;
import io.github.joaofranciscoms.devitrine.dominio.comentario.dto.ComentarioDTO;
import io.github.joaofranciscoms.devitrine.dominio.comentario.dto.ComentarioDetalhesDTO;
import io.github.joaofranciscoms.devitrine.dominio.comentario.mapper.ComentarioDetalhesMapper;
import io.github.joaofranciscoms.devitrine.dominio.comentario.mapper.ComentarioMapper;
import io.github.joaofranciscoms.devitrine.dominio.comentario.model.Comentario;
import io.github.joaofranciscoms.devitrine.dominio.comentario.repository.ComentarioRepository;
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
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final PostRepository postRepository;
    private final ComentarioMapper comentarioMapper;
    private final ComentarioDetalhesMapper comentarioDetalhesMapper;

    public ComentarioDetalhesDTO save(ComentarioDTO dto, Usuario autor){
        Post post = postRepository.findById(dto.idPost())
                .orElseThrow(() -> new PostNaoEncontradoException());

        Comentario entity = comentarioMapper.toEntity(dto);
        entity.setAutor(autor);
        entity.setPost(post);
        comentarioRepository.save(entity);
        return comentarioDetalhesMapper.toDTO(entity);
    }

    public ComentarioDetalhesDTO findById(UUID id){
        return comentarioRepository.findById(id).map(comentarioDetalhesMapper::toDTO).orElseThrow(() -> new ComentarioNaoEncontradoException());
    }

    public void update(UUID id, ComentarioDTO dto){
        var entity = comentarioMapper.toEntity(dto);
        entity.setConteudo(dto.conteudo());
        comentarioRepository.save(entity);
    }

    public Page<ComentarioDetalhesDTO> list(UUID postId, PageRequest pageRequest) {
        return comentarioRepository.findByPostId(postId, pageRequest).map(comentarioDetalhesMapper::toDTO);
    }

    public void delete(UUID id){
        Comentario entity = comentarioRepository.findById(id).orElseThrow(() -> new ComentarioNaoEncontradoException());
        comentarioRepository.delete(entity);
    }
}
