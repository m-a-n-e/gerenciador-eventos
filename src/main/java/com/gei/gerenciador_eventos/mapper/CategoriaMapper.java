package com.gei.gerenciador_eventos.mapper;

import com.gei.gerenciador_eventos.dto.request.CategoriaRequestDTO;
import com.gei.gerenciador_eventos.dto.response.CategoriaResponseDTO;
import com.gei.gerenciador_eventos.entity.Categoria;

public class CategoriaMapper {

    public static Categoria toEntity(CategoriaRequestDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());
        return categoria;
    }

    public static CategoriaResponseDTO toDTO(Categoria categoria) {
        CategoriaResponseDTO dto = new CategoriaResponseDTO();
        dto.setId(categoria.getId());
        dto.setNome(categoria.getNome());
        return dto;
    }
}