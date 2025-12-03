package com.gei.gerenciador_eventos.service;

import com.gei.gerenciador_eventos.dto.request.UsuarioRequestDTO;
import com.gei.gerenciador_eventos.dto.response.UsuarioResponseDTO;
import com.gei.gerenciador_eventos.entity.Usuario;
import com.gei.gerenciador_eventos.mapper.UsuarioMapper;
import com.gei.gerenciador_eventos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioResponseDTO create(UsuarioRequestDTO requestDTO) {
        repository.findByEmail(requestDTO.getEmail()).ifPresent(u -> {
            throw new RuntimeException("Email já cadastrado.");
        });

        Usuario usuario = UsuarioMapper.toEntity(requestDTO);
        // Em um app real, aqui ocorreria a criptografia da senha antes de salvar
        // usuario.setSenha(passwordEncoder.encode(requestDTO.getSenha()));
        usuario = repository.save(usuario);
        return UsuarioMapper.toDTO(usuario);
    }
}