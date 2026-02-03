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

/*
 * Regras de negócio - Patrimonio
 * */
@Service
public class PatrimonioService {
    /*
     Dependencias que o service tem.
     * */
    private final PatrimonioRepository patrimonioRepository;
    private final SetorRepository setorRepository;

    public PatrimonioService(PatrimonioRepository patrimonioRepository, SetorRepository setorRepository) {
        this.patrimonioRepository = patrimonioRepository;
        this.setorRepository = setorRepository;
    }

    /*
     * Recurso para salvar o patrimonio
     * Como o patrimonio é vinculado a um setor, é feito uma busca para validar se o setor existe
     * Caso o setor não exista, a operação é finalizada retornando um erro
     * */
    public PatrimonioEntity save(@Valid PatrimonioDtoCreate dto) {
        var setor = setorRepository.findById(dto.setor())
                .orElseThrow(()-> new EntityNotFoundException("Setor não encontrado"));

        PatrimonioEntity patrimonio = new PatrimonioEntity();

        patrimonio.setNome(dto.nome());
        patrimonio.setNumeroPatrimonio(dto.numeroPatrimonio());
        patrimonio.setSetor(setor);

        return patrimonioRepository.save(patrimonio);
    }

    /*
     * Recurso para localizar o patrimonio por ID
     * Caso o patrimonio não exista, a operação é ifinalizada retornando um erro
     * */
    public PatrimonioEntity findById(Long id) {
        return patrimonioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patrimônio não encontrado"));
    }

    /*
     * Recurso para localizar todos os cadastros
     * */
    public List<PatrimonioEntity> findAll() {
        return patrimonioRepository.findAll();
    }

    /*
     * Recurso para atualizar o patrimonio.
     * É necessario enviar um o ID do patrimonio que deseja atualizar, em conjunto com as informações
     * */
    public void update(Long id, @Valid PatrimonioDtoUpdate dto) {
        var patrimonio = this.findById(id);
        var setor = setorRepository.findById(dto.setor())
                .orElseThrow(()-> new EntityNotFoundException("Setor não encontrado"));

        patrimonio.setNome(dto.nome());
        patrimonio.setNumeroPatrimonio(dto.numeroPatrimonio());
        patrimonio.setSetor(setor);

        patrimonioRepository.save(patrimonio);
    }

    /*
     * Recurso para deletar o patrimonio por ID
     * Caso o patrimonio não exista, a operação é finalizada retornando um erro
     * */
    public void delete(Long id) {
        this.findById(id);
        patrimonioRepository.deleteById(id);
    }

}
