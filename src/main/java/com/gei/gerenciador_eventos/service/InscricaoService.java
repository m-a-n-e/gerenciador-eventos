package com.gei.gerenciador_eventos.service;

import com.gei.gerenciador_eventos.dto.request.InscricaoRequestDTO;
import com.gei.gerenciador_eventos.dto.response.InscricaoResponseDTO;
import com.gei.gerenciador_eventos.entity.Evento;
import com.gei.gerenciador_eventos.entity.Inscricao;
import com.gei.gerenciador_eventos.entity.Usuario;
import com.gei.gerenciador_eventos.entity.enums.StatusInscricao;
import com.gei.gerenciador_eventos.mapper.InscricaoMapper;
import com.gei.gerenciador_eventos.repository.EventoRepository;
import com.gei.gerenciador_eventos.repository.InscricaoRepository;
import com.gei.gerenciador_eventos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InscricaoService {

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<InscricaoResponseDTO> findByEventoId(Long eventoId) {
        return inscricaoRepository.findByEventoId(eventoId).stream()
                .map(InscricaoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public InscricaoResponseDTO create(InscricaoRequestDTO requestDTO) {
        Usuario usuario = usuarioRepository.findById(requestDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        Evento evento = eventoRepository.findById(requestDTO.getEventoId())
                .orElseThrow(() -> new RuntimeException("Evento não encontrado."));

        // REGRA CRÍTICA
        Long vagasOcupadas = eventoRepository.countInscricoesConfirmadasByEventoId(evento.getId());
        if (vagasOcupadas >= evento.getLimiteVagas()) {
            throw new RuntimeException("Não há vagas disponíveis para este evento.");
        }

        Inscricao inscricao = new Inscricao();
        inscricao.setUsuario(usuario);
        inscricao.setEvento(evento);
        inscricao.setDataInscricao(LocalDateTime.now());
        inscricao.setStatus(StatusInscricao.CONFIRMADA);

        inscricao = inscricaoRepository.save(inscricao);

        return InscricaoMapper.toDTO(inscricao);
    }
}