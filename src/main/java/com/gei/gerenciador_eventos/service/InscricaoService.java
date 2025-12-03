package com.gei.gerenciador_eventos.service;

import com.gei.gerenciador_eventos.dto.request.InscricaoRequestDTO;
import com.gei.gerenciador_eventos.dto.request.InscricaoUpdateDTO;
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

    

        public List<InscricaoResponseDTO> findAll() {

            return inscricaoRepository.findAll().stream()

                    .map(InscricaoMapper::toDTO)

                    .collect(Collectors.toList());

        }

    

        public List<InscricaoResponseDTO> findByEventoId(Long eventoId) {

            return inscricaoRepository.findByEventoId(eventoId).stream()

                    .map(InscricaoMapper::toDTO)

                    .collect(Collectors.toList());

        }

    public InscricaoResponseDTO findById(Long id) {
        return inscricaoRepository.findById(id)
                .map(InscricaoMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Inscrição não encontrada."));
    }

    public InscricaoResponseDTO create(InscricaoRequestDTO requestDTO) {
        inscricaoRepository.findByUsuarioIdAndEventoId(requestDTO.getUsuarioId(), requestDTO.getEventoId()).ifPresent(inscricao -> {
            throw new RuntimeException("Este usuário já está inscrito neste evento.");
        });

        Evento evento = eventoRepository.findById(requestDTO.getEventoId())
                .orElseThrow(() -> new RuntimeException("Evento não encontrado."));
        Usuario usuario = usuarioRepository.findById(requestDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        if (evento.getInscricoes().size() >= evento.getLimiteVagas()) {
            throw new RuntimeException("O evento já atingiu o limite de vagas.");
        }

        Inscricao inscricao = InscricaoMapper.toEntity(requestDTO, evento, usuario);
        inscricao.setStatus(StatusInscricao.PENDENTE);
        inscricao = inscricaoRepository.save(inscricao);
        return InscricaoMapper.toDTO(inscricao);
    }

    @Transactional
    public InscricaoResponseDTO updateStatus(Long id, InscricaoUpdateDTO requestDTO) {
        Inscricao inscricao = inscricaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscrição não encontrada."));

        inscricao.setStatus(requestDTO.getStatus());

        inscricao = inscricaoRepository.save(inscricao);

        return InscricaoMapper.toDTO(inscricao);
    }

    public void delete(Long id) {
        inscricaoRepository.deleteById(id);
    }
}