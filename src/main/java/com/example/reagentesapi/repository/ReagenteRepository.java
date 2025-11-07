package com.example.reagentesapi.repository;

import com.example.reagentesapi.entity.Reagente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReagenteRepository extends JpaRepository<Reagente, UUID> {
}
