package com.example.reagentesapi.mapper;

import com.example.reagentesapi.dto.LocalizacaoDTO;
import com.example.reagentesapi.entity.LocalizacaoEstoque;

public class LocalizacaoMapper {

    public static LocalizacaoEstoque toEntity(LocalizacaoDTO dto) {
        if (dto == null) return null;

        LocalizacaoEstoque l = new LocalizacaoEstoque();
        l.setId(dto.getId());
        l.setCodigoLocal(dto.getCodigoLocal());
        l.setDescricao(dto.getDescricao());
        l.setTipo(dto.getTipo());
        l.setFaixaTemperaturaNominal(dto.getFaixaTemperaturaNominal());
        l.setSetor(dto.getSetor());
        return l;
    }

    public static LocalizacaoDTO toDTO(LocalizacaoEstoque entity) {
        if (entity == null) return null;

        return new LocalizacaoDTO(
                entity.getId(),
                entity.getCodigoLocal(),
                entity.getDescricao(),
                entity.getTipo(),
                entity.getFaixaTemperaturaNominal(),
                entity.getSetor()
        );
    }
}
