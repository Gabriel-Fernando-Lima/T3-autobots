package com.autobots.automanager.modelos.atualizador;

import com.autobots.automanager.entidades.Veiculo;
import com.autobots.automanager.modelos.StringVerificadorNulo;

public class VeiculoAtualizador {
    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void atualizar(Veiculo veiculo, Veiculo atualizacao) {
        if (atualizacao != null) {
            if (!verificador.verificar(atualizacao.getModelo())) {
                veiculo.setModelo(atualizacao.getModelo());
            }
            if (!verificador.verificar(atualizacao.getPlaca())) {
                veiculo.setPlaca(atualizacao.getPlaca());
            }
            if (atualizacao.getTipo() != null) {
                veiculo.setTipo(atualizacao.getTipo());
            }
        }
    }
}