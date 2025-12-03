package com.gei.gerenciador_eventos.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalRequestDTO {

    @NotBlank(message = "O nome do local é obrigatório.")
    private String nome;

    @NotBlank(message = "O endereço é obrigatório.")
    private String endereco;

    @NotNull(message = "A capacidade é obrigatória.")
    @Min(value = 1, message = "A capacidade deve ser de no mínimo 1.")
    private Integer capacidade;
}