package com.alura.literalura.controller;

import com.alura.literalura.dto.LivroDto;
import com.alura.literalura.model.AutorModel;
//import com.alura.literalura.model.IdiomaModel;
import com.alura.literalura.model.LivroModel;
import com.alura.literalura.service.ApiClient;
import com.alura.literalura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LiteraluraController {

    @Autowired
    private ApiClient apiClient;

    @Autowired
    private LivroService livroService;

    @Autowired
    private LivroService autorService;

    public void buscarLivroPeloTitulo(String titulo) {
        try {
            List<LivroDto> livros = apiClient.buscarLivrosPeloTitulo(titulo);

            for (LivroDto livro : livros) {
                System.out.println("Título: " + livro.getTitle());
                System.out.println("Autor: " + livro.getAutor().getName());
                System.out.println("Idioma: " + livro.getLanguages());
                System.out.println("Número de downloads: " + livro.getDownloadCount());
                System.out.println("______________________");
            }
            livroService.salvarLivros(livros);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listarTodosLivros() {
        List<LivroModel> livros = livroService.listarTodosLivros();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
        } else {
            for (LivroModel livro : livros) {
                System.out.println("________LIVRO______________");
                System.out.println("Título: " + livro.getTitle());
                System.out.println("Autor: " + livro.getAutor().getName());
                System.out.println("Idioma: " + livro.getLanguages());
                System.out.println("Número de downloads: " + livro.getDownloadCount());

            }
        }
    }

    public void listarAutorPorNome(String name) {
        List<AutorModel> autores = autorService.listarAutorService(name);
        for (AutorModel autor : autores) {
            System.out.println("________AUTOR______________");
            System.out.println("Nome: " + autor.getName());
            System.out.println("Nascimento: " + autor.getBirthYear());
            System.out.println("Falecimento: " + (autor.getDeathYear() != null ? autor.getDeathYear() : "Vivo"));
            System.out.println("Livros: ");
            for (LivroModel livro : autor.getLivros()) {
                System.out.println("Título: " + livro.getTitle());
            }
        }
    }

    public void listarAutoresVivosNoAno(int year) {
        List<AutorModel> autores = autorService.listarAutoresVivosNoAno(year);
        for (AutorModel autor : autores) {
            System.out.println("Nome: " + autor.getName());
            System.out.println("Nascimento: " + autor.getBirthYear());
            System.out.println("Falecimento: " + autor.getDeathYear());
            System.out.println("___________________________________________");
        }
    }

    public void listarLivrosPorIdioma(String idioma) {
        List<LivroModel> livros = livroService.listarLivrosPorIdioma(idioma);
        for (LivroModel livro : livros) {
            System.out.println("Título: " + livro.getTitle());
            System.out.println("Autor: " + livro.getAutor().getName());
            System.out.println("Idiomas: " + livro.getLanguages()
//                    .stream().map(IdiomaModel::getName).collect(Collectors.joining(", "))
            );
            System.out.println("-----------------------------------");
        }
    }
}
