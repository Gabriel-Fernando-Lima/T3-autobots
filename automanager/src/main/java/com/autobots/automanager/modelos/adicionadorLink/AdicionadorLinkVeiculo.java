package com.autobots.automanager.modelos.adicionadorLink;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controles.VeiculoControle;
import com.autobots.automanager.DTOs.VeiculoDTO;

@Component
public class AdicionadorLinkVeiculo implements AdicionadorLink<VeiculoDTO> {

    @Override
    public void adicionarLink(List<VeiculoDTO> lista) {
        for (VeiculoDTO veiculoDto : lista) {
            long id = veiculoDto.getId();
            Link linkProprio = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(VeiculoControle.class)
                            .obterVeiculo(id))
                    .withSelfRel();
            veiculoDto.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(VeiculoDTO objeto) {
        Link linkLista = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(VeiculoControle.class)
                        .obterVeiculos())
                .withRel("lista_veiculos");
        objeto.add(linkLista);

    }
}