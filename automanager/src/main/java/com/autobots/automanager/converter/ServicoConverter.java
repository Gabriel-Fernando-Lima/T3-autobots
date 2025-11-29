package com.autobots.automanager.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.autobots.automanager.DTOs.ServicoDTO;
import com.autobots.automanager.entidades.Servico;

@Component
public class ServicoConverter {

    public Servico dtoParaEntidade(ServicoDTO dto) {
        Servico entidade = new Servico();

        entidade.setId(dto.getId());
        entidade.setNome(dto.getNome());
        entidade.setValor(dto.getValor());
        entidade.setDescricao(dto.getDescricao());

        return entidade;
    }

    public ServicoDTO entidadeParaDto(Servico entidade) {
        ServicoDTO dto = new ServicoDTO();

        dto.setId(entidade.getId());
        dto.setNome(entidade.getNome());
        dto.setValor(entidade.getValor());
        dto.setDescricao(entidade.getDescricao());

        return dto;
    }

    public List<ServicoDTO> entidadeParaDto(List<Servico> entidades) {
        return entidades.stream()
                .map(this::entidadeParaDto)
                .collect(Collectors.toList());
    }
}
