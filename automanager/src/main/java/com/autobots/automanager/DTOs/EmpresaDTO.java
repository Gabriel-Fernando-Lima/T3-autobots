package com.autobots.automanager.DTOs;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.entidades.Telefone;

import lombok.Data;

@Data
public class EmpresaDTO extends RepresentationModel<EmpresaDTO> {
    private Long id;
    private String razaoSocial;
    private String nomeFantasia;
    private Date cadastro;
    private Endereco endereco;
    private Set<Telefone> telefones;
    
    private List<Long> usuariosIds;
    private List<Long> mercadoriasIds;
    private List<Long> servicosIds;
    private List<Long> vendasIds;
}