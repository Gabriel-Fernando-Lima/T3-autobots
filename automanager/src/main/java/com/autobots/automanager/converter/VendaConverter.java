package com.autobots.automanager.converter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.autobots.automanager.DTOs.VendaDTO;
import com.autobots.automanager.entidades.Mercadoria;
import com.autobots.automanager.entidades.Servico;
import com.autobots.automanager.entidades.Usuario;
import com.autobots.automanager.entidades.Veiculo;
import com.autobots.automanager.entidades.Venda;
import com.autobots.automanager.repositorios.RepositorioMercadoria;
import com.autobots.automanager.repositorios.RepositorioServico;
import com.autobots.automanager.repositorios.RepositorioUsuario;
import com.autobots.automanager.repositorios.RepositorioVeiculo;

@Component
public class VendaConverter {

    @Autowired
    private RepositorioUsuario repositorioUsuario;
    @Autowired
    private RepositorioVeiculo repositorioVeiculo;
    @Autowired
    private RepositorioMercadoria repositorioMercadoria;
    @Autowired
    private RepositorioServico repositorioServico;

    public Venda dtoParaEntidade(VendaDTO dto) {
        Venda venda = new Venda();
        
        // 1. Dados simples
        venda.setId(dto.getId());
        venda.setCadastro(dto.getCadastro());
        venda.setIdentificacao(dto.getIdentificacao());

        // 2. Convertendo ID -> Usuario (Cliente)
        if (dto.getClienteId() != null) {
            Usuario cliente = repositorioUsuario.findById(dto.getClienteId()).orElse(null);
            venda.setCliente(cliente); // Aqui setamos o objeto completo do banco!
        }

        // 3. Convertendo ID -> Usuario (Funcionario)
        if (dto.getFuncionarioId() != null) {
            Usuario funcionario = repositorioUsuario.findById(dto.getFuncionarioId()).orElse(null);
            venda.setFuncionario(funcionario);
        }

        // 4. Convertendo ID -> Veiculo
        if (dto.getVeiculoId() != null) {
            Veiculo veiculo = repositorioVeiculo.findById(dto.getVeiculoId()).orElse(null);
            venda.setVeiculo(veiculo);
        }

        // 5. Convertendo IDs -> Mercadorias
        if (dto.getMercadoriasIds() != null) {
            Set<Mercadoria> mercadorias = new HashSet<>();
            for (Long id : dto.getMercadoriasIds()) {
                Mercadoria m = repositorioMercadoria.findById(id).orElse(null);
                if (m != null) {
                    mercadorias.add(m);
                }
            }
            venda.setMercadorias(mercadorias);
        }

        // 6. Convertendo IDs -> Servicos
        if (dto.getServicosIds() != null) {
            Set<Servico> servicos = new HashSet<>();
            for (Long id : dto.getServicosIds()) {
                Servico s = repositorioServico.findById(id).orElse(null);
                if (s != null) {
                    servicos.add(s);
                }
            }
            venda.setServicos(servicos);
        }

        return venda;
    }

    public VendaDTO entidadeParaDto(Venda entidade) {
        VendaDTO dto = new VendaDTO();
        dto.setId(entidade.getId());
        dto.setCadastro(entidade.getCadastro());
        dto.setIdentificacao(entidade.getIdentificacao());

        if (entidade.getCliente() != null) {
            dto.setClienteId(entidade.getCliente().getId());
        }
        if (entidade.getFuncionario() != null) {
            dto.setFuncionarioId(entidade.getFuncionario().getId());
        }
        if (entidade.getVeiculo() != null) {
            dto.setVeiculoId(entidade.getVeiculo().getId());
        }
        if (entidade.getMercadorias() != null) {
            dto.setMercadoriasIds(entidade.getMercadorias().stream()
                    .map(Mercadoria::getId).collect(Collectors.toList()));
        }
        if (entidade.getServicos() != null) {
            dto.setServicosIds(entidade.getServicos().stream()
                    .map(Servico::getId).collect(Collectors.toList()));
        }
        return dto;
    }
    
    public List<VendaDTO> entidadeParaDto(List<Venda> entidades) {
        return entidades.stream().map(this::entidadeParaDto).collect(Collectors.toList());
    }
}