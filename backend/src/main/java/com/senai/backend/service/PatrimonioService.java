package com.senai.backend.service;

import com.senai.backend.controllers.dtos.PatrimonioDtoCreate;
import com.senai.backend.controllers.dtos.PatrimonioDtoUpdate;
import com.senai.backend.entities.PatrimonioEntity;
import com.senai.backend.exceptions.EntityNotFoundException;
import com.senai.backend.repositories.PatrimonioRepository;
import com.senai.backend.repositories.SetorRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatrimonioService {
    private final PatrimonioRepository patrimonioRepository;
    private final SetorRepository setorRepository;

    public PatrimonioService(PatrimonioRepository patrimonioRepository, SetorRepository setorRepository) {
        this.patrimonioRepository = patrimonioRepository;
        this.setorRepository = setorRepository;
    }

    public PatrimonioEntity save(@Valid PatrimonioDtoCreate dto) {
        var setor = setorRepository.findById(dto.setor())
                .orElseThrow(()-> new EntityNotFoundException("Setor não encontrado"));

        PatrimonioEntity patrimonio = new PatrimonioEntity();

        patrimonio.setNome(dto.nome());
        patrimonio.setNumeroPatrimonio(dto.numeroPatrimonio());
        patrimonio.setSetor(setor);

        return patrimonioRepository.save(patrimonio);
    }

    public PatrimonioEntity findById(Long id) {
        return patrimonioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patrimônio não encontrado"));
    }

    public List<PatrimonioEntity> findAll() {
        return patrimonioRepository.findAll();
    }

    public void update(Long id, @Valid PatrimonioDtoUpdate dto) {
        var patrimonio = this.findById(id);
        var setor = setorRepository.findById(dto.setor())
                .orElseThrow(()-> new EntityNotFoundException("Setor não encontrado"));

        patrimonio.setNome(dto.nome());
        patrimonio.setNumeroPatrimonio(dto.numeroPatrimonio());
        patrimonio.setSetor(setor);

        patrimonioRepository.save(patrimonio);
    }

    public void delete(Long id) {
        this.findById(id);
        patrimonioRepository.deleteById(id);
    }

}
