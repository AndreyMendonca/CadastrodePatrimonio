package com.senai.backend.service;

import com.senai.backend.controllers.dtos.SetorDtoCreate;
import com.senai.backend.controllers.dtos.SetorDtoUpdate;
import com.senai.backend.entities.SetorEntity;
import com.senai.backend.exceptions.EntityNotFoundException;
import com.senai.backend.repositories.SetorRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SetorService {
    private final SetorRepository setorRepository;

    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    public SetorEntity save(SetorDtoCreate dto) {
        SetorEntity setor = new SetorEntity();
        setor.setNome(dto.nome());
        return setorRepository.save(setor);
    }

    public SetorEntity findById(Long id) {
        return setorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Setor não encontrado"));
    }

    public void update(Long id, @Valid SetorDtoUpdate dto) {
        var setor = this.findById(id);
        setor.setNome(dto.nome());
        setorRepository.save(setor);
    }

    public void delete(Long id) {
        this.findById(id);
        setorRepository.deleteById(id);
    }

    public List<SetorEntity> findAll() {
        return setorRepository.findAll();
    }
}
