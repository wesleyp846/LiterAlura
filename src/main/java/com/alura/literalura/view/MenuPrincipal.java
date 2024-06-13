package com.alura.literalura.view;

import com.alura.literalura.controller.LiteraluraController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MenuPrincipal {

    @Autowired
    private LiteraluraController literAluraController;

    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        while (true) {
            System.out.println("___________________________________________");
            System.out.println("*** Menu Liter_Alura ***");
            System.out.println("1- Buscar livro pelo título");
            System.out.println("2- Listar livros registrados");
            System.out.println("3- Listar autores registrados");
            System.out.println("4- Listar autores vivos em um determinado ano");
            System.out.println("5- Listar livros em um determinado idioma");
            System.out.println("9 - Sair");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("___________________________________________");
                    System.out.println("Digite o título do livro: ");
                    String titulo = scanner.nextLine();
                    literAluraController.buscarLivroPeloTitulo(titulo);
                    break;
                case 2:
                    System.out.println("___________________________________________");
                    System.out.println("Listando livros no DB.......");
                    literAluraController.listarTodosLivros();
                    break;
                case 3:
                    System.out.println("___________________________________________");
                    System.out.println("Listando autores no DB.......");
                    System.out.println("Digite o nome do autor:");
                    String nome = scanner.nextLine();
                    literAluraController.listarAutorPorNome(nome);
                    break;
                case 4:
                    System.out.println("___________________________________________");
                    System.out.println("Listando autores vivos no ano.......");
                    System.out.println("Digite o ano:");
                    int year = scanner.nextInt();
                    literAluraController.listarAutoresVivosNoAno(year);
                    break;
                case 5:
                    System.out.println("___________________________________________");
                    int opIdioma;
                    System.out.println("Escolha a opção do idioma desejado");
                    System.out.println("1- pt Português");
                    System.out.println("2- en Inglês");
                    System.out.println("3- fr Frances");
                    opIdioma = scanner.nextInt();
                    scanner.nextLine();

                    switch (opIdioma) {
                        case 1:
                            System.out.println("Listando livros no idioma selecionado.......");
                            literAluraController.listarLivrosPorIdioma("pt");
                            break;

                        case 2:
                            System.out.println("Listando livros no idioma selecionado.......");
                            literAluraController.listarLivrosPorIdioma("en");
                            break;

                        case 3:
                            System.out.println("Listando livros no idioma selecionado.......");
                            literAluraController.listarLivrosPorIdioma("fr");
                            break;

                        default:
                            System.out.println("Opção inválida, tente novamente.");
                            break;
                    }
                    break;

                case 9:
                    System.out.println("Saindo...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }
}