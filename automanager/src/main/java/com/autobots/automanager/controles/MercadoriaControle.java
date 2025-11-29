package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Mercadoria;
import com.autobots.automanager.DTOs.MercadoriaDTO;
import com.autobots.automanager.converter.MercadoriaConverter;
import com.autobots.automanager.modelos.adicionadorLink.AdicionadorLinkMercadoria;
import com.autobots.automanager.modelos.atualizador.MercadoriaAtualizador;
import com.autobots.automanager.modelos.selecionador.MercadoriaSelecionador;
import com.autobots.automanager.repositorios.RepositorioMercadoria;

@RestController
@RequestMapping("/mercadoria")
public class MercadoriaControle {
    @Autowired
    private RepositorioMercadoria repositorio;
    @Autowired
    private MercadoriaSelecionador selecionador;
    @Autowired
    private AdicionadorLinkMercadoria adicionadorLink;
    @Autowired
    private MercadoriaConverter conversor;

    @GetMapping("/{id}")
    public ResponseEntity<MercadoriaDTO> obterMercadoria(@PathVariable long id) {
        List<Mercadoria> mercadorias = repositorio.findAll();
        Mercadoria mercadoria = selecionador.selecionar(mercadorias, id);
        if (mercadoria == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            MercadoriaDTO dto = conversor.entidadeParaDto(mercadoria);
            adicionadorLink.adicionarLink(dto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
    }

    @GetMapping("/mercadorias")
    public ResponseEntity<List<MercadoriaDTO>> obterMercadorias() {
        List<Mercadoria> mercadorias = repositorio.findAll();
        if (mercadorias.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<MercadoriaDTO> dtos = conversor.entidadeParaDto(mercadorias);
            adicionadorLink.adicionarLink(dtos);
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrarMercadoria(@RequestBody Mercadoria mercadoria) {
        HttpStatus status = HttpStatus.CONFLICT;
        if (mercadoria.getId() == null) {
            repositorio.save(mercadoria);
            status = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(status);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarMercadoria(@RequestBody Mercadoria atualizacao) {
        HttpStatus status = HttpStatus.CONFLICT;
        Mercadoria mercadoria = repositorio.findById(atualizacao.getId()).orElse(null);
        if (mercadoria != null) {
            MercadoriaAtualizador atualizador = new MercadoriaAtualizador();
            atualizador.atualizar(mercadoria, atualizacao);
            repositorio.save(mercadoria);
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(status);
    }

    @DeleteMapping("/excluir")
    public ResponseEntity<?> excluirMercadoria(@RequestBody Mercadoria exclusao) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Mercadoria mercadoria = repositorio.findById(exclusao.getId()).orElse(null);
        if (mercadoria != null) {
            repositorio.delete(mercadoria);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(status);
    }
}