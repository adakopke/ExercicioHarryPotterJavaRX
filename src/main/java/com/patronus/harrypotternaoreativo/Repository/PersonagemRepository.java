package com.patronus.harrypotternaoreativo.Repository;


import com.patronus.harrypotternaoreativo.Entity.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonagemRepository extends JpaRepository<Personagem, Integer> {

  // O JPARepository já fornece os métodos de consulta baseados nos atributos das Entities, não
  // é necessário usar o nativeQuery. Deixando o comentário para estudo futuro.
  //  @Query(value = "select id, nome, id_casa from personagem where nome = ?1", nativeQuery = true)
    Personagem findByNome(String nome);

}
