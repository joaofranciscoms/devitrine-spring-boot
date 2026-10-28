package io.github.joaofranciscoms.devitrine.dominio.colaboracao.controller;

import io.github.joaofranciscoms.devitrine.dominio.colaboracao.dto.ColaboracaoDTO;
import io.github.joaofranciscoms.devitrine.dominio.colaboracao.dto.ColaboracaoDetalhesDTO;
import io.github.joaofranciscoms.devitrine.dominio.colaboracao.service.ColaboracaoService;
import io.github.joaofranciscoms.devitrine.dominio.usuario.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/colaboracoes")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ColaboracaoController {

    private final ColaboracaoService service;

    @PostMapping
    public ResponseEntity<Object> salvarColaboracao(@RequestBody ColaboracaoDTO dto, @RequestAttribute("usuarioAutenticado") Usuario usuario){
        ColaboracaoDetalhesDTO colaboracaoDetalhesDTO = service.save(dto, usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(colaboracaoDetalhesDTO);
    }

    @GetMapping
    public Page<ColaboracaoDetalhesDTO> listarColaboracoes(
            @RequestParam UUID ideiaId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size)
    {
        var pageRequest = PageRequest.of(page, size);
        return service.list(ideiaId, pageRequest);
    }
}
