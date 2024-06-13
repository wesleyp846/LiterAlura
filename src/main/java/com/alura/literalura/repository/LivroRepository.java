package com.alura.literalura.repository;

import com.alura.literalura.model.LivroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<LivroModel, Long> {
    List<LivroModel> findByLanguages(@Param("languages") String language);
}

