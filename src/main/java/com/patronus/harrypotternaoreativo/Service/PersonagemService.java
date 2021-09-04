package com.patronus.harrypotternaoreativo.Service;

import com.google.gson.Gson;
import com.patronus.harrypotternaoreativo.Entity.Casa;
import com.patronus.harrypotternaoreativo.Entity.SorteioDoChapeu;
import com.patronus.harrypotternaoreativo.Entity.Personagem;
import com.patronus.harrypotternaoreativo.Repository.PersonagemRepository;
import com.patronus.harrypotternaoreativo.Request.PersonagemRequest;
import com.patronus.harrypotternaoreativo.Response.PersonagemResponse;
import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PersonagemService {

    private final PersonagemRepository personagemRepository;

    public SorteioDoChapeu sortearCasa() {
        String url = "https://api-harrypotter.herokuapp.com/sortinghat";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resposta = restTemplate.getForEntity(url, String.class);
        Gson gson = new Gson();
        return gson.fromJson(resposta.getBody(), SorteioDoChapeu.class);
    }

    public Single<PersonagemResponse> executarGravacao(PersonagemRequest personagemRequest) {
        return Single.create(single -> {
            Personagem personagem = personagemRequest.convert();
            personagem.setId_casa(sortearCasa().getId());
            Casa casa = retornaDetalhesCasaAtribuida(personagem);
            personagemRepository.save(personagem);
            single.onSuccess(new PersonagemResponse(personagem, casa));
        });


    }

    public Single<PersonagemResponse> executarConsulta(String nome) {
        return Single.create(single -> {
          Personagem personagem = personagemRepository.findByNome(nome);
          Casa casa = retornaDetalhesCasaAtribuida(personagem);
          single.onSuccess(new PersonagemResponse(personagem, casa));
        });

    }

    public Casa retornaDetalhesCasaAtribuida(Personagem personagem) {

        String url = "https://api-harrypotter.herokuapp.com/house/" + personagem.getId_casa();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resposta = restTemplate.getForEntity(url, String.class);
        Gson gson = new Gson();
        return gson.fromJson(resposta.getBody(), Casa.class);
    }

    public Observable<PersonagemResponse> listarTodos() {

        return Observable.fromIterable(personagemRepository.findAll()).map(this::converterUnitario);

// Só estou deixando este código comentado aqui para efeito de estudo futuro.
// O mesmo foi refatorado na aula do dia 03/09 pelo Matheus, consultar o 1/4 do video da aula.
//        Single single = Single.create(emitter -> {
//            emitter.onSuccess(
//            personagemRepository.findAll().stream().map(this::converterUnitario).collect(Collectors.toList()));
//        });
//        return single.toObservable();

    }

    public List<PersonagemResponse> converter(List<Personagem> personagens) {
        List<PersonagemResponse> lista = new ArrayList<>();
        for (Personagem personagem : personagens) {
            Casa casa = retornaDetalhesCasaAtribuida(personagem);
            PersonagemResponse personagemResponse = new PersonagemResponse(personagem, casa);
            lista.add(personagemResponse);
        }
        return lista;
    }

    public PersonagemResponse converterUnitario(Personagem personagem) {
            Casa casa = retornaDetalhesCasaAtribuida(personagem);
        return new PersonagemResponse(personagem, casa);    }


}
