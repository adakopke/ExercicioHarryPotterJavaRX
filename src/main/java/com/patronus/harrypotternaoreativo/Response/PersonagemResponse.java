package com.patronus.harrypotternaoreativo.Response;

import com.patronus.harrypotternaoreativo.Entity.Casa;
import com.patronus.harrypotternaoreativo.Entity.Personagem;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PersonagemResponse {

    private Integer id;
    private String nome;
    private Casa casa;

    public PersonagemResponse(Personagem personagem, Casa casa) {
        this.id = personagem.getId();
        this.nome = personagem.getNome();
        this.casa = casa;

    }

//    public static List<PersonagemResponse> convert(List<Personagem> personagens){
//            return personagens.stream().
//                map(PersonagemResponse::new).
//                collect(Collectors.toList());
//    }

}
