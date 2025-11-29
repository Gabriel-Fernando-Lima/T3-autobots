package com.autobots.automanager.modelos.adicionadorLink;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.DTOs.UsuarioDTO;
import com.autobots.automanager.controles.UsuarioControle;


@Component
public class AdicionadorLinkUsuario implements AdicionadorLink<UsuarioDTO> {

    @Override
    public void adicionarLink(List<UsuarioDTO> lista) {
        for (UsuarioDTO usuarioDto : lista) {
            long id = usuarioDto.getId();
            Link linkProprio = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(UsuarioControle.class)
                            .obterUsuario(id))
                    .withSelfRel();
            usuarioDto.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(UsuarioDTO objeto) {
        Link linkLista = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(UsuarioControle.class)
                        .obterUsuarios())
                .withRel("lista_usuarios");
        objeto.add(linkLista);
    }
}