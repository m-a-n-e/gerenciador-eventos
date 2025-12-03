package com.gei.gerenciador_eventos.dto.request;

import com.gei.gerenciador_eventos.entity.enums.StatusInscricao;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InscricaoUpdateDTO {

    @NotNull(message = "O status da inscrição é obrigatório.")
    private StatusInscricao status;
}
