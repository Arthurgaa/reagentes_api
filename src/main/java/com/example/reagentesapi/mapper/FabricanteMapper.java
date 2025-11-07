package com.example.reagentesapi.mapper;

import com.example.reagentesapi.dto.FabricanteDTO;
import com.example.reagentesapi.entity.Fabricante;

public class FabricanteMapper {

    public static Fabricante toEntity(FabricanteDTO dto) {
        if (dto == null) return null;

        Fabricante f = new Fabricante();
        f.setId(dto.getId());
        f.setNomeOficial(dto.getNomeOficial());
        f.setNomeFantasia(dto.getNomeFantasia());
        f.setCnpj(dto.getCnpj());
        f.setPaisOrigem(dto.getPaisOrigem());
        return f;
    }

    public static FabricanteDTO toDTO(Fabricante entity) {
        if (entity == null) return null;

        return new FabricanteDTO(
                entity.getId(),
                entity.getNomeOficial(),
                entity.getNomeFantasia(),
                entity.getCnpj(),
                entity.getPaisOrigem()
        );
    }
}
