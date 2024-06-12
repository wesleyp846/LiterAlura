package com.alura.literalura.controller;

import com.alura.literalura.dto.LivroDto;
import com.alura.literalura.model.LivroModel;
import com.alura.literalura.service.ApiClient;
import com.alura.literalura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
public class LiteraluraController {

    @Autowired
    private ApiClient apiClient;

    @Autowired
    private LivroService livroService;

    public void buscarLivroPeloTitulo(String titulo) {
        try {
            List<LivroDto> livros = apiClient.buscarLivrosPeloTitulo(titulo);

            for (LivroDto livro : livros) {
                System.out.println("Título: " + livro.getTitle());
                System.out.println("Autor: " + livro.getAutor().getName());
                System.out.println("Idioma: " + livro.getLanguages());
                System.out.println("Número de downloads: " + livro.getDownloadCount());
                System.out.println(); // Separador entre livros
            }

            // Salvar os livros no banco de dados
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
                System.out.println("Título: " + livro.getTitle());
                System.out.println("Autor: " + livro.getAutor().getName());
                System.out.println("Idioma: " + livro.getLanguages());
                System.out.println("Número de downloads: " + livro.getDownloadCount());
                System.out.println("----------------------------");
            }
        }
    }
}
