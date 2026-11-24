import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Centraliza a conexão com o SQLite.
 *
 * O banco é um único arquivo chamado indices.db. Ele é criado automaticamente
 * dentro da pasta dados quando o programa é executado pela primeira vez.
 */
public final class ConexaoSQLite {
    private static final Path PASTA_DADOS = Paths.get("dados").toAbsolutePath();
    private static final Path ARQUIVO_BANCO = PASTA_DADOS.resolve("indices.db");
    private static final String URL = "jdbc:sqlite:" + ARQUIVO_BANCO;

    private ConexaoSQLite() {
    }

    /** Prepara a pasta, carrega o driver e cria a tabela quando necessário. */
    public static void inicializarBanco() throws SQLException {
        try {
            Files.createDirectories(PASTA_DADOS);
            Class.forName("org.sqlite.JDBC");
        } catch (IOException erro) {
            throw new SQLException("Não foi possível criar a pasta de dados.", erro);
        } catch (ClassNotFoundException erro) {
            throw new SQLException(
                "Driver SQLite não encontrado. Execute o projeto com os arquivos da pasta lib.",
                erro
            );
        }

        String sql = "CREATE TABLE IF NOT EXISTS resultados ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "total_validos INTEGER NOT NULL, "
            + "nome_candidato TEXT NOT NULL, "
            + "total_candidato INTEGER NOT NULL, "
            + "percentual_candidato TEXT NOT NULL, "
            + "satisfacao_media TEXT NOT NULL, "
            + "total_saude INTEGER NOT NULL, "
            + "percentual_saude TEXT NOT NULL, "
            + "conclusao TEXT NOT NULL, "
            + "criado_em TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP"
            + ")";

        try (Connection conexao = abrir();
             Statement comando = conexao.createStatement()) {
            comando.execute(sql);
        }
    }

    /** Abre uma nova conexão. Cada operação do DAO fecha a conexão ao terminar. */
    public static Connection abrir() throws SQLException {
        Connection conexao = DriverManager.getConnection(URL);

        try (Statement comando = conexao.createStatement()) {
            comando.execute("PRAGMA busy_timeout = 5000");
        }

        return conexao;
    }

    /** Retorna o caminho do arquivo apenas para informar o usuário no menu. */
    public static String getCaminhoBanco() {
        return ARQUIVO_BANCO.toString();
    }
}
