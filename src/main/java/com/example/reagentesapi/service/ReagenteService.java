package com.example.reagentesapi.service;

import com.example.reagentesapi.entity.Fabricante;
import com.example.reagentesapi.entity.LocalizacaoEstoque;
import com.example.reagentesapi.entity.Reagente;
import com.example.reagentesapi.enums.StatusReagente;
import com.example.reagentesapi.repository.FabricanteRepository;
import com.example.reagentesapi.repository.LocalizacaoRepository;
import com.example.reagentesapi.repository.ReagenteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReagenteService {

    private final ReagenteRepository reagenteRepository;
    private final FabricanteRepository fabricanteRepository;
    private final LocalizacaoRepository localizacaoRepository;

    public ReagenteService(ReagenteRepository reagenteRepository, FabricanteRepository fabricanteRepository, LocalizacaoRepository localizacaoRepository) {
        this.reagenteRepository = reagenteRepository;
        this.fabricanteRepository = fabricanteRepository;
        this.localizacaoRepository = localizacaoRepository;
    }

    public Reagente create(Reagente r) {
        if (r.getQuantidadeEmEstoque() == null || r.getQuantidadeEmEstoque() < 0) r.setQuantidadeEmEstoque(0);
        if (r.getDataValidade() != null && r.getDataValidade().isBefore(LocalDate.now())) {
            r.setStatus(StatusReagente.VENCIDO);
        } else if (r.getStatus() == null) {
            r.setStatus(StatusReagente.QUARENTENA);
        }
        if (r.getFabricante() != null && r.getFabricante().getId() != null) {
            Optional<Fabricante> f = fabricanteRepository.findById(r.getFabricante().getId());
            f.ifPresent(r::setFabricante);
        }
        if (r.getLocalizacaoEstoque() != null && r.getLocalizacaoEstoque().getId() != null) {
            Optional<LocalizacaoEstoque> l = localizacaoRepository.findById(r.getLocalizacaoEstoque().getId());
            l.ifPresent(r::setLocalizacaoEstoque);
        }
        return reagenteRepository.save(r);
    }

    public List<Reagente> listAll() {
        return reagenteRepository.findAll();
    }

    public Optional<Reagente> findById(UUID id) {
        return reagenteRepository.findById(id);
    }

    public Optional<Reagente> update(UUID id, Reagente updated) {
        return reagenteRepository.findById(id).map(existing -> {
            existing.setNome(updated.getNome());
            existing.setCodigoSku(updated.getCodigoSku());
            existing.setLote(updated.getLote());
            existing.setDataRecebimento(updated.getDataRecebimento());
            existing.setDataValidade(updated.getDataValidade());
            existing.setQuantidadeEmEstoque(updated.getQuantidadeEmEstoque());
            existing.setStatus(updated.getStatus());
            if (updated.getFabricante() != null && updated.getFabricante().getId() != null) {
                fabricanteRepository.findById(updated.getFabricante().getId()).ifPresent(existing::setFabricante);
            }
            if (updated.getLocalizacaoEstoque() != null && updated.getLocalizacaoEstoque().getId() != null) {
                localizacaoRepository.findById(updated.getLocalizacaoEstoque().getId()).ifPresent(existing::setLocalizacaoEstoque);
            }
            return reagenteRepository.save(existing);
        });
    }

    public boolean delete(UUID id) {
        if (reagenteRepository.existsById(id)) {
            reagenteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
