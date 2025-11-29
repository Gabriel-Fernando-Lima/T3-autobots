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

import com.autobots.automanager.entidades.Veiculo;
import com.autobots.automanager.DTOs.VeiculoDTO;
import com.autobots.automanager.converter.VeiculoConverter;
import com.autobots.automanager.modelos.adicionadorLink.AdicionadorLinkVeiculo;
import com.autobots.automanager.modelos.atualizador.VeiculoAtualizador;
import com.autobots.automanager.modelos.selecionador.VeiculoSelecionador;
import com.autobots.automanager.repositorios.RepositorioVeiculo;

@RestController
@RequestMapping("/veiculo")
public class VeiculoControle {
    @Autowired
    private RepositorioVeiculo repositorio;
    @Autowired
    private VeiculoSelecionador selecionador;
    @Autowired
    private AdicionadorLinkVeiculo adicionadorLink;
    @Autowired
    private VeiculoConverter conversor;

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> obterVeiculo(@PathVariable long id) {
        List<Veiculo> veiculos = repositorio.findAll();
        Veiculo veiculo = selecionador.selecionar(veiculos, id);
        if (veiculo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            VeiculoDTO dto = conversor.entidadeParaDto(veiculo);
            adicionadorLink.adicionarLink(dto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
    }

    @GetMapping("/veiculos")
    public ResponseEntity<List<VeiculoDTO>> obterVeiculos() {
        List<Veiculo> veiculos = repositorio.findAll();
        if (veiculos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<VeiculoDTO> dtos = conversor.entidadeParaDto(veiculos);
            adicionadorLink.adicionarLink(dtos);
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrarVeiculo(@RequestBody Veiculo veiculo) {
        HttpStatus status = HttpStatus.CONFLICT;
        if (veiculo.getId() == null) {
            repositorio.save(veiculo);
            status = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(status);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarVeiculo(@RequestBody Veiculo atualizacao) {
        HttpStatus status = HttpStatus.CONFLICT;
        Veiculo veiculo = repositorio.findById(atualizacao.getId()).orElse(null);
        if (veiculo != null) {
            VeiculoAtualizador atualizador = new VeiculoAtualizador();
            atualizador.atualizar(veiculo, atualizacao);
            repositorio.save(veiculo);
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(status);
    }

    @DeleteMapping("/excluir")
    public ResponseEntity<?> excluirVeiculo(@RequestBody Veiculo exclusao) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Veiculo veiculo = repositorio.findById(exclusao.getId()).orElse(null);
        if (veiculo != null) {
            repositorio.delete(veiculo);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(status);
    }
}