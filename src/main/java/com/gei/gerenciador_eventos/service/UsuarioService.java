package com.gei.gerenciador_eventos.service;

import com.gei.gerenciador_eventos.dto.request.UsuarioRequestDTO;
import com.gei.gerenciador_eventos.dto.response.UsuarioResponseDTO;
import com.gei.gerenciador_eventos.entity.Usuario;
import com.gei.gerenciador_eventos.mapper.UsuarioMapper;
import com.gei.gerenciador_eventos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<UsuarioResponseDTO> findAll() {
        return repository.findAll().stream().map(UsuarioMapper::toDTO).collect(Collectors.toList());
    }

    public UsuarioResponseDTO findById(Long id) {
        return repository.findById(id).map(UsuarioMapper::toDTO).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }

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

    public UsuarioResponseDTO update(Long id, UsuarioRequestDTO requestDTO) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        usuario.setNome(requestDTO.getNome());
        usuario.setEmail(requestDTO.getEmail());
        usuario.setSenha(requestDTO.getSenha());
        usuario.setPerfil(requestDTO.getPerfil());
        usuario = repository.save(usuario);
        return UsuarioMapper.toDTO(usuario);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}