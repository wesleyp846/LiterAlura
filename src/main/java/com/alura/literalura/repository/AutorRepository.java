package com.alura.literalura.repository;

import com.alura.literalura.model.AutorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<AutorModel, Long> {
    List<AutorModel> findByName(String name);

    @Query("SELECT a FROM AutorModel a WHERE a.birthYear <= :year AND (a.deathYear IS NULL OR a.deathYear > :year)")
    List<AutorModel> findAuthorsAliveInYear(@Param("year") int year);
}

