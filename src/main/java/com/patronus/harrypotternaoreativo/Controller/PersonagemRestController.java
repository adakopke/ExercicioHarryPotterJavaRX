package com.patronus.harrypotternaoreativo.Controller;


import com.patronus.harrypotternaoreativo.Entity.Casa;
import com.patronus.harrypotternaoreativo.Entity.Personagem;
import com.patronus.harrypotternaoreativo.Request.PersonagemRequest;
import com.patronus.harrypotternaoreativo.Response.PersonagemResponse;
import com.patronus.harrypotternaoreativo.Service.PersonagemService;
import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hp/person")
@AllArgsConstructor
public class PersonagemRestController {

    private final PersonagemService personagemService;

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public Single<PersonagemResponse> addPersonagem(@RequestBody PersonagemRequest personagemRequest) {
        return personagemService.executarGravacao(personagemRequest);
    }

    @GetMapping("{nome}")
    @ResponseStatus(HttpStatus.OK)
    public Single<PersonagemResponse> consultarPersonagem(@PathVariable String nome) {
        return personagemService.executarConsulta(nome);
    }

    @GetMapping("todos")
    @ResponseStatus(HttpStatus.OK)
    public Observable<PersonagemResponse> listarTodos() {
        return personagemService.listarTodos();

    }

}


