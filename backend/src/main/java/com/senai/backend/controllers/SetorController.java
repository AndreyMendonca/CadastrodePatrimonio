package com.senai.backend.controllers;

import com.senai.backend.controllers.dtos.SetorDtoCreate;
import com.senai.backend.controllers.dtos.SetorDtoUpdate;
import com.senai.backend.entities.SetorEntity;
import com.senai.backend.service.SetorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/*
* Mapeamento de um novo endpont - "/setores
* */
@RestController
@RequestMapping("/setores")
@CrossOrigin("*")
public class SetorController {

    /*
     * Conexão com o service
     * */
    private final SetorService setorService;

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    /*
     * Endpoint para criar um novo setor
     * */
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid SetorDtoCreate dto){
        var  setor = setorService.save(dto);
        return ResponseEntity.created(URI.create("/setores/"+setor.getId())).build();
    }

    /*
     * Endpoint para encontrar o setor por ID
     * */
    @GetMapping("/{id}")
    public ResponseEntity<SetorEntity> findById(@PathVariable Long id){
        var setor = setorService.findById(id);
        return ResponseEntity.ok(setor);
    }

    /*
     * Endpoint para listar todos os setores
     * */
    @GetMapping
    public ResponseEntity<List<SetorEntity>> findAll(){
        var setores = setorService.findAll();
        return ResponseEntity.ok(setores);
    }

    /*
     * Endpoint para atualizar um setor, precisamos passar o id e os dados para serem atualizados
     * */
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid SetorDtoUpdate dto){
        setorService.update(id, dto);
        return ResponseEntity.ok().build();
    }

    /*
     * Endpoint para deletar um setor.
     * Pode ocorrer um erro caso o setor esteja vinculado a um patrimonio
     * */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        setorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
