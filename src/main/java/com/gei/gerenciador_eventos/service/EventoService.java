package com.gei.gerenciador_eventos.service;

import com.gei.gerenciador_eventos.dto.request.EventoRequestDTO;
import com.gei.gerenciador_eventos.dto.response.EventoResponseDTO;
import com.gei.gerenciador_eventos.entity.Categoria;
import com.gei.gerenciador_eventos.entity.Evento;
import com.gei.gerenciador_eventos.entity.Local;
import com.gei.gerenciador_eventos.entity.Usuario;
import com.gei.gerenciador_eventos.entity.enums.Perfil;
import com.gei.gerenciador_eventos.mapper.EventoMapper;
import com.gei.gerenciador_eventos.repository.CategoriaRepository;
import com.gei.gerenciador_eventos.repository.EventoRepository;
import com.gei.gerenciador_eventos.repository.LocalRepository;
import com.gei.gerenciador_eventos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private LocalRepository localRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<EventoResponseDTO> findAll() {
        return eventoRepository.findAll().stream()
                .map(EventoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EventoResponseDTO findById(Long id) {
        return eventoRepository.findById(id)
                .map(EventoMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado."));
    }

    public EventoResponseDTO create(EventoRequestDTO requestDTO) {
        Usuario organizador = usuarioRepository.findById(requestDTO.getOrganizadorId())
                .orElseThrow(() -> new RuntimeException("Organizador não encontrado."));

        if (!organizador.getPerfil().equals(Perfil.ORGANIZADOR)) {
            throw new RuntimeException("Apenas organizadores podem criar eventos.");
        }

        Local local = localRepository.findById(requestDTO.getLocalId())
                .orElseThrow(() -> new RuntimeException("Local não encontrado."));
        Categoria categoria = categoriaRepository.findById(requestDTO.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));

        if(requestDTO.getLimiteVagas() > local.getCapacidade()){
            throw new RuntimeException("O limite de vagas não pode ser maior que a capacidade do local.");
        }

        Evento evento = EventoMapper.toEntity(requestDTO, organizador, local, categoria);
        evento = eventoRepository.save(evento);
        return EventoMapper.toDTO(evento);
    }

    public EventoResponseDTO update(Long id, EventoRequestDTO requestDTO) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado."));

        Usuario organizador = usuarioRepository.findById(requestDTO.getOrganizadorId())
                .orElseThrow(() -> new RuntimeException("Organizador não encontrado."));

        if (!organizador.getPerfil().equals(Perfil.ORGANIZADOR)) {
            throw new RuntimeException("Apenas organizadores podem criar eventos.");
        }

        Local local = localRepository.findById(requestDTO.getLocalId())
                .orElseThrow(() -> new RuntimeException("Local não encontrado."));
        Categoria categoria = categoriaRepository.findById(requestDTO.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));

        if(requestDTO.getLimiteVagas() > local.getCapacidade()){
            throw new RuntimeException("O limite de vagas não pode ser maior que a capacidade do local.");
        }

        evento.setNome(requestDTO.getNome());
        evento.setDescricao(requestDTO.getDescricao());
        evento.setData(requestDTO.getData());
        evento.setLimiteVagas(requestDTO.getLimiteVagas());
        evento.setOrganizador(organizador);
        evento.setLocal(local);
        evento.setCategoria(categoria);

        evento = eventoRepository.save(evento);
        return EventoMapper.toDTO(evento);
    }

    public void delete(Long id) {
        eventoRepository.deleteById(id);
    }

}