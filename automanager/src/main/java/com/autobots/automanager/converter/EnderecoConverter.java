package com.autobots.automanager.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.autobots.automanager.DTOs.EnderecoDTO;
import com.autobots.automanager.entidades.Endereco;

@Component
public class EnderecoConverter {

    public Endereco dtoParaEntidade(EnderecoDTO dto) {
        Endereco entidade = new Endereco();

        entidade.setId(dto.getId());
        entidade.setEstado(dto.getEstado());
        entidade.setCidade(dto.getCidade());
        entidade.setBairro(dto.getBairro());
        entidade.setRua(dto.getRua());
        entidade.setNumero(dto.getNumero());
        entidade.setCodigoPostal(dto.getCodigoPostal());
        entidade.setInformacoesAdicionais(dto.getInformacoesAdicionais());

        return entidade;
    }

    public EnderecoDTO entidadeParaDto(Endereco entidade) {
        EnderecoDTO dto = new EnderecoDTO();

        dto.setId(entidade.getId());
        dto.setEstado(entidade.getEstado());
        dto.setCidade(entidade.getCidade());
        dto.setBairro(entidade.getBairro());
        dto.setRua(entidade.getRua());
        dto.setNumero(entidade.getNumero());
        dto.setCodigoPostal(entidade.getCodigoPostal());
        dto.setInformacoesAdicionais(entidade.getInformacoesAdicionais());

        return dto;
    }

    public List<EnderecoDTO> entidadeParaDto(List<Endereco> entidades) {
        return entidades.stream()
                .map(this::entidadeParaDto)
                .collect(Collectors.toList());
    }
}
