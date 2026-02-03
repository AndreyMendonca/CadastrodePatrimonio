package com.senai.backend.entities;

import jakarta.persistence.*;

/*
 * Entidade do sistema
 * */
@Entity
@Table(name = "tb_patrimonio")
public class PatrimonioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patrimonio_id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "numero_patrimonio")
    private String numeroPatrimonio;

    @ManyToOne
    @JoinColumn(name = "setor_id")
    private SetorEntity setor;

    public PatrimonioEntity(){}

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

    public String getNumeroPatrimonio() {
        return numeroPatrimonio;
    }

    public void setNumeroPatrimonio(String numeroPatrimonio) {
        this.numeroPatrimonio = numeroPatrimonio;
    }

    public SetorEntity getSetor() {
        return setor;
    }

    public void setSetor(SetorEntity setor) {
        this.setor = setor;
    }
}
