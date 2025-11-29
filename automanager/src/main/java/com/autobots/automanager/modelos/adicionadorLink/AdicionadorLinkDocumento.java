package com.autobots.automanager.modelos.adicionadorLink;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controles.DocumentoControle;
import com.autobots.automanager.DTOs.DocumentoDTO;

@Component
public class AdicionadorLinkDocumento implements AdicionadorLink<DocumentoDTO> {

    @Override
    public void adicionarLink(List<DocumentoDTO> lista) {
        for (DocumentoDTO documentoDto : lista) {
            long id = documentoDto.getId();
            Link linkProprio = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(DocumentoControle.class)
                            .obterDocumento(id))
                    .withSelfRel();
            documentoDto.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(DocumentoDTO objeto) {
        Link linkLista = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(DocumentoControle.class)
                        .obterDocumentos())
                .withRel("lista_documentos");
        objeto.add(linkLista);


    }
}