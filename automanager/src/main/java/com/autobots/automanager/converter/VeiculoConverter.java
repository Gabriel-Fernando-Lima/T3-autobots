package com.autobots.automanager.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.autobots.automanager.DTOs.VeiculoDTO;
import com.autobots.automanager.entidades.Veiculo;

@Component
public class VeiculoConverter {

    public Veiculo dtoParaEntidade(VeiculoDTO dto) {
        Veiculo entidade = new Veiculo();

        entidade.setId(dto.getId());
        entidade.setTipo(dto.getTipo());
        entidade.setModelo(dto.getModelo());
        entidade.setPlaca(dto.getPlaca());
        return entidade;
    }

    public VeiculoDTO entidadeParaDto(Veiculo entidade) {
        VeiculoDTO dto = new VeiculoDTO();

        dto.setId(entidade.getId());
        dto.setTipo(entidade.getTipo());
        dto.setModelo(entidade.getModelo());
        dto.setPlaca(entidade.getPlaca());
        if (entidade.getProprietario() != null) {
            dto.setProprietarioId(entidade.getProprietario().getId());
        }
        if (entidade.getVendas() != null) {
            dto.setVendasIds(entidade.getVendas().stream()
                    .map(v -> v.getId())
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public List<VeiculoDTO> entidadeParaDto(List<Veiculo> entidades) {
        return entidades.stream()
                .map(this::entidadeParaDto)
                .collect(Collectors.toList());
    }
}
