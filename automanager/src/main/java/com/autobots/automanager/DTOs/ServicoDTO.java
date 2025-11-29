package com.autobots.automanager.DTOs;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Data
public class ServicoDTO extends RepresentationModel<ServicoDTO> {
    private Long id;
    private String nome;
    private double valor;
    private String descricao;
}