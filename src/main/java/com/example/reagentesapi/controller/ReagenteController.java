package com.example.reagentesapi.controller;

import com.example.reagentesapi.dto.ReagenteDTO;
import com.example.reagentesapi.entity.Reagente;
import com.example.reagentesapi.mapper.ReagenteMapper;
import com.example.reagentesapi.service.ReagenteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/reagentes")
public class ReagenteController {

    private final ReagenteService service;

    public ReagenteController(ReagenteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ReagenteDTO dto) {
        try {
            Reagente toSave = ReagenteMapper.toEntity(dto);
            Reagente saved = service.create(toSave);
            return ResponseEntity.status(HttpStatus.CREATED).body(ReagenteMapper.toDTO(saved));
        } catch (Exception e) {
            e.printStackTrace();
            String msg = (e.getMessage() != null && !e.getMessage().isBlank())
                    ? e.getMessage()
                    : e.getClass().getSimpleName();
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(org.springframework.http.MediaType.TEXT_PLAIN)
                    .body("Falha ao criar reagente: " + msg);
        }
    }

    @GetMapping
    public ResponseEntity<List<ReagenteDTO>> listAll() {
        List<ReagenteDTO> list = service.listAll()
                .stream()
                .map(ReagenteMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReagenteDTO> getById(@PathVariable UUID id) {
        return service.findById(id)
                .map(r -> ResponseEntity.ok(ReagenteMapper.toDTO(r)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReagenteDTO> update(@PathVariable UUID id, @RequestBody ReagenteDTO dto) {
        Reagente ent = ReagenteMapper.toEntity(dto);
        return service.update(id, ent)
                .map(r -> ResponseEntity.ok(ReagenteMapper.toDTO(r)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        boolean deleted = service.delete(id);
        if (deleted) return ResponseEntity.noContent().build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
