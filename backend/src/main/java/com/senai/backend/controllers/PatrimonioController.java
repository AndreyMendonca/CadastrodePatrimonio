package com.senai.backend.controllers;

import com.senai.backend.controllers.dtos.PatrimonioDtoCreate;
import com.senai.backend.controllers.dtos.PatrimonioDtoUpdate;
import com.senai.backend.entities.PatrimonioEntity;
import com.senai.backend.service.PatrimonioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/patrimonios")
public class PatrimonioController {
    private final PatrimonioService patrimonioService;

    public PatrimonioController(PatrimonioService patrimonioService) {
        this.patrimonioService = patrimonioService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid PatrimonioDtoCreate dto){
        var  patrimonio = patrimonioService.save(dto);
        return ResponseEntity.created(URI.create("/patrimonios/"+patrimonio.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatrimonioEntity> findById(@PathVariable Long id){
        var patrimonio = patrimonioService.findById(id);
        return ResponseEntity.ok(patrimonio);
    }

    @GetMapping
    public ResponseEntity<List<PatrimonioEntity>> findAll(){
        var patrimonios = patrimonioService.findAll();
        return ResponseEntity.ok(patrimonios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid PatrimonioDtoUpdate dto){
        patrimonioService.update(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        patrimonioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
