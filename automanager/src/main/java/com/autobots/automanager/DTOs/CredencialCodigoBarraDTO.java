package com.autobots.automanager.DTOs;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Data
public class CredencialCodigoBarraDTO extends RepresentationModel<CredencialCodigoBarraDTO> {
    private Long id;
    private Date criacao;
    private Date ultimoAcesso;
    private boolean inativo;
    private long codigo;
}
