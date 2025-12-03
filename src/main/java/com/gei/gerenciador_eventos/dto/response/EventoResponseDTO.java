package com.gei.gerenciador_eventos.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventoResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private LocalDateTime data;
    private Integer limiteVagas;
    private UsuarioResponseDTO organizador;
    private LocalResponseDTO local;
    private CategoriaResponseDTO categoria;
}