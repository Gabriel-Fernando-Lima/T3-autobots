package com.autobots.automanager.modelos.atualizador;

import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.modelos.StringVerificadorNulo;

public class EmpresaAtualizador {
    private StringVerificadorNulo verificador = new StringVerificadorNulo();
    private EnderecoAtualizador enderecoAtualizador = new EnderecoAtualizador();

    public void atualizar(Empresa empresa, Empresa atualizacao) {
        if (atualizacao != null) {
            if (!verificador.verificar(atualizacao.getRazaoSocial())) {
                empresa.setRazaoSocial(atualizacao.getRazaoSocial());
            }
            if (!verificador.verificar(atualizacao.getNomeFantasia())) {
                empresa.setNomeFantasia(atualizacao.getNomeFantasia());
            }
            if (atualizacao.getCadastro() != null) {
                empresa.setCadastro(atualizacao.getCadastro());
            }
            if (atualizacao.getEndereco() != null) {
                enderecoAtualizador.atualizar(empresa.getEndereco(), atualizacao.getEndereco());
            }
        }
    }
}