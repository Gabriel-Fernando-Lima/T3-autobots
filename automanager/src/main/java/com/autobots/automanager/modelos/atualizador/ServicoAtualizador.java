package com.autobots.automanager.modelos.atualizador;

import com.autobots.automanager.entidades.Servico;
import com.autobots.automanager.modelos.StringVerificadorNulo;

public class ServicoAtualizador {
    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void atualizar(Servico servico, Servico atualizacao) {
        if (atualizacao != null) {
            if (!verificador.verificar(atualizacao.getNome())) {
                servico.setNome(atualizacao.getNome());
            }
            if (!verificador.verificar(atualizacao.getDescricao())) {
                servico.setDescricao(atualizacao.getDescricao());
            }
            if (atualizacao.getValor() > 0) {
                servico.setValor(atualizacao.getValor());
            }
        }
    }
}