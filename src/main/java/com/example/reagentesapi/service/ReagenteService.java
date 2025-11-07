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

    // ========= PATCH: método create mais tolerante =========
    public Reagente create(Reagente r) {
        // Garante ID na criação
        if (r.getId() == null) {
            r.setId(UUID.randomUUID());
        }

        // Higieniza campos obrigatórios
        r.setNome(r.getNome() == null ? "" : r.getNome().trim());
        r.setCodigoSku(r.getCodigoSku() == null ? "" : r.getCodigoSku().trim());
        r.setLote(r.getLote() == null ? "" : r.getLote().trim());

        if (r.getNome().isEmpty())      throw new IllegalArgumentException("nome é obrigatório");
        if (r.getCodigoSku().isEmpty()) throw new IllegalArgumentException("codigoSku é obrigatório");
        if (r.getLote().isEmpty())      throw new IllegalArgumentException("lote é obrigatório");

        // Datas: se dataRecebimento vier nula, usa hoje
        if (r.getDataRecebimento() == null) {
            r.setDataRecebimento(LocalDate.now());
        }
        if (r.getDataValidade() == null) {
            throw new IllegalArgumentException("dataValidade é obrigatória");
        }

        // Quantidade: default 0 e nunca negativa
        if (r.getQuantidadeEmEstoque() == null || r.getQuantidadeEmEstoque() < 0) {
            r.setQuantidadeEmEstoque(0);
        }

        // Status: regras simples e tolerantes
        if (r.getDataValidade().isBefore(LocalDate.now())) {
            r.setStatus(StatusReagente.VENCIDO);
        } else if (r.getStatus() == null) {
            r.setStatus(StatusReagente.QUARENTENA);
        }

        // Resolver Fabricante somente se vier ID válido
        if (r.getFabricante() != null && r.getFabricante().getId() != null) {
            fabricanteRepository.findById(r.getFabricante().getId())
                    .ifPresentOrElse(r::setFabricante, () -> r.setFabricante(null));
        } else {
            r.setFabricante(null);
        }

        // Resolver Localização somente se vier ID válido
        if (r.getLocalizacaoEstoque() != null && r.getLocalizacaoEstoque().getId() != null) {
            localizacaoRepository.findById(r.getLocalizacaoEstoque().getId())
                    .ifPresentOrElse(r::setLocalizacaoEstoque, () -> r.setLocalizacaoEstoque(null));
        } else {
            r.setLocalizacaoEstoque(null);
        }

        return reagenteRepository.save(r);
    }
    // ========= FIM PATCH =========

    public List<Reagente> listAll() {
        return reagenteRepository.findAll();
    }

    public Optional<Reagente> findById(UUID id) {
        return reagenteRepository.findById(id);
    }

    public Optional<Reagente> update(UUID id, Reagente updated) {
        return reagenteRepository.findById(id).map(existing -> {
            // NUNCA mexe no ID aqui
            existing.setNome(updated.getNome());
            existing.setCodigoSku(updated.getCodigoSku());
            existing.setLote(updated.getLote());
            existing.setDataRecebimento(updated.getDataRecebimento());
            existing.setDataValidade(updated.getDataValidade());

            Integer qtd = updated.getQuantidadeEmEstoque();
            existing.setQuantidadeEmEstoque(qtd == null || qtd < 0 ? 0 : qtd);

            // Mantém regra de status semelhante à criação
            if (updated.getDataValidade() != null && updated.getDataValidade().isBefore(LocalDate.now())) {
                existing.setStatus(StatusReagente.VENCIDO);
            } else {
                existing.setStatus(updated.getStatus() != null ? updated.getStatus() : existing.getStatus());
            }

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
