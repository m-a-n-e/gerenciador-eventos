package com.gei.gerenciador_eventos.entity;

import com.gei.gerenciador_eventos.entity.enums.Perfil;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String senha;

    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    @OneToMany(mappedBy = "organizador")
    private List<Evento> eventosOrganizados;

    @OneToMany(mappedBy = "usuario")
    private List<Inscricao> inscricoes;
}