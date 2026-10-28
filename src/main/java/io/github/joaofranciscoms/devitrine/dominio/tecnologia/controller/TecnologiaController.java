package io.github.joaofranciscoms.devitrine.dominio.tecnologia.controller;

import io.github.joaofranciscoms.devitrine.dominio.tecnologia.service.TecnologiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tecnologias")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TecnologiaController {

    private final TecnologiaService service;

    @GetMapping
    public List<String> listarTecnologias(){
        return service.list();
    }
}
