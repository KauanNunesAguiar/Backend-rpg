package com.rpgded.charactercreation.Models;

public class Personagem {
    private Long id;
    private String nome;
    private Long hpMax;
    private Long hpAtual;

    public Personagem(Long id, String nome, Long hpMax, Long hpAtual) {
        this.id = id;
        this.nome = nome;
        this.hpMax = hpMax;
        this.hpAtual = hpAtual;
    }

    public Personagem(Long id, String nome) {
        this.id = id;
        this.nome = nome;
        this.hpMax = 10L;
        this.hpAtual = 10L;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Long getHpMax() {
        return hpMax;
    }

    public Long getHpAtual() {
        return hpAtual;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setHpMax(Long hpMax) {
        this.hpMax = hpMax;
    }

    public void setHpAtual(Long hpAtual) {
        this.hpAtual = hpAtual;
    }
}
