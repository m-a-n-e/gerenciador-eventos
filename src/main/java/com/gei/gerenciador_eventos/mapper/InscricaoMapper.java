package com.gei.gerenciador_eventos.mapper;

import com.gei.gerenciador_eventos.dto.request.InscricaoRequestDTO;
import com.gei.gerenciador_eventos.dto.response.InscricaoResponseDTO;
import com.gei.gerenciador_eventos.entity.Evento;
import com.gei.gerenciador_eventos.entity.Inscricao;
import com.gei.gerenciador_eventos.entity.Usuario;
import com.gei.gerenciador_eventos.entity.enums.StatusInscricao;

import java.time.LocalDateTime;

public class InscricaoMapper {

    public static Inscricao toEntity(InscricaoRequestDTO dto, Evento evento, Usuario usuario) {
        Inscricao inscricao = new Inscricao();
        inscricao.setEvento(evento);
        inscricao.setUsuario(usuario);
        inscricao.setStatus(dto.getStatus());
        inscricao.setDataInscricao(LocalDateTime.now());
        return inscricao;
    }

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