package com.senai.backend.service;

import com.senai.backend.repositories.SetorRepository;
import org.springframework.stereotype.Service;

@Service
public class SetorService {
    private final SetorRepository setorRepository;

    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

}
