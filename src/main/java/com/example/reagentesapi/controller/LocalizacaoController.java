package com.example.reagentesapi.controller;

import com.example.reagentesapi.dto.LocalizacaoDTO;
import com.example.reagentesapi.entity.LocalizacaoEstoque;
import com.example.reagentesapi.mapper.LocalizacaoMapper;
import com.example.reagentesapi.service.LocalizacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/localizacoes")
public class LocalizacaoController {
    private final LocalizacaoService service;
    public LocalizacaoController(LocalizacaoService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<LocalizacaoDTO> create(@RequestBody LocalizacaoDTO dto) {
        LocalizacaoEstoque saved = service.create(LocalizacaoMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(LocalizacaoMapper.toDTO(saved));
    }

    @GetMapping
    public ResponseEntity<List<LocalizacaoDTO>> listAll() {
        return ResponseEntity.ok(service.listAll().stream().map(LocalizacaoMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalizacaoDTO> getById(@PathVariable UUID id) {
        return service.findById(id).map(l -> ResponseEntity.ok(LocalizacaoMapper.toDTO(l))).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocalizacaoDTO> update(@PathVariable UUID id, @RequestBody LocalizacaoDTO dto) {
        return service.update(id, LocalizacaoMapper.toEntity(dto)).map(l -> ResponseEntity.ok(LocalizacaoMapper.toDTO(l))).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (service.delete(id)) return ResponseEntity.noContent().build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
