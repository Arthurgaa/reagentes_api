package com.example.reagentesapi.service;

import com.example.reagentesapi.entity.MovimentacaoEstoque;
import com.example.reagentesapi.entity.Reagente;
import com.example.reagentesapi.enums.TipoMovimentacao;
import com.example.reagentesapi.repository.MovimentacaoRepository;
import com.example.reagentesapi.repository.ReagenteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovimentacaoService {
    private final MovimentacaoRepository movRepo;
    private final ReagenteRepository reagenteRepository;

    public MovimentacaoService(MovimentacaoRepository movRepo, ReagenteRepository reagenteRepository) {
        this.movRepo = movRepo;
        this.reagenteRepository = reagenteRepository;
    }

    public MovimentacaoEstoque create(MovimentacaoEstoque m) {
        if (m.getReagente() != null && m.getReagente().getId() != null) {
            Optional<Reagente> rOpt = reagenteRepository.findById(m.getReagente().getId());
            if (rOpt.isPresent()) {
                Reagente r = rOpt.get();
                int current = r.getQuantidadeEmEstoque() != null ? r.getQuantidadeEmEstoque() : 0;
                int delta = m.getQuantidadeMovimentada() != null ? m.getQuantidadeMovimentada() : 0;
                int updated = current + delta;
                r.setQuantidadeEmEstoque(Math.max(0, updated));
                if (m.getTipo() == TipoMovimentacao.SAIDA_DESCARTE_VENCIMENTO) {
                    r.setStatus(com.example.reagentesapi.enums.StatusReagente.VENCIDO);
                }
                reagenteRepository.save(r);
                m.setReagente(r);
            }
        }
        return movRepo.save(m);
    }

    public List<MovimentacaoEstoque> listAll() { return movRepo.findAll(); }
    public Optional<MovimentacaoEstoque> findById(UUID id) { return movRepo.findById(id); }
}
