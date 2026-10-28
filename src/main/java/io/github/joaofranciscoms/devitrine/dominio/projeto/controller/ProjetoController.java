package io.github.joaofranciscoms.devitrine.dominio.projeto.controller;

import io.github.joaofranciscoms.devitrine.dominio.projeto.dto.ProjetoDTO;
import io.github.joaofranciscoms.devitrine.dominio.projeto.dto.ProjetoDetalhesDTO;
import io.github.joaofranciscoms.devitrine.dominio.projeto.model.TipoProjeto;
import io.github.joaofranciscoms.devitrine.dominio.projeto.service.ProjetoService;
import io.github.joaofranciscoms.devitrine.dominio.usuario.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/projetos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProjetoController {

    private final ProjetoService service;

    @PostMapping
    public ResponseEntity<Object> salvarProjeto(
            @RequestBody ProjetoDTO dto,
            @RequestAttribute("usuarioAutenticado") Usuario usuario) {

        ProjetoDetalhesDTO projetoDetalhesDTO = service.save(dto, usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(projetoDetalhesDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> obterDetalhes(@PathVariable("id") String id){
        var idProjeto = UUID.fromString(id);
        var result = service.findById(idProjeto);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public Page<ProjetoDetalhesDTO> listarProjetos(
            @RequestParam(value = "tipo", required = false) TipoProjeto tipo,
            @RequestParam(value = "tecnologia", required = false) String tecnologia,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size)
    {
        var pageRequest = PageRequest.of(page, size);
        return service.list(tipo, tecnologia, pageRequest);
    }
}
