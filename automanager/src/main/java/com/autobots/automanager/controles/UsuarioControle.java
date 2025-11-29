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

import com.autobots.automanager.DTOs.UsuarioDTO;
import com.autobots.automanager.entidades.Usuario;
import com.autobots.automanager.converter.UsuarioConverter;
import com.autobots.automanager.modelos.adicionadorLink.AdicionadorLinkUsuario;
import com.autobots.automanager.modelos.atualizador.UsuarioAtualizador;
import com.autobots.automanager.modelos.selecionador.UsuarioSelecionador;
import com.autobots.automanager.repositorios.RepositorioUsuario;

@RestController
@RequestMapping("/usuario")
public class UsuarioControle {
    @Autowired
    private RepositorioUsuario repositorio;
    @Autowired
    private UsuarioSelecionador selecionador;
    @Autowired
    private AdicionadorLinkUsuario adicionadorLink;
    @Autowired
    private UsuarioConverter conversor; 

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obterUsuario(@PathVariable long id) { // Retorna DTO
        List<Usuario> usuarios = repositorio.findAll();
        Usuario usuario = selecionador.selecionar(usuarios, id);
        
        if (usuario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            // 2. CONVERTER ENTIDADE -> DTO
            UsuarioDTO dto = conversor.entidadeParaDto(usuario);
            
            // 3. ADICIONAR LINK NO DTO
            adicionadorLink.adicionarLink(dto);
            
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioDTO>> obterUsuarios() { // Retorna Lista de DTOs
        List<Usuario> usuarios = repositorio.findAll();
        
        if (usuarios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            // 2. CONVERTER LISTA ENTIDADE -> LISTA DTO
            List<UsuarioDTO> dtos = conversor.entidadeParaDto(usuarios);
            
            // 3. ADICIONAR LINKS NOS DTOS
            adicionadorLink.adicionarLink(dtos);
            
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody Usuario usuario) {
        HttpStatus status = HttpStatus.CONFLICT;
        if (usuario.getId() == null) {
            repositorio.save(usuario);
            status = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(status);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarUsuario(@RequestBody Usuario atualizacao) {
        HttpStatus status = HttpStatus.CONFLICT;
        Usuario usuario = repositorio.findById(atualizacao.getId()).orElse(null);
        if (usuario != null) {
            UsuarioAtualizador atualizador = new UsuarioAtualizador();
            atualizador.atualizar(usuario, atualizacao);
            repositorio.save(usuario);
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(status);
    }

    @DeleteMapping("/excluir")
    public ResponseEntity<?> excluirUsuario(@RequestBody Usuario exclusao) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Usuario usuario = repositorio.findById(exclusao.getId()).orElse(null);
        if (usuario != null) {
            repositorio.delete(usuario);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(status);
    }
}