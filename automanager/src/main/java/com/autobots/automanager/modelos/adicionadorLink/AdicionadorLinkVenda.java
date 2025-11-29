package com.autobots.automanager.modelos.adicionadorLink;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controles.VendaControle;
import com.autobots.automanager.DTOs.VendaDTO;

@Component
public class AdicionadorLinkVenda implements AdicionadorLink<VendaDTO> {

    @Override
    public void adicionarLink(List<VendaDTO> lista) {
        for (VendaDTO vendaDto : lista) {
            long id = vendaDto.getId();
            Link linkProprio = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(VendaControle.class)
                            .obterVenda(id))
                    .withSelfRel();
            vendaDto.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(VendaDTO objeto) {
        Link linkLista = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(VendaControle.class)
                        .obterVendas())
                .withRel("lista_vendas");
        objeto.add(linkLista);


    }
}