package io.github.joaofranciscoms.devitrine.dominio.colaboracao.service;

import io.github.joaofranciscoms.devitrine.common.exceptions.IdeiaNaoEncontradaException;
import io.github.joaofranciscoms.devitrine.dominio.colaboracao.dto.ColaboracaoDTO;
import io.github.joaofranciscoms.devitrine.dominio.colaboracao.dto.ColaboracaoDetalhesDTO;
import io.github.joaofranciscoms.devitrine.dominio.colaboracao.mapper.ColaboracaoDetalhesMapper;
import io.github.joaofranciscoms.devitrine.dominio.colaboracao.mapper.ColaboracaoMapper;
import io.github.joaofranciscoms.devitrine.dominio.colaboracao.model.Colaboracao;
import io.github.joaofranciscoms.devitrine.dominio.colaboracao.repository.ColaboracaoRepository;
import io.github.joaofranciscoms.devitrine.dominio.ideia.model.Ideia;
import io.github.joaofranciscoms.devitrine.dominio.ideia.repository.IdeiaRepository;
import io.github.joaofranciscoms.devitrine.dominio.usuario.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ColaboracaoService {

    private final ColaboracaoRepository colaboracaoRepository;
    private final IdeiaRepository ideiaRepository;
    private final ColaboracaoMapper colaboracaoMapper;
    private final ColaboracaoDetalhesMapper colaboracaoDetalhesMapper;

    public ColaboracaoDetalhesDTO save(ColaboracaoDTO dto, Usuario autor){
        Ideia ideia = ideiaRepository.findById(dto.idIdeia())
                .orElseThrow(() -> new IdeiaNaoEncontradaException());

        Colaboracao entity = colaboracaoMapper.toEntity(dto);
        entity.setAutor(autor);
        entity.setIdeia(ideia);
        colaboracaoRepository.save(entity);
        return colaboracaoDetalhesMapper.toDTO(entity);
    }

    public Page<ColaboracaoDetalhesDTO> list(UUID ideiaId, PageRequest pageRequest) {
        return colaboracaoRepository.findByIdeiaId(ideiaId, pageRequest).map(colaboracaoDetalhesMapper::toDTO);
    }
}
