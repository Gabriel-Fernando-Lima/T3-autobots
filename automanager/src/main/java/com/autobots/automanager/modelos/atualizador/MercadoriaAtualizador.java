package com.autobots.automanager.modelos.atualizador;

import com.autobots.automanager.entidades.Mercadoria;
import com.autobots.automanager.modelos.StringVerificadorNulo;

public class MercadoriaAtualizador {
    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void atualizar(Mercadoria mercadoria, Mercadoria atualizacao) {
        if (atualizacao != null) {
            if (!verificador.verificar(atualizacao.getNome())) {
                mercadoria.setNome(atualizacao.getNome());
            }
            if (!verificador.verificar(atualizacao.getDescricao())) {
                mercadoria.setDescricao(atualizacao.getDescricao());
            }
            if (atualizacao.getCadastro() != null) {
                mercadoria.setCadastro(atualizacao.getCadastro());
            }
            if (atualizacao.getFabricao() != null) {
                mercadoria.setFabricao(atualizacao.getFabricao());
            }
            if (atualizacao.getValidade() != null) {
                mercadoria.setValidade(atualizacao.getValidade());
            }
            if (atualizacao.getValor() > 0) {
                mercadoria.setValor(atualizacao.getValor());
            }
            if (atualizacao.getQuantidade() > 0) {
                mercadoria.setQuantidade(atualizacao.getQuantidade());
            }
        }
    }
}