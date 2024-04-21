package com.rpgded.charactercreation.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.rpgded.charactercreation.Models.Personagem;
import com.rpgded.charactercreation.Models.PersonagemRequest;
import com.rpgded.charactercreation.Services.PersonagemService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/personagem")
public class PersonagemController {

    @Autowired
    private PersonagemService personagemService;
    private ArrayList<Personagem> listaDePersonagens = new ArrayList<>();

    @GetMapping("/criar")
    public ResponseEntity<Personagem> criarPersonagem(@RequestParam String nome, @RequestParam(required = false) Long hp) {
        if(hp == null) hp = 10L;
        PersonagemRequest request = new PersonagemRequest(nome, hp);
        Personagem personagem = personagemService.criarPersonagem(request);
        listaDePersonagens.add(personagem);
        return ResponseEntity.ok(personagem);
    }

    @GetMapping("/deletar/id:{id}")
    public ResponseEntity<String> deletarPersonagem(@PathVariable Long id) {
        for (Personagem personagem : listaDePersonagens) {
            if (personagem.getId().equals(id)) {
                listaDePersonagens.remove(personagem);
                return ResponseEntity.ok("Personagem deletado");
            }
        }
        return ResponseEntity.ok("Personagem não encontrado");
    }

    @GetMapping("/procurar/id:{id}")
    public Personagem getPersonagem(@PathVariable Long id) {
        for (Personagem personagem : listaDePersonagens) {
            if (personagem.getId().equals(id)) {
                return personagem;
            }
        }
        return null;
    }

    @GetMapping("/procurar/nome:{nome}")
    public Personagem getPersonagem(@PathVariable String nome) {
        for (Personagem personagem : listaDePersonagens) {
            if (personagem.getNome().equals(nome)) {
                return personagem;
            }
        }
        return null;
    }

    @GetMapping("/açao/id:{id}/{acao}/{valor}")
    public String acao(@PathVariable Long id, @PathVariable String acao, @PathVariable Long valor) {
        Personagem personagem = getPersonagem(id);
        return personagemService.fazAcao(personagem, acao, valor);
    }

    @GetMapping("/açao/nome:{nome}/{acao}/{valor}")
    public String acao(@PathVariable String nome, @PathVariable String acao, @PathVariable Long valor) {
        Personagem personagem = getPersonagem(nome);
        return personagemService.fazAcao(personagem, acao, valor);
    }

    @GetMapping("/listar")
    public ResponseEntity<Map<String, String>> listarPersonagens() {
        Map<String, String> resposta = new HashMap<>();
        for (Personagem personagem : listaDePersonagens) {
            resposta.put(personagem.getId().toString(), personagem.getNome());
        }
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/ajuda")
    public ResponseEntity<Map<String, String>> obterAjuda() {
        Map<String, String> resposta = new HashMap<>();
        resposta.put("estudante", "Seu nome");
        resposta.put("projeto", "Nome do projeto");
        return ResponseEntity.ok(resposta);
    }
}
