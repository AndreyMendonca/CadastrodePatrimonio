package com.senai.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_setor")
public class SetorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "setor_id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    public SetorEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
