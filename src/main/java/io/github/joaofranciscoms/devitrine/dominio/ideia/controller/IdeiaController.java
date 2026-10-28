package io.github.joaofranciscoms.devitrine.dominio.ideia.controller;

import io.github.joaofranciscoms.devitrine.dominio.ideia.dto.IdeiaDTO;
import io.github.joaofranciscoms.devitrine.dominio.ideia.dto.IdeiaDetalhesDTO;
import io.github.joaofranciscoms.devitrine.dominio.ideia.service.IdeiaService;
import io.github.joaofranciscoms.devitrine.dominio.usuario.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/ideias")
@RequiredArgsConstructor
@CrossOrigin("*")
public class IdeiaController {

    private final IdeiaService service;

    @PostMapping
    public ResponseEntity<Object> salvarIdeia(
            @RequestBody IdeiaDTO dto,
            @RequestAttribute("usuarioAutenticado") Usuario usuario) {

        IdeiaDetalhesDTO ideiaDetalhesDTO = service.save(dto, usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(ideiaDetalhesDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> obterDetalhes(@PathVariable("id") String id){
        var idIdeia = UUID.fromString(id);
        var result = service.findById(idIdeia);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public Page<IdeiaDetalhesDTO> listarIdeias(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size)
    {
        var pageRequest = PageRequest.of(page, size);
        return service.list(pageRequest);
    }
}
