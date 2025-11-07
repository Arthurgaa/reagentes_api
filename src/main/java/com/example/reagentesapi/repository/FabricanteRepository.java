package com.example.reagentesapi.repository;

import com.example.reagentesapi.entity.Fabricante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FabricanteRepository extends JpaRepository<Fabricante, UUID> {
}
