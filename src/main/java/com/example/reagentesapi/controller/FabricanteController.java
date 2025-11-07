package com.example.reagentesapi.controller;

import com.example.reagentesapi.dto.FabricanteDTO;
import com.example.reagentesapi.entity.Fabricante;
import com.example.reagentesapi.mapper.FabricanteMapper;
import com.example.reagentesapi.service.FabricanteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/fabricantes")
public class FabricanteController {
    private final FabricanteService service;
    public FabricanteController(FabricanteService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<FabricanteDTO> create(@RequestBody FabricanteDTO dto) {
        Fabricante saved = service.create(FabricanteMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(FabricanteMapper.toDTO(saved));
    }

    @GetMapping
    public ResponseEntity<List<FabricanteDTO>> listAll() {
        return ResponseEntity.ok(service.listAll().stream().map(FabricanteMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FabricanteDTO> getById(@PathVariable UUID id) {
        return service.findById(id).map(f -> ResponseEntity.ok(FabricanteMapper.toDTO(f))).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FabricanteDTO> update(@PathVariable UUID id, @RequestBody FabricanteDTO dto) {
        return service.update(id, FabricanteMapper.toEntity(dto)).map(f -> ResponseEntity.ok(FabricanteMapper.toDTO(f))).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (service.delete(id)) return ResponseEntity.noContent().build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
