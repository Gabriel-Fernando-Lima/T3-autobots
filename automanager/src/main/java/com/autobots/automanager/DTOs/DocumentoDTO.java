package com.autobots.automanager.DTOs;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.autobots.automanager.enumeracoes.TipoDocumento;

import lombok.Data;

@Data
public class DocumentoDTO extends RepresentationModel<DocumentoDTO> {
    private Long id;
    private TipoDocumento tipo;
    private Date dataEmissao;
    private String numero;
}
