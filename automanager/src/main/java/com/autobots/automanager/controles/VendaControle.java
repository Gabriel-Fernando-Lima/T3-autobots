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

import com.autobots.automanager.entidades.Venda;
import com.autobots.automanager.DTOs.VendaDTO;
import com.autobots.automanager.converter.VendaConverter;
import com.autobots.automanager.modelos.adicionadorLink.AdicionadorLinkVenda;
import com.autobots.automanager.modelos.atualizador.VendaAtualizador;
import com.autobots.automanager.modelos.selecionador.VendaSelecionador;
import com.autobots.automanager.repositorios.RepositorioVenda;

@RestController
@RequestMapping("/venda")
public class VendaControle {
    @Autowired
    private RepositorioVenda repositorio;
    @Autowired
    private VendaSelecionador selecionador;
    @Autowired
    private AdicionadorLinkVenda adicionadorLink;
    @Autowired
    private VendaConverter conversor;

    @GetMapping("/{id}")
    public ResponseEntity<VendaDTO> obterVenda(@PathVariable long id) {
        List<Venda> vendas = repositorio.findAll();
        Venda venda = selecionador.selecionar(vendas, id);
        if (venda == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            VendaDTO dto = conversor.entidadeParaDto(venda);
            adicionadorLink.adicionarLink(dto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
    }

    @GetMapping("/vendas")
    public ResponseEntity<List<VendaDTO>> obterVendas() {
        List<Venda> vendas = repositorio.findAll();
        if (vendas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<VendaDTO> dtos = conversor.entidadeParaDto(vendas);
            adicionadorLink.adicionarLink(dtos);
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrarVenda(@RequestBody Venda venda) {
        HttpStatus status = HttpStatus.CONFLICT;
        if (venda.getId() == null) {
            repositorio.save(venda);
            status = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(status);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarVenda(@RequestBody Venda atualizacao) {
        HttpStatus status = HttpStatus.CONFLICT;
        Venda venda = repositorio.findById(atualizacao.getId()).orElse(null);
        if (venda != null) {
            VendaAtualizador atualizador = new VendaAtualizador();
            atualizador.atualizar(venda, atualizacao);
            repositorio.save(venda);
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(status);
    }

    @DeleteMapping("/excluir")
    public ResponseEntity<?> excluirVenda(@RequestBody Venda exclusao) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Venda venda = repositorio.findById(exclusao.getId()).orElse(null);
        if (venda != null) {
            repositorio.delete(venda);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(status);
    }
}