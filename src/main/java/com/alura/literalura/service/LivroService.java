package com.alura.literalura.service;

import com.alura.literalura.model.AutorModel;
import com.alura.literalura.model.LivroModel;
import com.alura.literalura.dto.LivroDto;
import com.alura.literalura.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    public void salvarLivros(List<LivroDto> livrosDto) {
        for (LivroDto livroDto : livrosDto) {
            LivroModel livro = new LivroModel();
            livro.setTitle(livroDto.getTitle());
            livro.setLanguages(livroDto.getLanguages());
            livro.setDownloadCount(livroDto.getDownloadCount());

            AutorModel autor = new AutorModel();
            autor.setName(livroDto.getAutor().getName());
            autor.setBirthYear(livroDto.getAutor().getBirthYear());
            autor.setDeathYear(livroDto.getAutor().getDeathYear());

            try {
                livro.setAutor(autor);
                livroRepository.save(livro);
            } catch (DataIntegrityViolationException e) {
                System.out.println("O autor " + livro.getAutor().getName() + " já existe no banco de dados.");
                System.out.println("Voltando ao menu principal...");
            }
        }
    }

    public List<LivroModel> listarTodosLivros() {
        return livroRepository.findAll();
    }

    public List<AutorModel> listarAutorService(String name) {
        return autorRepository.findByName(name);
    }
}
