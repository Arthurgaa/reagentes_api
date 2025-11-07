package com.example.reagentesapi.mapper;

import com.example.reagentesapi.dto.ReagenteDTO;
import com.example.reagentesapi.entity.Fabricante;
import com.example.reagentesapi.entity.LocalizacaoEstoque;
import com.example.reagentesapi.entity.Reagente;

public class ReagenteMapper {

    public static Reagente toEntity(ReagenteDTO dto) {
        if (dto == null) return null;

        Reagente r = new Reagente();
        r.setId(dto.getId());
        r.setNome(dto.getNome());
        r.setCodigoSku(dto.getCodigoSku());
        r.setLote(dto.getLote());
        r.setDataValidade(dto.getDataValidade());
        r.setDataRecebimento(dto.getDataRecebimento());
        r.setQuantidadeEmEstoque(dto.getQuantidadeEmEstoque());
        r.setStatus(dto.getStatus());

        // Evita NullPointerException
        if (dto.getFabricanteId() != null) {
            Fabricante f = new Fabricante();
            f.setId(dto.getFabricanteId());
            r.setFabricante(f);
        }

        if (dto.getLocalizacaoId() != null) {
            LocalizacaoEstoque l = new LocalizacaoEstoque();
            l.setId(dto.getLocalizacaoId());
            r.setLocalizacaoEstoque(l);
        }

        return r;
    }

    public static ReagenteDTO toDTO(Reagente entity) {
        if (entity == null) return null;

        return new ReagenteDTO(
                entity.getId(),
                entity.getNome(),
                entity.getCodigoSku(),
                entity.getLote(),
                entity.getDataValidade(),
                entity.getDataRecebimento(),
                entity.getQuantidadeEmEstoque(),
                entity.getStatus(),
                entity.getFabricante() != null ? entity.getFabricante().getId() : null,
                entity.getLocalizacaoEstoque() != null ? entity.getLocalizacaoEstoque().getId() : null
        );
    }
}
