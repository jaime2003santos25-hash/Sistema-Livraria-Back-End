package livraria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexao.conexao;
import livro.Livro;

public class LivroDAO {

    // Inserir livro
    public boolean inserirLivro(Livro livro) {

        String sql =
            "INSERT INTO livros (idLivros, titulo, autor, preco, categoria) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = conexao.conectar()) {

            if (conn == null) {

                System.out.println(
                    "Não foi possível conectar ao banco."
                );

                return false;
            }

            int novoId = gerarNovoId(conn);

            try (PreparedStatement stmt =
                     conn.prepareStatement(sql)) {

                stmt.setInt(1, novoId);
                stmt.setString(2, livro.getTitulo());
                stmt.setString(3, livro.getAutor());
                stmt.setDouble(4, livro.getPreco());
                stmt.setString(5, livro.getCategoria());

                int linhasAfetadas = stmt.executeUpdate();

                if (linhasAfetadas > 0) {

                    System.out.println(
                        "Livro inserido com sucesso: "
                        + livro.getTitulo()
                    );

                    return true;
                }

            }

        } catch (SQLException e) {

            System.out.println(
                "Erro ao inserir livro: "
                + e.getMessage()
            );
        }

        return false;
    }

    private int gerarNovoId(Connection conn) throws SQLException {

        String sql = "SELECT COALESCE(MAX(idLivros), 0) + 1 FROM livros";

        try (
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }

        return 1;
    }

    // Listar livros
    public List<Livro> listarLivros() {

        List<Livro> livros = new ArrayList<>();

        String sql =
            "SELECT titulo, autor, preco, categoria FROM livros";

        try (Connection conn = conexao.conectar()) {

            if (conn == null) {

                System.out.println(
                    "Não foi possível conectar ao banco."
                );

                return livros;
            }

            try (
                Statement stmt = conn.createStatement();

                ResultSet rs = stmt.executeQuery(sql)
            ) {

                while (rs.next()) {

                    Livro livro = new Livro(

                        rs.getString("titulo"),

                        rs.getString("autor"),

                        rs.getDouble("preco"),

                        rs.getString("categoria")
                    );

                    livros.add(livro);
                }
            }

        } catch (SQLException e) {

            System.out.println(
                "Erro ao listar livros: "
                + e.getMessage()
            );
        }

        return livros;
    }
}