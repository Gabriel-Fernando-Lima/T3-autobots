package com.autobots.automanager.modelos.adicionadorLink;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controles.EnderecoControle;
import com.autobots.automanager.DTOs.EnderecoDTO;

@Component
public class AdicionadorLinkEndereco implements AdicionadorLink<EnderecoDTO> {

    @Override
    public void adicionarLink(List<EnderecoDTO> lista) {
        for (EnderecoDTO enderecoDto : lista) {
            long id = enderecoDto.getId();
            Link linkProprio = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(EnderecoControle.class)
                            .obterEndereco(id))
                    .withSelfRel();
            enderecoDto.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(EnderecoDTO objeto) {
        Link linkLista = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(EnderecoControle.class)
                        .obterEnderecos())
                .withRel("lista_enderecos");
        objeto.add(linkLista);

    }
}