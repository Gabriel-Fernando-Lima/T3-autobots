package com.autobots.automanager.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.autobots.automanager.DTOs.EmpresaDTO;
import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.entidades.Mercadoria;
import com.autobots.automanager.entidades.Servico;
import com.autobots.automanager.entidades.Usuario;
import com.autobots.automanager.entidades.Venda;

@Component
public class EmpresaConverter {

    public Empresa dtoParaEntidade(EmpresaDTO dto) {
        Empresa entidade = new Empresa();

        entidade.setId(dto.getId());
        entidade.setRazaoSocial(dto.getRazaoSocial());
        entidade.setNomeFantasia(dto.getNomeFantasia());
        entidade.setCadastro(dto.getCadastro());
        entidade.setEndereco(dto.getEndereco());
        entidade.setTelefones(dto.getTelefones());

        return entidade;
    }

    public EmpresaDTO entidadeParaDto(Empresa entidade) {
        EmpresaDTO dto = new EmpresaDTO();

        dto.setId(entidade.getId());
        dto.setRazaoSocial(entidade.getRazaoSocial());
        dto.setNomeFantasia(entidade.getNomeFantasia());
        dto.setCadastro(entidade.getCadastro());
        dto.setEndereco(entidade.getEndereco());
        dto.setTelefones(entidade.getTelefones());

        if (entidade.getUsuarios() != null) {
            dto.setUsuariosIds(entidade.getUsuarios().stream()
                    .map(Usuario::getId)
                    .collect(Collectors.toList()));
        }

        if (entidade.getMercadorias() != null) {
            dto.setMercadoriasIds(entidade.getMercadorias().stream()
                    .map(Mercadoria::getId)
                    .collect(Collectors.toList()));
        }

        if (entidade.getServicos() != null) {
            dto.setServicosIds(entidade.getServicos().stream()
                    .map(Servico::getId)
                    .collect(Collectors.toList()));
        }

        if (entidade.getVendas() != null) {
            dto.setVendasIds(entidade.getVendas().stream()
                    .map(Venda::getId)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public List<EmpresaDTO> entidadeParaDto(List<Empresa> entidades) {
        return entidades.stream()
                .map(this::entidadeParaDto)
                .collect(Collectors.toList());
    }
}
