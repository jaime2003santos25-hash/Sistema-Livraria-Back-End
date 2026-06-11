package conexao;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class conexao {

    // Configurações padrão
    private static final String DEFAULT_URL =
        "jdbc:mysql://localhost:3306/livraria";

    private static final String DEFAULT_USER = "root";

    // COLOQUE SUA SENHA REAL DO MYSQL AQUI
    private static final String DEFAULT_PASSWORD = "123456789";

    public static Connection conectar() {

        Properties props = loadProperties();

        // Lê do arquivo database.properties
        String url = props.getProperty("db.url", DEFAULT_URL);

        String user = props.getProperty("db.user", DEFAULT_USER);

        String password = props.getProperty(
            "db.password",
            DEFAULT_PASSWORD
        );

        // Debug
        System.out.println("=================================");
        System.out.println("Tentando conectar ao banco...");
        System.out.println("URL: " + url);
        System.out.println("Usuário: " + user);
        System.out.println("Senha: " + password);
        System.out.println("=================================");

        try {

            // Carrega driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Conecta ao MySQL
            Connection conn = DriverManager.getConnection(
                url,
                user,
                password
            );

            System.out.println("Conexão realizada com sucesso!");

            return conn;

        } catch (ClassNotFoundException e) {

            System.out.println(
                "Driver JDBC não encontrado: " + e.getMessage()
            );

            return null;

        } catch (SQLException e) {

            System.out.println(
                "Erro na conexão: " + e.getMessage()
            );

            return null;
        }
    }

    private static Properties loadProperties() {

        Properties props = new Properties();

        Path[] candidatePaths = new Path[] {

            // raiz do projeto
            Paths.get(
                System.getProperty("user.dir"),
                "database.properties"
            ),

            // src/database.properties para execução a partir da raiz do projeto
            Paths.get(
                System.getProperty("user.dir"),
                "src",
                "database.properties"
            ),

            // pasta acima
            Paths.get(
                System.getProperty("user.dir"),
                "..",
                "database.properties"
            )
        };

        for (Path path : candidatePaths) {

            if (Files.exists(path)) {

                try (
                    FileInputStream input =
                        new FileInputStream(path.toFile())
                ) {

                    props.load(input);

                    System.out.println(
                        "Configuração carregada de: "
                        + path.normalize()
                    );

                    return props;

                } catch (IOException e) {

                    System.out.println(
                        "Erro ao carregar database.properties: "
                        + e.getMessage()
                    );
                }
            }
        }

        System.out.println(
            "Arquivo database.properties não encontrado."
        );

        return props;
    }
}