package com.autobots.automanager.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.autobots.automanager.DTOs.UsuarioDTO;
import com.autobots.automanager.entidades.Mercadoria;
import com.autobots.automanager.entidades.Usuario;
import com.autobots.automanager.entidades.Veiculo;
import com.autobots.automanager.entidades.Venda;

@Component
public class UsuarioConverter {

    public Usuario dtoParaEntidade(UsuarioDTO dto) {
        Usuario entidade = new Usuario();

        entidade.setId(dto.getId());
        entidade.setNome(dto.getNome());
        entidade.setNomeSocial(dto.getNomeSocial());
        entidade.setPerfis(dto.getPerfis());
        entidade.setTelefones(dto.getTelefones());
        entidade.setEndereco(dto.getEndereco());
        entidade.setDocumentos(dto.getDocumentos());
        entidade.setEmails(dto.getEmails());
        entidade.setCredenciais(dto.getCredenciais());

        return entidade;
    }

    public UsuarioDTO entidadeParaDto(Usuario entidade) {
        UsuarioDTO dto = new UsuarioDTO();

        dto.setId(entidade.getId());
        dto.setNome(entidade.getNome());
        dto.setNomeSocial(entidade.getNomeSocial());
        dto.setPerfis(entidade.getPerfis());
        dto.setTelefones(entidade.getTelefones());
        dto.setEndereco(entidade.getEndereco());
        dto.setDocumentos(entidade.getDocumentos());
        dto.setEmails(entidade.getEmails());
        dto.setCredenciais(entidade.getCredenciais());

        
        if (entidade.getVeiculos() != null) {
            dto.setVeiculosIds(entidade.getVeiculos().stream()
                    .map(Veiculo::getId)
                    .collect(Collectors.toList()));
        }

        if (entidade.getVendas() != null) {
            dto.setVendasIds(entidade.getVendas().stream()
                    .map(Venda::getId)
                    .collect(Collectors.toList()));
        }

        if (entidade.getMercadorias() != null) {
            dto.setMercadoriasIds(entidade.getMercadorias().stream()
                    .map(Mercadoria::getId)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public List<UsuarioDTO> entidadeParaDto(List<Usuario> entidades) {
        return entidades.stream()
                .map(this::entidadeParaDto)
                .collect(Collectors.toList());
    }
}