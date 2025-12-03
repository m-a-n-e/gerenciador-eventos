package com.gei.gerenciador_eventos.mapper;

import com.gei.gerenciador_eventos.dto.request.EventoRequestDTO;
import com.gei.gerenciador_eventos.dto.response.EventoResponseDTO;
import com.gei.gerenciador_eventos.entity.Categoria;
import com.gei.gerenciador_eventos.entity.Evento;
import com.gei.gerenciador_eventos.entity.Local;
import com.gei.gerenciador_eventos.entity.Usuario;

public class EventoMapper {

    public static Evento toEntity(EventoRequestDTO dto, Usuario organizador, Local local, Categoria categoria) {
        Evento evento = new Evento();
        evento.setNome(dto.getNome());
        evento.setDescricao(dto.getDescricao());
        evento.setData(dto.getData());
        evento.setLimiteVagas(dto.getLimiteVagas());
        evento.setOrganizador(organizador);
        evento.setLocal(local);
        evento.setCategoria(categoria);
        return evento;
    }

    public static EventoResponseDTO toDTO(Evento evento) {
        EventoResponseDTO dto = new EventoResponseDTO();
        dto.setId(evento.getId());
        dto.setNome(evento.getNome());
        dto.setDescricao(evento.getDescricao());
        dto.setData(evento.getData());
        dto.setLimiteVagas(evento.getLimiteVagas());
        if (evento.getOrganizador() != null) {
            dto.setOrganizador(UsuarioMapper.toDTO(evento.getOrganizador()));
        }
        if (evento.getLocal() != null) {
            dto.setLocal(LocalMapper.toDTO(evento.getLocal()));
        }
        if (evento.getCategoria() != null) {
            dto.setCategoria(CategoriaMapper.toDTO(evento.getCategoria()));
        }
        return dto;
    }
}