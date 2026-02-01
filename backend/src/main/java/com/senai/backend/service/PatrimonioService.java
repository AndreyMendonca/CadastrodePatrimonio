package com.senai.backend.service;

import com.senai.backend.repositories.PatrimonioRepository;
import com.senai.backend.repositories.SetorRepository;
import org.springframework.stereotype.Service;

@Service
public class PatrimonioService {
    private final PatrimonioRepository patrimonioRepository;
    private final SetorRepository setorRepository;

    public PatrimonioService(PatrimonioRepository patrimonioRepository, SetorRepository setorRepository) {
        this.patrimonioRepository = patrimonioRepository;
        this.setorRepository = setorRepository;
    }
}
