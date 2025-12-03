package com.gei.gerenciador_eventos.dto.response;

import com.gei.gerenciador_eventos.entity.enums.StatusInscricao;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InscricaoResponseDTO {
    private Long id;
    private LocalDateTime dataInscricao;
    private StatusInscricao status;
    private UsuarioResponseDTO usuario;
    private EventoResponseDTO evento;
}