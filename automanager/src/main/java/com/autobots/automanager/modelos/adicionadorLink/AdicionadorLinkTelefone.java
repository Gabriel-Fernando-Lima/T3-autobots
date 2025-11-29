package com.autobots.automanager.modelos.adicionadorLink;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controles.TelefoneControle;
import com.autobots.automanager.DTOs.TelefoneDTO;

@Component
public class AdicionadorLinkTelefone implements AdicionadorLink<TelefoneDTO> {

    @Override
    public void adicionarLink(List<TelefoneDTO> lista) {
        for (TelefoneDTO telefoneDto : lista) {
            long id = telefoneDto.getId();
            Link linkProprio = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(TelefoneControle.class)
                            .obterTelefone(id))
                    .withSelfRel();
            telefoneDto.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(TelefoneDTO objeto) {
        Link linkLista = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(TelefoneControle.class)
                        .obterTelefones())
                .withRel("lista_telefones");
        objeto.add(linkLista);


    }
}