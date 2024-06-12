package com.alura.literalura.repository;

import com.alura.literalura.model.AutorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<AutorModel, Long> {
    List<AutorModel> findByName(String name);
}

