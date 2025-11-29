package com.autobots.automanager.modelos.atualizador;

import com.autobots.automanager.entidades.Venda;
import com.autobots.automanager.modelos.StringVerificadorNulo;

public class VendaAtualizador {
    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void atualizar(Venda venda, Venda atualizacao) {
        if (atualizacao != null) {
            if (!verificador.verificar(atualizacao.getIdentificacao())) {
                venda.setIdentificacao(atualizacao.getIdentificacao());
            }
            if (atualizacao.getCadastro() != null) {
                venda.setCadastro(atualizacao.getCadastro());
            }
            
            if (atualizacao.getCliente() != null) {
                venda.setCliente(atualizacao.getCliente());
            }
            if (atualizacao.getFuncionario() != null) {
                venda.setFuncionario(atualizacao.getFuncionario());
            }
            if (atualizacao.getVeiculo() != null) {
                venda.setVeiculo(atualizacao.getVeiculo());
            }

            if (atualizacao.getMercadorias() != null) {
                venda.getMercadorias().clear();
                venda.getMercadorias().addAll(atualizacao.getMercadorias());
            }
            if (atualizacao.getServicos() != null) {
                venda.getServicos().clear();
                venda.getServicos().addAll(atualizacao.getServicos());
            }
        }
    }
}