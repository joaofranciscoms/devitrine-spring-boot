package io.github.joaofranciscoms.devitrine.dominio.comentario.controller;

import io.github.joaofranciscoms.devitrine.dominio.comentario.dto.ComentarioDTO;
import io.github.joaofranciscoms.devitrine.dominio.comentario.dto.ComentarioDetalhesDTO;
import io.github.joaofranciscoms.devitrine.dominio.comentario.service.ComentarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/comentarios")
@RequiredArgsConstructor
public class ComentarioController {

    private final ComentarioService service;

    @PostMapping
    public ResponseEntity<Object> salvarComentario(@RequestBody ComentarioDTO dto){
        ComentarioDetalhesDTO comentarioDetalhesDTO = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioDetalhesDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> obterDetalhes(@PathVariable("id") String id){
        var idComentario = UUID.fromString(id);
        var result = service.findById(idComentario);
        return ResponseEntity.ok(result);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizarComentario(@PathVariable("id") String id, @RequestBody ComentarioDTO comentarioDTO){
        var idComentario = UUID.fromString(id);
        service.update(idComentario, comentarioDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public Page<ComentarioDetalhesDTO> listarComentarios(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size)
    {
        var pageRequest = PageRequest.of(page, size);
        return service.list(pageRequest);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarComentario(@PathVariable("id") String id){
        var idComentario = UUID.fromString(id);
        service.delete(idComentario);
        return ResponseEntity.noContent().build();
    }
}
