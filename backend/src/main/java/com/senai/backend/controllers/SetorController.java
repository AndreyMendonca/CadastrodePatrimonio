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

@RestController
@RequestMapping("/setores")
@CrossOrigin("*")
public class SetorController {

    private final SetorService setorService;

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid SetorDtoCreate dto){
        var  setor = setorService.save(dto);
        return ResponseEntity.created(URI.create("/setores/"+setor.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SetorEntity> findById(@PathVariable Long id){
        var setor = setorService.findById(id);
        return ResponseEntity.ok(setor);
    }

    @GetMapping
    public ResponseEntity<List<SetorEntity>> findAll(){
        var setores = setorService.findAll();
        return ResponseEntity.ok(setores);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid SetorDtoUpdate dto){
        setorService.update(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        setorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
