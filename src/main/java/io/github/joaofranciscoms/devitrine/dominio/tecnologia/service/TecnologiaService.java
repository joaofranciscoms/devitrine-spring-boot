package io.github.joaofranciscoms.devitrine.dominio.tecnologia.service;

import io.github.joaofranciscoms.devitrine.dominio.tecnologia.model.Tecnologia;
import io.github.joaofranciscoms.devitrine.dominio.tecnologia.repository.TecnologiaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TecnologiaService {

    private final TecnologiaRepository repository;

    public List<String> list(){
        return repository.findAllByOrderByNomeAsc().stream().map(Tecnologia::getNome).toList();
    }

    public Set<Tecnologia> obterOuCriar(List<String> nomes){
        Set<Tecnologia> tecnologias = new HashSet<>();
        if(nomes == null){
            return tecnologias;
        }

        for(String nome : nomes){
            if(nome == null || nome.isBlank()){
                continue;
            }

            String nomeTratado = nome.trim();
            Tecnologia tecnologia = repository.findByNomeIgnoreCase(nomeTratado).orElseGet(() -> {
                Tecnologia nova = new Tecnologia();
                nova.setNome(nomeTratado);
                return repository.save(nova);
            });
            tecnologias.add(tecnologia);
        }
        return tecnologias;
    }
}
