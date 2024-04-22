package com.rpgded.charactercreation.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.rpgded.charactercreation.Models.Personagem;
import com.rpgded.charactercreation.Models.PersonagemRequest;
import com.rpgded.charactercreation.Services.PersonagemService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/personagem")
public class PersonagemController {

    private RestTemplate restTemplate = new RestTemplate();
    private final String ROLL_API_URL = "https://rpg-dice-roller-api.djpeacher.com/api/roll/";

    @Autowired
    private PersonagemService personagemService;
    private ArrayList<Personagem> listaDePersonagens = new ArrayList<>();

    @PostMapping("/criar")
    public ResponseEntity<String> criarPersonagem(@RequestBody PersonagemRequest request) {
        Personagem personagem = personagemService.criarPersonagem(request);
        listaDePersonagens.add(personagem);
        return ResponseEntity.ok("Personagem " + personagem.getNome() + " criado com sucesso. ID: " + personagem.getId());
    }

    @GetMapping("/criar/nome:{nome}")
    public ResponseEntity<Personagem> criarPersonagem(@PathVariable String nome, @RequestParam(required = false) Long hp) {
        if(hp == null) hp = 10L;
        PersonagemRequest request = new PersonagemRequest(nome, hp);
        Personagem personagem = personagemService.criarPersonagem(request);
        listaDePersonagens.add(personagem);
        return ResponseEntity.ok(personagem);
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

    @GetMapping("/rolar/{notacao}")
    public ResponseEntity<String> rolagem(@PathVariable String notacao) {
        int rolledValue = rolarDado(notacao);
        return ResponseEntity.ok("Valor rolado: "+ notacao + " = " + rolledValue);
    }

    public int rolarDado(String notacao) {
        String apiUrl = ROLL_API_URL + notacao;
        ResponseEntity<String> resposta = restTemplate.getForEntity(apiUrl, String.class);

        if (resposta.getStatusCode() == HttpStatus.OK) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(resposta.getBody());

                int total = jsonNode.get("total").asInt();
                return total;
            } catch (Exception e) {
                throw new RuntimeException("Erro ao processar resposta da API de rolagem de dados", e);
            }
        } else {
            throw new RuntimeException("Falha ao rolar o dado.");
        }
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

    @GetMapping("/açao/id:{id}/{acao}/valor:{valor}")
    public String acao(@PathVariable Long id, @PathVariable String acao, @PathVariable Long valor) {
        Personagem personagem = getPersonagem(id);
        return personagemService.fazAcao(personagem, acao, valor);
    }

    @GetMapping("/açao/id:{id}/{acao}/dado:{dado}")
    public String acao(@PathVariable Long id, @PathVariable String acao, @PathVariable String dado) {
        int valor = rolarDado(dado);
        Personagem personagem = getPersonagem(id);
        return personagemService.fazAcao(personagem, acao, (long) valor);
    }

    @GetMapping("/açao/nome:{nome}/{acao}/{valor}")
    public String acao(@PathVariable String nome, @PathVariable String acao, @PathVariable Long valor) {
        Personagem personagem = getPersonagem(nome);
        return personagemService.fazAcao(personagem, acao, valor);
    }

    @GetMapping("/açao/nome:{nome}/{acao}/dado:{dado}")
    public String acao(@PathVariable String nome, @PathVariable String acao, @PathVariable String dado) {
        int valor = rolarDado(dado);
        Personagem personagem = getPersonagem(nome);
        return personagemService.fazAcao(personagem, acao, (long) valor);
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
