package com.alura.literalura.service;

import com.alura.literalura.model.AutorModel;
import com.alura.literalura.model.LivroModel;
import com.alura.literalura.dto.LivroDto;
import com.alura.literalura.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    public void salvarLivros(List<LivroDto> livrosDto) {
        for (LivroDto livroDto : livrosDto) {
            try {
                AutorModel autor = new AutorModel();
                LivroModel livro = new LivroModel();

                livro.setTitle(livroDto.getTitle());
                livro.setLanguages(livroDto.getLanguages());
                livro.setDownloadCount(livroDto.getDownloadCount());

                autor.setName(livroDto.getAutor().getName());
                autor.setBirthYear(livroDto.getAutor().getBirthYear());
                autor.setDeathYear(livroDto.getAutor().getDeathYear());

                AutorModel autorSalvo = autorRepository.save(autor);

                livro.setAutor(autorSalvo);

                livroRepository.save(livro);

                autorSalvo.setLivros(Collections.singletonList(livro));
                autorRepository.save(autorSalvo); // Atualizar o autor com a lista de livros
            } catch (DataIntegrityViolationException e) {
                System.out.println("Erro: Título de livro duplicado no banco de dados.");
            } catch (Exception e) {
                System.out.println("Erro ao salvar livro: " + e.getMessage());
            }
        }
    }

    public List<LivroModel> listarTodosLivros() {
        return livroRepository.findAll();
    }

    public List<AutorModel> listarAutorService(String name) {
            return autorRepository.findByNameContainingIgnoreCase(name);
    }

    public List<AutorModel> listarAutoresVivosNoAno(int year) {
        return autorRepository.findAuthorsAliveInYear(year);
    }

    public List<LivroModel> listarLivrosPorIdioma(String language) {
        return livroRepository.findByLanguages(language);
    }
}
