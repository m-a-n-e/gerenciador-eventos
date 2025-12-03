package com.gei.gerenciador_eventos.dto.response;

import com.gei.gerenciador_eventos.entity.enums.Perfil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private Perfil perfil;
}