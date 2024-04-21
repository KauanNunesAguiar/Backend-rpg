package com.rpgded.charactercreation.Models;

public class PersonagemRequest {
    private String nome;
    private Long hpMax;

    public PersonagemRequest(String nome, Long hpMax) {
        this.nome = nome;
        this.hpMax = hpMax;
    }

    public PersonagemRequest(String nome) {
        this.nome = nome;
        this.hpMax = 10L;
    }

    // Getters e setters
    public String getNome() {
        return nome;
    }

    public Long getHpMax() {
        return hpMax;
    }
}
