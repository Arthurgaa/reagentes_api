package com.example.reagentesapi.controller;

import com.example.reagentesapi.dto.MovimentacaoDTO;
import com.example.reagentesapi.entity.MovimentacaoEstoque;
import com.example.reagentesapi.entity.Reagente;
import com.example.reagentesapi.service.MovimentacaoService;
import com.example.reagentesapi.service.ReagenteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/movimentacoes")
public class MovimentacaoController {

    private final MovimentacaoService movService;
    private final ReagenteService reagenteService;

    public MovimentacaoController(MovimentacaoService movService, ReagenteService reagenteService) {
        this.movService = movService;
        this.reagenteService = reagenteService;
    }

    @PostMapping
    public ResponseEntity<MovimentacaoDTO> create(@RequestBody MovimentacaoDTO dto) {
        try {
            MovimentacaoEstoque mov = new MovimentacaoEstoque();
            mov.setDataHoraMovimentacao(dto.getDataHoraMovimentacao() != null ? dto.getDataHoraMovimentacao() : LocalDateTime.now());
            mov.setTipo(dto.getTipo());
            mov.setQuantidadeMovimentada(dto.getQuantidadeMovimentada());
            mov.setObservacao(dto.getObservacao());

            if (dto.getReagenteId() != null) {
                Reagente r = reagenteService.findById(dto.getReagenteId()).orElse(null);
                mov.setReagente(r);
            }

            MovimentacaoEstoque saved = movService.create(mov);

            MovimentacaoDTO resp = new MovimentacaoDTO(
                    saved.getId(),
                    saved.getDataHoraMovimentacao(),
                    saved.getTipo(),
                    saved.getQuantidadeMovimentada(),
                    saved.getObservacao(),
                    saved.getReagente() != null ? saved.getReagente().getId() : null
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(resp);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<MovimentacaoEstoque>> listAll() {
        return ResponseEntity.ok(movService.listAll());
    }
}
