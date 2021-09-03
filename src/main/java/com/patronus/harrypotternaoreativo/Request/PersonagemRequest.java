package com.patronus.harrypotternaoreativo.Request;

import com.patronus.harrypotternaoreativo.Entity.Personagem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonagemRequest {

    private String nome;

    public Personagem convert() {
        Personagem personagem = new Personagem();
        personagem.setNome(this.nome);
        return personagem;
    }

}
