package com.autobots.automanager.DTOs;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Data
public class EnderecoDTO extends RepresentationModel<EnderecoDTO> {
    private Long id;
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private String numero;
    private String codigoPostal;
    private String informacoesAdicionais;
}
