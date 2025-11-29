package com.autobots.automanager.DTOs;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Data
public class CredencialDTO extends RepresentationModel<CredencialDTO> {
    private Long id;
    private Date criacao;
    private Date ultimoAcesso;
    private boolean inativo;
    private String nomeUsuario;
    private String senha;
}