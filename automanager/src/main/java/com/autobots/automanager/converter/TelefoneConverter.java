package com.autobots.automanager.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.autobots.automanager.DTOs.TelefoneDTO;
import com.autobots.automanager.entidades.Telefone;

@Component
public class TelefoneConverter {

    public Telefone dtoParaEntidade(TelefoneDTO dto) {
        Telefone entidade = new Telefone();

        entidade.setId(dto.getId());
        entidade.setDdd(dto.getDdd());
        entidade.setNumero(dto.getNumero());

        return entidade;
    }

    public TelefoneDTO entidadeParaDto(Telefone entidade) {
        TelefoneDTO dto = new TelefoneDTO();

        dto.setId(entidade.getId());
        dto.setDdd(entidade.getDdd());
        dto.setNumero(entidade.getNumero());

        return dto;
    }

    public List<TelefoneDTO> entidadeParaDto(List<Telefone> entidades) {
        return entidades.stream()
                .map(this::entidadeParaDto)
                .collect(Collectors.toList());
    }
}
