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

/*
 * Mapeamento de um novo endpont - "/patrimonios
 * */
@RestController
@RequestMapping("/patrimonios")
@CrossOrigin("*")
public class PatrimonioController {
    /*
     * Conexão com o service
     * */
    private final PatrimonioService patrimonioService;

    public PatrimonioController(PatrimonioService patrimonioService) {
        this.patrimonioService = patrimonioService;
    }

    /*
     * Endpoint para criar um novo patrimonio
     * É necessario passar a qual setor o patrimonio está vinculado
     * */
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid PatrimonioDtoCreate dto){
        var  patrimonio = patrimonioService.save(dto);
        return ResponseEntity.created(URI.create("/patrimonios/"+patrimonio.getId())).build();
    }

    /*
     * Endpoint para encontrar o patrimonio por ID
     * */
    @GetMapping("/{id}")
    public ResponseEntity<PatrimonioEntity> findById(@PathVariable Long id){
        var patrimonio = patrimonioService.findById(id);
        return ResponseEntity.ok(patrimonio);
    }

    /*
     * Endpoint para listar todos os patrimonios
     * */
    @GetMapping
    public ResponseEntity<List<PatrimonioEntity>> findAll(){
        var patrimonios = patrimonioService.findAll();
        return ResponseEntity.ok(patrimonios);
    }

    /*
     * Endpoint para atualizar um patrimonio, precisamos passar o id e os dados para serem atualizados
     * */
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid PatrimonioDtoUpdate dto){
        patrimonioService.update(id, dto);
        return ResponseEntity.ok().build();
    }

    /*
     * Endpoint para deletar um patrimonio.
     * */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        patrimonioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
