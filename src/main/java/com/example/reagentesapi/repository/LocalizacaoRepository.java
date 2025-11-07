package com.example.reagentesapi.repository;

import com.example.reagentesapi.entity.LocalizacaoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LocalizacaoRepository extends JpaRepository<LocalizacaoEstoque, UUID> {
}
