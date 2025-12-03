package com.gei.gerenciador_eventos.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalResponseDTO {
    private Long id;
    private String nome;
    private String endereco;
    private Integer capacidade;
}