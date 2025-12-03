package com.gei.gerenciador_eventos.repository;

import com.gei.gerenciador_eventos.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    @Query("SELECT COUNT(i) FROM Inscricao i WHERE i.evento.id = :eventoId AND i.status = 'CONFIRMADA'")
    Long countInscricoesConfirmadasByEventoId(Long eventoId);

}