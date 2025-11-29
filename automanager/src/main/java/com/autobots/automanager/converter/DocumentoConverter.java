package com.autobots.automanager.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.autobots.automanager.DTOs.DocumentoDTO;
import com.autobots.automanager.entidades.Documento;

@Component
public class DocumentoConverter {

    public Documento dtoParaEntidade(DocumentoDTO dto) {
        Documento entidade = new Documento();

        entidade.setId(dto.getId());
        entidade.setTipo(dto.getTipo());
        entidade.setDataEmissao(dto.getDataEmissao());
        entidade.setNumero(dto.getNumero());

        return entidade;
    }

    public DocumentoDTO entidadeParaDto(Documento entidade) {
        DocumentoDTO dto = new DocumentoDTO();

        dto.setId(entidade.getId());
        dto.setTipo(entidade.getTipo());
        dto.setDataEmissao(entidade.getDataEmissao());
        dto.setNumero(entidade.getNumero());

        return dto;
    }

    public List<DocumentoDTO> entidadeParaDto(List<Documento> entidades) {
        return entidades.stream()
                .map(this::entidadeParaDto)
                .collect(Collectors.toList());
    }
}
