package livro;

public class Livro {

    private String titulo;
    private String autor;
    private double preco;
    private String categoria;

    // Construtor
    public Livro(String titulo, String autor, double preco, String categoria) {

        this.titulo = titulo;
        this.autor = autor;
        this.preco = preco;
        this.categoria = categoria;
    }

    // Getter do título
    public String getTitulo() {
        return titulo;
    }

    // Setter do título
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // Getter do autor
    public String getAutor() {
        return autor;
    }

    // Setter do autor
    public void setAutor(String autor) {
        this.autor = autor;
    }

    // Getter do preço
    public double getPreco() {
        return preco;
    }

    // Setter do preço
    public void setPreco(double preco) {
        this.preco = preco;
    }

    // Getter da categoria
    public String getCategoria() {
        return categoria;
    }

    // Setter da categoria
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Exibir informações do livro
    @Override
    public String toString() {

        return "Título: " + titulo +
               " | Autor: " + autor +
               " | Preço: R$ " + preco +
               " | Categoria: " + categoria;
    }
}