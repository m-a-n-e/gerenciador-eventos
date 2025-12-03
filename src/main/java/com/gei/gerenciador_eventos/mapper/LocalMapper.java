package com.gei.gerenciador_eventos.mapper;

import com.gei.gerenciador_eventos.dto.request.LocalRequestDTO;
import com.gei.gerenciador_eventos.dto.response.LocalResponseDTO;
import com.gei.gerenciador_eventos.entity.Local;

public class LocalMapper {

    public static Local toEntity(LocalRequestDTO dto) {
        Local local = new Local();
        local.setNome(dto.getNome());
        local.setEndereco(dto.getEndereco());
        local.setCapacidade(dto.getCapacidade());
        return local;
    }

    public static LocalResponseDTO toDTO(Local local) {
        LocalResponseDTO dto = new LocalResponseDTO();
        dto.setId(local.getId());
        dto.setNome(local.getNome());
        dto.setEndereco(local.getEndereco());
        dto.setCapacidade(local.getCapacidade());
        return dto;
    }
}