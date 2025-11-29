package com.autobots.automanager.DTOs;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.autobots.automanager.enumeracoes.TipoVeiculo;

import lombok.Data;

@Data
public class VeiculoDTO extends RepresentationModel<VeiculoDTO> {
    private Long id;
    private TipoVeiculo tipo;
    private String modelo;
    private String placa;
    private Long proprietarioId;
    private List<Long> vendasIds;
}