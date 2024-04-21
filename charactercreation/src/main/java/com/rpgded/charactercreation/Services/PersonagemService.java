package com.rpgded.charactercreation.Services;

import org.springframework.stereotype.Service;
import com.rpgded.charactercreation.Models.Personagem;
import com.rpgded.charactercreation.Models.PersonagemRequest;

@Service
public class PersonagemService {

    private Long idCounter = 1L;

    public Personagem criarPersonagem(PersonagemRequest request) {
        Personagem personagem = new Personagem(idCounter, request.getNome(), request.getHpMax(), request.getHpMax());
        idCounter++;
        return personagem;
    }

    public Personagem cura (Personagem personagem, Long cura) {
        Long hpAtual = personagem.getHpAtual();
        Long hpMax = personagem.getHpMax();
        if (hpAtual + cura > hpMax) {
            personagem.setHpAtual(hpMax);
        } else {
            personagem.setHpAtual(hpAtual + cura);
        }
        return personagem;
    }

    public String fazAcao (Personagem personagem, String acao, Long valor) {
        if (personagem == null) {
            return "Personagem não encontrado";
        }
        switch (acao) {
            case "cura":
                this.cura(personagem, valor);
                return "Personagem " + personagem.getNome() + " curado em " + valor + " pontos de vida";
            case "dano":
                this.dano(personagem, valor);
                return "Personagem " + personagem.getNome() + " recebeu " + valor + " pontos de dano";
            default:
                return "Ação inválida";
        }
    }

    public Personagem dano (Personagem personagem, Long dano) {
        Long hpAtual = personagem.getHpAtual();
        if (hpAtual - dano < 0) {
            personagem.setHpAtual(0L);
        } else {
            personagem.setHpAtual(hpAtual - dano);
        }
        return personagem;
    }
}
