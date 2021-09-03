package com.patronus.harrypotternaoreativo.Repository;


import com.patronus.harrypotternaoreativo.Entity.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonagemRepository extends JpaRepository<Personagem, Integer> {

    @Query(value = "select id, nome, id_casa from personagem where nome = ?1", nativeQuery = true)
    Personagem findByName(String nome);
}
