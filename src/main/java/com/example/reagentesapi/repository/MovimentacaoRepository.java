package com.example.reagentesapi.repository;

import com.example.reagentesapi.entity.MovimentacaoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovimentacaoRepository extends JpaRepository<MovimentacaoEstoque, UUID> {
}
