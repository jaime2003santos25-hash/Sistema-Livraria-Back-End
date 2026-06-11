package livraria;

import livro.Livro;

public class Main {

    public static void main(String[] args) {

        LivroDAO dao = new LivroDAO();

        // Inserindo livros
        dao.inserirLivro(
            new Livro(
                "Clean Code",
                "Robert C. Martin",
                89.90,
                "Programação"
            )
        );

        dao.inserirLivro(
            new Livro(
                "Hábitos Atômicos",
                "James Clear",
                74.90,
                "Autoajuda"
            )
        );

        dao.inserirLivro(
            new Livro(
                "O Poder do Hábito",
                "Charles Duhigg",
                79.90,
                "Autoajuda"
            )
        );

        dao.inserirLivro(
            new Livro(
                "Anatomia do Ciclismo",
                "Chris Sidwells",
                65.90,
                "Esporte"
            )
        );

        dao.inserirLivro(
            new Livro(
                "Java Básico",
                "Denilson Silva",
                59.90,
                "Programação"
            )
        );

        dao.inserirLivro(
            new Livro(
                "POO na Prática",
                "Fernando Lima",
                79.90,
                "Programação"
            )
        );

        dao.inserirLivro(
            new Livro(
                "Banco de Dados MySQL",
                "Carla Mendes",
                89.50,
                "Programação"
            )
        );

        dao.inserirLivro(
            new Livro(
                "Redes de Computadores",
                "Ricardo Souza",
                120.00,
                "Programação"
            )
        );

        dao.inserirLivro(
            new Livro(
                "Segurança da Informação",
                "Amanda Rocha",
                149.90,
                "Segurança"
            )
        );

        // Listando livros
        ListarLivros lista = new ListarLivros();
        lista.listar();
    }
}