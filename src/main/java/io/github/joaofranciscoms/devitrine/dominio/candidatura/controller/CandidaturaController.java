package io.github.joaofranciscoms.devitrine.dominio.candidatura.controller;

import io.github.joaofranciscoms.devitrine.dominio.candidatura.dto.CandidaturaDTO;
import io.github.joaofranciscoms.devitrine.dominio.candidatura.dto.CandidaturaDetalhesDTO;
import io.github.joaofranciscoms.devitrine.dominio.candidatura.service.CandidaturaService;
import io.github.joaofranciscoms.devitrine.dominio.usuario.model.TipoUsuario;
import io.github.joaofranciscoms.devitrine.dominio.usuario.model.Usuario;
import io.github.joaofranciscoms.devitrine.infra.security.ApenasTipo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidaturas")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CandidaturaController {

    private final CandidaturaService service;

    @PostMapping
    @ApenasTipo({TipoUsuario.PESQUISADOR, TipoUsuario.DESENVOLVEDOR})
    public ResponseEntity<CandidaturaDetalhesDTO> salvarCandidatura(
            @RequestBody @Valid CandidaturaDTO dto,
            @RequestAttribute("usuarioAutenticado") Usuario usuario) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto, usuario));
    }

    @GetMapping
    public Page<CandidaturaDetalhesDTO> listarVagas(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size)
    {
        var pageRequest = PageRequest.of(page, size);
        return service.list(pageRequest);
    }
}
