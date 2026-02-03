package com.senai.backend.service;

import com.senai.backend.controllers.dtos.SetorDtoCreate;
import com.senai.backend.controllers.dtos.SetorDtoUpdate;
import com.senai.backend.entities.SetorEntity;
import com.senai.backend.exceptions.EntityDeleteException;
import com.senai.backend.exceptions.EntityNotFoundException;
import com.senai.backend.repositories.SetorRepository;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

/*
 * Regras de negócio - Setor
 * */
@Service
public class SetorService {
    /*
    * Dependencia que o service tem
    * */
    private final SetorRepository setorRepository;

    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    /*
     * Recurso para salvar o setor
     * */
    public SetorEntity save(SetorDtoCreate dto) {
        SetorEntity setor = new SetorEntity();
        setor.setNome(dto.nome());
        return setorRepository.save(setor);
    }

    /*
     * Recurso para localizar o setor por id
     * Caso o setor não exista, a operação é finalizada retornando um erro
     * */
    public SetorEntity findById(Long id) {
        return setorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Setor não encontrado"));
    }
    /*
     * Recurso para atualizar o setor por id
     * É necessario enviar um o ID do setor que deseja atualizar, em conjunto com as informações
     * Caso o setor não exista, a operação é finalizada retornando um erro
     * */
    public void update(Long id, @Valid SetorDtoUpdate dto) {
        var setor = this.findById(id);
        setor.setNome(dto.nome());
        setorRepository.save(setor);
    }

    /*
     * Recurso para deletar o setor por ID
     * Caso o patrimonio não exista, a operação é ifinalizada retornando um erro
     * */
    public void delete(Long id) {
        this.findById(id);
        try{
            setorRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityDeleteException("Não é possível deletar um setor vinculado a algum patrimônio");
        }
    }

    /*
     * Recurso para listar todos os setor
     * */
    public List<SetorEntity> findAll() {
        return setorRepository.findAll();
    }
}
