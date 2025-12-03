package com.gei.gerenciador_eventos.resource;

import com.gei.gerenciador_eventos.dto.request.LocalRequestDTO;
import com.gei.gerenciador_eventos.dto.response.LocalResponseDTO;
import com.gei.gerenciador_eventos.service.LocalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/locais")
public class LocalResource {

    @Autowired
    private LocalService service;

    @GetMapping
    public ResponseEntity<List<LocalResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<LocalResponseDTO> create(@Valid @RequestBody LocalRequestDTO requestDTO) {
        LocalResponseDTO responseDTO = service.create(requestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(responseDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(responseDTO);
    }
}