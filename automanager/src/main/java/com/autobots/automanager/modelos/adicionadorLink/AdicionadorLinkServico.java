package com.autobots.automanager.modelos.adicionadorLink;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controles.ServicoControle;
import com.autobots.automanager.DTOs.ServicoDTO;

@Component
public class AdicionadorLinkServico implements AdicionadorLink<ServicoDTO> {

    @Override
    public void adicionarLink(List<ServicoDTO> lista) {
        for (ServicoDTO servicoDto : lista) {
            long id = servicoDto.getId();
            Link linkProprio = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(ServicoControle.class)
                            .obterServico(id))
                    .withSelfRel();
            servicoDto.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(ServicoDTO objeto) {
        Link linkLista = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(ServicoControle.class)
                        .obterServicos())
                .withRel("lista_servicos");
        objeto.add(linkLista);


    }
}