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
            // Criar instâncias dos modelos
            AutorModel autor = new AutorModel();
            LivroModel livro = new LivroModel();

            // Configurar livro
            livro.setTitle(livroDto.getTitle());
            livro.setLanguages(livroDto.getLanguages());
            livro.setDownloadCount(livroDto.getDownloadCount());

            // Configurar autor
            autor.setName(livroDto.getAutor().getName());
            autor.setBirthYear(livroDto.getAutor().getBirthYear());
            autor.setDeathYear(livroDto.getAutor().getDeathYear());

            // Salvar autor primeiro
            AutorModel autorSalvo = autorRepository.save(autor);

            // Associar o autor salvo ao livro
            livro.setAutor(autorSalvo);

            // Salvar o livro
            livroRepository.save(livro);

            // Associar o livro salvo ao autor
            autorSalvo.setLivros(Collections.singletonList(livro));
            autorRepository.save(autorSalvo); // Atualizar o autor com a lista de livros

            // Debugging output
            System.out.println("debug de controle ----------");
            System.out.println(livroDto.getTitle());

//            try {
//                // Associando o livro ao autor
//                livro.setAutor(autor);
//                autor.setLivros(List.of(livro)); // Cria uma lista com o livro
//                livroRepository.save(livro);
//            } catch (DataIntegrityViolationException e) {
//                System.out.println("O autor " + livro.getAutor().getName() + " já existe no banco de dados.");
//                System.out.println("Voltando ao menu principal...");
//            }
        }
    }

    public List<LivroModel> listarTodosLivros() {
        return livroRepository.findAll();
    }

    public List<AutorModel> listarAutorService(String name) {
        return autorRepository.findByName(name);
    }

    public List<AutorModel> listarAutoresVivosNoAno(int year) {
        return autorRepository.findAuthorsAliveInYear(year);
    }

//    public List<LivroModel> listarLivrosPorIdioma(String language) {
//        return livroRepository.findByLanguage(language);
//    }
}
