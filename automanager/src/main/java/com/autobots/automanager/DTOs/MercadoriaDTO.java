package com.autobots.automanager.DTOs;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Data
public class MercadoriaDTO extends RepresentationModel<MercadoriaDTO> {
    private Long id;
    private Date validade;
    private Date fabricao;
    private Date cadastro;
    private String nome;
    private long quantidade;
    private double valor;
    private String descricao;
}