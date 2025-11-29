package com.autobots.automanager.modelos.adicionadorLink;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controles.MercadoriaControle;
import com.autobots.automanager.DTOs.MercadoriaDTO;

@Component
public class AdicionadorLinkMercadoria implements AdicionadorLink<MercadoriaDTO> {

    @Override
    public void adicionarLink(List<MercadoriaDTO> lista) {
        for (MercadoriaDTO mercadoriaDto : lista) {
            long id = mercadoriaDto.getId();
            Link linkProprio = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(MercadoriaControle.class)
                            .obterMercadoria(id))
                    .withSelfRel();
            mercadoriaDto.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(MercadoriaDTO objeto) {
        Link linkLista = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(MercadoriaControle.class)
                        .obterMercadorias())
                .withRel("lista_mercadorias");
        objeto.add(linkLista);


    }
}