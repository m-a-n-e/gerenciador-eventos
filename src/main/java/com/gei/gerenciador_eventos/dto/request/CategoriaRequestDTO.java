package com.gei.gerenciador_eventos.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaRequestDTO {
    @NotBlank(message = "O nome da categoria é obrigatório.")
    private String nome;
}