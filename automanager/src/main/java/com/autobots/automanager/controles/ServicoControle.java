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

import com.autobots.automanager.entidades.Servico;
import com.autobots.automanager.DTOs.ServicoDTO;
import com.autobots.automanager.converter.ServicoConverter;
import com.autobots.automanager.modelos.adicionadorLink.AdicionadorLinkServico;
import com.autobots.automanager.modelos.atualizador.ServicoAtualizador;
import com.autobots.automanager.modelos.selecionador.ServicoSelecionador;
import com.autobots.automanager.repositorios.RepositorioServico;

@RestController
@RequestMapping("/servico")
public class ServicoControle {
    @Autowired
    private RepositorioServico repositorio;
    @Autowired
    private ServicoSelecionador selecionador;
    @Autowired
    private AdicionadorLinkServico adicionadorLink;
    @Autowired
    private ServicoConverter conversor;

    @GetMapping("/{id}")
    public ResponseEntity<ServicoDTO> obterServico(@PathVariable long id) {
        List<Servico> servicos = repositorio.findAll();
        Servico servico = selecionador.selecionar(servicos, id);
        if (servico == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            ServicoDTO dto = conversor.entidadeParaDto(servico);
            adicionadorLink.adicionarLink(dto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
    }

    @GetMapping("/servicos")
    public ResponseEntity<List<ServicoDTO>> obterServicos() {
        List<Servico> servicos = repositorio.findAll();
        if (servicos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<ServicoDTO> dtos = conversor.entidadeParaDto(servicos);
            adicionadorLink.adicionarLink(dtos);
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrarServico(@RequestBody Servico servico) {
        HttpStatus status = HttpStatus.CONFLICT;
        if (servico.getId() == null) {
            repositorio.save(servico);
            status = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(status);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarServico(@RequestBody Servico atualizacao) {
        HttpStatus status = HttpStatus.CONFLICT;
        Servico servico = repositorio.findById(atualizacao.getId()).orElse(null);
        if (servico != null) {
            ServicoAtualizador atualizador = new ServicoAtualizador();
            atualizador.atualizar(servico, atualizacao);
            repositorio.save(servico);
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(status);
    }

    @DeleteMapping("/excluir")
    public ResponseEntity<?> excluirServico(@RequestBody Servico exclusao) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Servico servico = repositorio.findById(exclusao.getId()).orElse(null);
        if (servico != null) {
            repositorio.delete(servico);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(status);
    }
}