package com.gei.gerenciador_eventos.repository;

import com.gei.gerenciador_eventos.entity.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {
    List<Inscricao> findByEventoId(Long eventoId);
    Optional<Inscricao> findByUsuarioIdAndEventoId(Long usuarioId, Long eventoId);
}