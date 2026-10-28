package io.github.joaofranciscoms.devitrine.dominio.projeto.mapper;

import io.github.joaofranciscoms.devitrine.dominio.projeto.dto.ProjetoDetalhesDTO;
import io.github.joaofranciscoms.devitrine.dominio.projeto.model.Projeto;
import io.github.joaofranciscoms.devitrine.dominio.tecnologia.model.Tecnologia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ProjetoDetalhesMapper {

    @Mapping(source = "autor.nome", target = "autorNome")
    ProjetoDetalhesDTO toDTO(Projeto projeto);

    default List<String> toNomes(Set<Tecnologia> tecnologias){
        return tecnologias.stream().map(Tecnologia::getNome).sorted().toList();
    }
}
