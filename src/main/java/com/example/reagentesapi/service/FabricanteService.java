package com.example.reagentesapi.service;

import com.example.reagentesapi.entity.Fabricante;
import com.example.reagentesapi.repository.FabricanteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FabricanteService {
    private final FabricanteRepository repo;
    public FabricanteService(FabricanteRepository repo) { this.repo = repo; }
    public Fabricante create(Fabricante f) { return repo.save(f); }
    public List<Fabricante> listAll() { return repo.findAll(); }
    public Optional<Fabricante> findById(UUID id) { return repo.findById(id); }
    public Optional<Fabricante> update(UUID id, Fabricante updated) {
        return repo.findById(id).map(existing -> {
            existing.setNomeOficial(updated.getNomeOficial());
            existing.setNomeFantasia(updated.getNomeFantasia());
            existing.setCnpj(updated.getCnpj());
            existing.setPaisOrigem(updated.getPaisOrigem());
            return repo.save(existing);
        });
    }
    public boolean delete(UUID id) {
        if (repo.existsById(id)) { repo.deleteById(id); return true; } return false;
    }
}
