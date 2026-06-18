package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {
    "http://localhost:5173",
    "https://sistema-livraria-front-end.vercel.app",
    "https://sistema-livraria-front-9a442edbd-jayme-s-projects2.vercel.app"
})
public class LivroController {

    @GetMapping("/livros")
    public List<Map<String, Object>> listarLivros() {

        List<Map<String, Object>> livros = new ArrayList<>();
        Set<String> seenLivros = new HashSet<>();

        String url = "jdbc:mysql://thomas.proxy.rlwy.net:33256/railway";
        String user = "root";
        String password = "TtLvkseGpmczjiqGbFXWNDYqzjJLsRwd";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement("SELECT idLivros, titulo, autor, preco, categoria FROM Livros");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                double preco = rs.getDouble("preco");
                String categoria = rs.getString("categoria");
                String key = titulo + "|" + autor + "|" + preco + "|" + categoria;

                if (!seenLivros.add(key)) {
                    continue;
                }

                Map<String, Object> livro = new HashMap<>();
                livro.put("id", rs.getInt("idLivros"));
                livro.put("titulo", titulo);
                livro.put("autor", autor);
                livro.put("preco", preco);
                livro.put("categoria", categoria);
                livros.add(livro);
            }

        } catch (Exception e) {
            System.err.println("Erro ao listar livros: " + e.getMessage());
        }

        return livros;
    }
}  