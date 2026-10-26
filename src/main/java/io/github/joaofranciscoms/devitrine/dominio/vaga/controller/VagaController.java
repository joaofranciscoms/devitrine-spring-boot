package io.github.joaofranciscoms.devitrine.dominio.vaga.controller;

import io.github.joaofranciscoms.devitrine.dominio.usuario.model.Usuario;
import io.github.joaofranciscoms.devitrine.dominio.vaga.dto.VagaDTO;
import io.github.joaofranciscoms.devitrine.dominio.vaga.dto.VagaDetalhesDTO;
import io.github.joaofranciscoms.devitrine.dominio.vaga.service.VagaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/vagas")
@RequiredArgsConstructor
@CrossOrigin("*")
public class VagaController {

    private final VagaService service;

    @PostMapping
    public ResponseEntity<Object> salvarVaga(
            @RequestBody VagaDTO dto,
            @RequestAttribute("usuarioAutenticado") Usuario usuario) {

        VagaDetalhesDTO vagaDetalhesDTO = service.save(dto, usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(vagaDetalhesDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> obterDetalhes(@PathVariable("id") String id){
        var idVaga = UUID.fromString(id);
        var result = service.findById(idVaga);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public Page<VagaDetalhesDTO> listarVagas(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size)
    {
        var pageRequest = PageRequest.of(page, size);
        return service.list(pageRequest);
    }
}

