package com.autobots.automanager.DTOs;

import java.util.Date;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Data
public class VendaDTO extends RepresentationModel<VendaDTO> {
    private Long id;
    private Date cadastro;
    private String identificacao;
    private Long clienteId;
    private Long funcionarioId;
    private Long veiculoId;
    private List<Long> mercadoriasIds;
    private List<Long> servicosIds;
}