package com.gei.gerenciador_eventos.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InscricaoRequestDTO {

    @NotNull(message = "O ID do usuário é obrigatório.")
    private Long usuarioId;

    @NotNull(message = "O ID do evento é obrigatório.")
    private Long eventoId;
}