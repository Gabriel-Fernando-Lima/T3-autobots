package com.autobots.automanager.DTOs;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Data
public class TelefoneDTO extends RepresentationModel<TelefoneDTO> {
    private Long id;
    private String ddd;
    private String numero;
}
