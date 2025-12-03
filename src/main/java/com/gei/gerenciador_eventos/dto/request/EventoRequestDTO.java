package com.gei.gerenciador_eventos.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventoRequestDTO {

    @NotBlank(message = "O nome do evento é obrigatório.")
    private String nome;

    private String descricao;

    @NotNull(message = "A data do evento é obrigatória.")
    @Future(message = "A data do evento deve ser futura.")
    private LocalDateTime data;

    @NotNull(message = "O limite de vagas é obrigatório.")
    @Min(value = 1, message = "O limite de vagas deve ser de no mínimo 1.")
    private Integer limiteVagas;

    @NotNull(message = "O ID do organizador é obrigatório.")
    private Long organizadorId;

    @NotNull(message = "O ID do local é obrigatório.")
    private Long localId;

    @NotNull(message = "O ID da categoria é obrigatório.")
    private Long categoriaId;
}