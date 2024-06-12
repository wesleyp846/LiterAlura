package com.alura.literalura.service;

import com.alura.literalura.dto.AutorDto;
import com.alura.literalura.dto.LivroDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApiClient {
    private static final String API_URL = "https://gutendex.com/books/?search=";

    public List<LivroDto> buscarLivrosPeloTitulo(String titulo) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(API_URL + titulo, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(response);
        JsonNode results = jsonNode.get("results");

        List<LivroDto> livros = new ArrayList<>();
        if (results != null) {
            for (JsonNode node : results) {
                LivroDto livro = new LivroDto();
                livro.setTitle(node.get("title").asText());
                livro.setLanguages(mapper.convertValue(node.get("languages"), List.class));
                livro.setDownloadCount(node.get("download_count").asInt());

                AutorDto autor = new AutorDto();
                JsonNode authorNode = node.get("authors").get(0);
                autor.setName(authorNode.get("name").asText());
                autor.setBirthYear(authorNode.get("birth_year").asInt());
                autor.setDeathYear(authorNode.get("death_year").asInt());

                livro.setAutor(autor);
                livros.add(livro);
            }
        }
        return livros;
    }
}

