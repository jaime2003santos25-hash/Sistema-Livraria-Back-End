package livraria;

import java.util.List;

import livro.Livro;

public class ListarLivros {

    public void listar() {
        LivroDAO dao = new LivroDAO();
        List<Livro> livros = dao.listarLivros();

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
            return;
        }

        System.out.println("Livros cadastrados:");
        for (Livro livro : livros) {
            System.out.println("- " + livro);
        }
    }
}
