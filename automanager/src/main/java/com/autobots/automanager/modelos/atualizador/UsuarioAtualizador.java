package com.autobots.automanager.modelos.atualizador;

import java.util.ArrayList;

import com.autobots.automanager.entidades.Usuario;
import com.autobots.automanager.modelos.StringVerificadorNulo;

public class UsuarioAtualizador {
    private StringVerificadorNulo verificador = new StringVerificadorNulo();
    private EnderecoAtualizador enderecoAtualizador = new EnderecoAtualizador();
    private DocumentoAtualizador documentoAtualizador = new DocumentoAtualizador();
    private TelefoneAtualizador telefoneAtualizador = new TelefoneAtualizador();

    private void atualizarDados(Usuario usuario, Usuario atualizacao) {
        if (!verificador.verificar(atualizacao.getNome())) {
            usuario.setNome(atualizacao.getNome());
        }
        if (!verificador.verificar(atualizacao.getNomeSocial())) {
            usuario.setNomeSocial(atualizacao.getNomeSocial());
        }
        if (atualizacao.getPerfis() != null && !atualizacao.getPerfis().isEmpty()) {
            usuario.getPerfis().clear();
            usuario.getPerfis().addAll(atualizacao.getPerfis());
        }
    }

    public void atualizar(Usuario usuario, Usuario atualizacao) {
        atualizarDados(usuario, atualizacao);
        
        enderecoAtualizador.atualizar(usuario.getEndereco(), atualizacao.getEndereco());
        documentoAtualizador.atualizar(new ArrayList<>(usuario.getDocumentos()), new ArrayList<>(atualizacao.getDocumentos()));
        telefoneAtualizador.atualizar(new ArrayList<>(usuario.getTelefones()), new ArrayList<>(atualizacao.getTelefones()));
    }
}