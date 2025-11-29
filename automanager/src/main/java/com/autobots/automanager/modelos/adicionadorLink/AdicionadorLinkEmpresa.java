package com.autobots.automanager.modelos.adicionadorLink;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.DTOs.EmpresaDTO;
import com.autobots.automanager.controles.EmpresaControle;

@Component
public class AdicionadorLinkEmpresa implements AdicionadorLink<EmpresaDTO> {

    @Override
    public void adicionarLink(List<EmpresaDTO> lista) {
        for (EmpresaDTO empresaDto : lista) {
            long id = empresaDto.getId();
            Link linkProprio = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(EmpresaControle.class)
                            .obterEmpresa(id))
                    .withSelfRel();
            empresaDto.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(EmpresaDTO objeto) {
        Link linkLista = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(EmpresaControle.class)
                        .obterEmpresas())
                .withRel("lista_empresas");
        objeto.add(linkLista);


    }
}