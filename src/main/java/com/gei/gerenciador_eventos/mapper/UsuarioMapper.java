package com.gei.gerenciador_eventos.mapper;

import com.gei.gerenciador_eventos.dto.request.UsuarioRequestDTO;
import com.gei.gerenciador_eventos.dto.response.UsuarioResponseDTO;
import com.gei.gerenciador_eventos.entity.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha()); // Em um projeto real, a senha seria criptografada aqui
        usuario.setPerfil(dto.getPerfil());
        return usuario;
    }

    public static UsuarioResponseDTO toDTO(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setPerfil(usuario.getPerfil());
        return dto;
    }
}