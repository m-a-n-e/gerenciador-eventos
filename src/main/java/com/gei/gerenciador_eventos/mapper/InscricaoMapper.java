package com.gei.gerenciador_eventos.mapper;

import com.gei.gerenciador_eventos.dto.response.InscricaoResponseDTO;
import com.gei.gerenciador_eventos.entity.Inscricao;

public class InscricaoMapper {

    public static InscricaoResponseDTO toDTO(Inscricao inscricao) {
        InscricaoResponseDTO dto = new InscricaoResponseDTO();
        dto.setId(inscricao.getId());
        dto.setDataInscricao(inscricao.getDataInscricao());
        dto.setStatus(inscricao.getStatus());
        if (inscricao.getUsuario() != null) {
            dto.setUsuario(UsuarioMapper.toDTO(inscricao.getUsuario()));
        }
        if (inscricao.getEvento() != null) {
            dto.setEvento(EventoMapper.toDTO(inscricao.getEvento()));
        }
        return dto;
    }
}