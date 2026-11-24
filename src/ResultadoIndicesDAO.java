import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * DAO (Data Access Object): concentra os comandos de banco de dados.
 * O menu não precisa conhecer SQL, facilitando manutenção e testes.
 */
public class ResultadoIndicesDAO {
    /** Salva uma nova fotografia dos dados e devolve o ID criado pelo SQLite. */
    public long salvar(ResultadoIndices resultado) throws SQLException {
        String sql = "INSERT INTO resultados ("
            + "total_validos, nome_candidato, total_candidato, "
            + "percentual_candidato, satisfacao_media, total_saude, "
            + "percentual_saude, conclusao"
            + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoSQLite.abrir()) {
            // A transação garante que o registro seja salvo por inteiro ou não seja salvo.
            conexao.setAutoCommit(false);

            try (PreparedStatement comando = conexao.prepareStatement(
                    sql, Statement.RETURN_GENERATED_KEYS)) {

                comando.setInt(1, resultado.getTotalValidos());
                comando.setString(2, resultado.getNomeCandidato());
                comando.setInt(3, resultado.getTotalCandidato());
                comando.setString(4, resultado.getPercentualCandidato());
                comando.setString(5, resultado.getSatisfacaoMedia());
                comando.setInt(6, resultado.getTotalSaude());
                comando.setString(7, resultado.getPercentualSaude());
                comando.setString(8, resultado.gerarConclusao());

                comando.executeUpdate();

                long idGerado = 0;
                try (ResultSet chaves = comando.getGeneratedKeys()) {
                    if (chaves.next()) {
                        idGerado = chaves.getLong(1);
                    }
                }

                conexao.commit();
                return idGerado;
            } catch (SQLException erro) {
                conexao.rollback();
                throw erro;
            }
        }
    }

    /** Lista todas as análises, começando pela mais recente. */
    public List<ResultadoIndices> listarTodos() throws SQLException {
        String sql = "SELECT id, criado_em, total_validos, nome_candidato, "
            + "total_candidato, percentual_candidato, satisfacao_media, "
            + "total_saude, percentual_saude "
            + "FROM resultados ORDER BY id DESC";

        List<ResultadoIndices> resultados = new ArrayList<ResultadoIndices>();

        try (Connection conexao = ConexaoSQLite.abrir();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet linhas = comando.executeQuery()) {

            while (linhas.next()) {
                resultados.add(criarObjeto(linhas));
            }
        }

        return resultados;
    }

    /** Busca somente o último registro para continuar o trabalho. */
    public Optional<ResultadoIndices> buscarUltimo() throws SQLException {
        String sql = "SELECT id, criado_em, total_validos, nome_candidato, "
            + "total_candidato, percentual_candidato, satisfacao_media, "
            + "total_saude, percentual_saude "
            + "FROM resultados ORDER BY id DESC LIMIT 1";

        try (Connection conexao = ConexaoSQLite.abrir();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet linha = comando.executeQuery()) {

            if (linha.next()) {
                return Optional.of(criarObjeto(linha));
            }
        }

        return Optional.empty();
    }

    /** Busca um registro específico pelo ID (extensão opcional do desafio). */
    public Optional<ResultadoIndices> buscarPorId(long id) throws SQLException {
        String sql = "SELECT id, criado_em, total_validos, nome_candidato, "
            + "total_candidato, percentual_candidato, satisfacao_media, "
            + "total_saude, percentual_saude "
            + "FROM resultados WHERE id = ?";

        try (Connection conexao = ConexaoSQLite.abrir();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setLong(1, id);

            try (ResultSet linha = comando.executeQuery()) {
                if (linha.next()) {
                    return Optional.of(criarObjeto(linha));
                }
            }
        }

        return Optional.empty();
    }

    /** Converte a linha atual do banco em um objeto Java. */
    private ResultadoIndices criarObjeto(ResultSet linha) throws SQLException {
        return new ResultadoIndices(
            linha.getLong("id"),
            linha.getString("criado_em"),
            linha.getInt("total_validos"),
            linha.getString("nome_candidato"),
            linha.getInt("total_candidato"),
            linha.getString("percentual_candidato"),
            linha.getString("satisfacao_media"),
            linha.getInt("total_saude"),
            linha.getString("percentual_saude")
        );
    }
}
