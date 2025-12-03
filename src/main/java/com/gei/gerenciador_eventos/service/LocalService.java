package com.gei.gerenciador_eventos.service;

import com.gei.gerenciador_eventos.dto.request.LocalRequestDTO;
import com.gei.gerenciador_eventos.dto.response.LocalResponseDTO;
import com.gei.gerenciador_eventos.entity.Local;
import com.gei.gerenciador_eventos.mapper.LocalMapper;
import com.gei.gerenciador_eventos.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocalService {

    @Autowired
    private LocalRepository repository;

    public LocalResponseDTO findById(Long id) {
        return repository.findById(id)
                .map(LocalMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Local não encontrado."));
    }

    public List<LocalResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(LocalMapper::toDTO)
                .collect(Collectors.toList());
    }

    public LocalResponseDTO create(LocalRequestDTO requestDTO) {
        Local local = LocalMapper.toEntity(requestDTO);
        local = repository.save(local);
        return LocalMapper.toDTO(local);
    }

    public LocalResponseDTO update(Long id, LocalRequestDTO requestDTO) {
        Local local = repository.findById(id).orElseThrow(() -> new RuntimeException("Local não encontrado."));
        local.setNome(requestDTO.getNome());
        local.setEndereco(requestDTO.getEndereco());
        local.setCapacidade(requestDTO.getCapacidade());
        local = repository.save(local);
        return LocalMapper.toDTO(local);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}