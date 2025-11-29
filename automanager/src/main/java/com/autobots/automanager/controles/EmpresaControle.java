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

import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.entidades.Usuario;
import com.autobots.automanager.DTOs.EmpresaDTO;
import com.autobots.automanager.converter.EmpresaConverter;
import com.autobots.automanager.modelos.adicionadorLink.AdicionadorLinkEmpresa;
import com.autobots.automanager.modelos.atualizador.EmpresaAtualizador;
import com.autobots.automanager.modelos.selecionador.EmpresaSelecionador;
import com.autobots.automanager.repositorios.RepositorioEmpresa;
import com.autobots.automanager.repositorios.RepositorioUsuario;

@RestController
@RequestMapping("/empresa")
public class EmpresaControle {
    @Autowired
    private RepositorioEmpresa repositorio;
    @Autowired
    private EmpresaSelecionador selecionador;
    @Autowired
    private AdicionadorLinkEmpresa adicionadorLink;
    @Autowired
    private EmpresaConverter conversor;
    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO> obterEmpresa(@PathVariable long id) {
        List<Empresa> empresas = repositorio.findAll();
        Empresa empresa = selecionador.selecionar(empresas, id);
        if (empresa == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            EmpresaDTO dto = conversor.entidadeParaDto(empresa);
            adicionadorLink.adicionarLink(dto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
    }

    @GetMapping("/empresas")
    public ResponseEntity<List<EmpresaDTO>> obterEmpresas() {
        List<Empresa> empresas = repositorio.findAll();
        if (empresas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<EmpresaDTO> dtos = conversor.entidadeParaDto(empresas);
            adicionadorLink.adicionarLink(dtos);
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrarEmpresa(@RequestBody EmpresaDTO empresaDto) {
        HttpStatus status = HttpStatus.CONFLICT;

        Empresa empresa = conversor.dtoParaEntidade(empresaDto);

        if (empresa.getId() == null) {
            repositorio.save(empresa);
            status = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(status);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarEmpresa(@RequestBody Empresa atualizacao) {
        HttpStatus status = HttpStatus.CONFLICT;
        Empresa empresa = repositorio.findById(atualizacao.getId()).orElse(null);

        if (empresa != null) {
            EmpresaAtualizador atualizador = new EmpresaAtualizador();
            atualizador.atualizar(empresa, atualizacao);
            repositorio.save(empresa);
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(status);
    }

    @DeleteMapping("/excluir")
    public ResponseEntity<?> excluirEmpresa(@RequestBody Empresa exclusao) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Empresa empresa = repositorio.findById(exclusao.getId()).orElse(null);

        if (empresa != null) {
            repositorio.delete(empresa);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(status);
    }

    @PostMapping("/associar/{idEmpresa}/{idUsuario}")
    public ResponseEntity<?> associarUsuario(@PathVariable long idEmpresa, @PathVariable long idUsuario) {
        Empresa empresa = repositorio.findById(idEmpresa).orElse(null);
        Usuario usuario = repositorioUsuario.findById(idUsuario).orElse(null);

        if (empresa == null || usuario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        empresa.getUsuarios().add(usuario);

        repositorio.save(empresa);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}