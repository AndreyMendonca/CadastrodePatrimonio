package com.senai.backend.repositories;

import com.senai.backend.entities.PatrimonioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatrimonioRepository extends JpaRepository<PatrimonioEntity, Long> {
}
