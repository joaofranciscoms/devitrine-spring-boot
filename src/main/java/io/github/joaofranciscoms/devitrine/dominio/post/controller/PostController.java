package io.github.joaofranciscoms.devitrine.dominio.post.controller;

import io.github.joaofranciscoms.devitrine.dominio.post.dto.PostDTO;
import io.github.joaofranciscoms.devitrine.dominio.post.dto.PostDetalhesDTO;
import io.github.joaofranciscoms.devitrine.dominio.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PostController {

    private final PostService service;

    @PostMapping
    public ResponseEntity<Object> salvarPost(@RequestBody PostDTO dto){
        PostDetalhesDTO postDetalhesDTO = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(postDetalhesDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> obterDetalhes(@PathVariable("id") String id){
        var idPost = UUID.fromString(id);
        var result = service.findById(idPost);
        return ResponseEntity.ok(result);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizarPost(@PathVariable("id") String id, @RequestBody PostDTO postDTO){
        var idPost = UUID.fromString(id);
        service.update(idPost, postDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public Page<PostDetalhesDTO> listarPosts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size)
    {
        var pageRequest = PageRequest.of(page, size);
        return service.list(pageRequest);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarPost(@PathVariable("id") String id){
        var idPost = UUID.fromString(id);
        service.delete(idPost);
        return ResponseEntity.noContent().build();
    }
}
