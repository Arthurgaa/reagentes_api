package com.example.reagentesapi.service;

import com.example.reagentesapi.entity.LocalizacaoEstoque;
import com.example.reagentesapi.repository.LocalizacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LocalizacaoService {
    private final LocalizacaoRepository repo;
    public LocalizacaoService(LocalizacaoRepository repo) { this.repo = repo; }
    public LocalizacaoEstoque create(LocalizacaoEstoque l) { return repo.save(l); }
    public List<LocalizacaoEstoque> listAll() { return repo.findAll(); }
    public Optional<LocalizacaoEstoque> findById(UUID id) { return repo.findById(id); }
    public Optional<LocalizacaoEstoque> update(UUID id, LocalizacaoEstoque updated) {
        return repo.findById(id).map(existing -> {
            existing.setCodigoLocal(updated.getCodigoLocal());
            existing.setDescricao(updated.getDescricao());
            existing.setTipo(updated.getTipo());
            existing.setFaixaTemperaturaNominal(updated.getFaixaTemperaturaNominal());
            existing.setSetor(updated.getSetor());
            return repo.save(existing);
        });
    }
    public boolean delete(UUID id) { if (repo.existsById(id)) { repo.deleteById(id); return true; } return false; }
}
