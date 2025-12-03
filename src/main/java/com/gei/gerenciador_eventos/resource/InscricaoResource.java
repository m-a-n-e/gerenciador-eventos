package com.gei.gerenciador_eventos.resource;

import com.gei.gerenciador_eventos.dto.request.InscricaoRequestDTO;
import com.gei.gerenciador_eventos.dto.response.InscricaoResponseDTO;
import com.gei.gerenciador_eventos.service.InscricaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/inscricoes")
public class InscricaoResource {

    @Autowired
    private InscricaoService service;

    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<List<InscricaoResponseDTO>> findByEventoId(@PathVariable Long eventoId) {
        return ResponseEntity.ok(service.findByEventoId(eventoId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscricaoResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<InscricaoResponseDTO> create(@Valid @RequestBody InscricaoRequestDTO requestDTO) {
        InscricaoResponseDTO responseDTO = service.create(requestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(responseDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InscricaoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody InscricaoRequestDTO requestDTO){
        return ResponseEntity.ok(service.update(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}