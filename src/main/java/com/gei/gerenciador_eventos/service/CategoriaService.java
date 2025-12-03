package com.gei.gerenciador_eventos.service;

import com.gei.gerenciador_eventos.dto.request.CategoriaRequestDTO;
import com.gei.gerenciador_eventos.dto.response.CategoriaResponseDTO;
import com.gei.gerenciador_eventos.entity.Categoria;
import com.gei.gerenciador_eventos.mapper.CategoriaMapper;
import com.gei.gerenciador_eventos.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public CategoriaResponseDTO findById(Long id) {
        return repository.findById(id)
                .map(CategoriaMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));
    }

    public List<CategoriaResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(CategoriaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CategoriaResponseDTO create(CategoriaRequestDTO requestDTO) {
        Categoria categoria = CategoriaMapper.toEntity(requestDTO);
        categoria = repository.save(categoria);
        return CategoriaMapper.toDTO(categoria);
    }
}