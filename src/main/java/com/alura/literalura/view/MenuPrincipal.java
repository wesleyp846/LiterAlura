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
        do {
            System.out.println("*** Menu Liter_Alura ***");
            System.out.println("1- Buscar livro pelo título");
            System.out.println("2- Listar livros registrados");
            System.out.println("3- Listar autores registrados");
            System.out.println("4- Listar autores vivos em um determinado ano");
            System.out.println("5- Listar livros em um determinado idioma");
            System.out.println("9 - Sair");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (opcao) {
                case 1:
                    System.out.println("Digite o título do livro: ");
                    String titulo = scanner.nextLine();
                    literAluraController.buscarLivroPeloTitulo(titulo);
                    break;
                case 9:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        } while (opcao != 9);
    }
}