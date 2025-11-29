package com.autobots.automanager.DTOs;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Data
public class EmailDTO extends RepresentationModel<EmailDTO> {
    private Long id;
    private String endereco;
}
