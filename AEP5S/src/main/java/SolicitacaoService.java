import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolicitacaoService {

    public void criarTabela() {
        String sql = """
            CREATE TABLE IF NOT EXISTS solicitacao (
                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                nome VARCHAR(100),
                cpf VARCHAR(20),
                email VARCHAR(100),
                endereco VARCHAR(150),
                descricao VARCHAR(255),
                status VARCHAR(30),
                comentario VARCHAR(255)
            );
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar tabela", e);
        }
    }

    public void criar(Solicitacao solicitacao) {

        String sql = """
            INSERT INTO solicitacao
            (nome, cpf, email, endereco, descricao, status)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, solicitacao.getNome());
            stmt.setString(2, solicitacao.getCpf());
            stmt.setString(3, solicitacao.getEmail());
            stmt.setString(4, solicitacao.getEndereco());
            stmt.setString(5, solicitacao.getDescricao());
            stmt.setString(6, solicitacao.getStatus().name());

            stmt.executeUpdate();

            System.out.println("Solicitação criada com sucesso.");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar solicitação", e);
        }
    }



    public List<Solicitacao> listar() {

        List<Solicitacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM solicitacao";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Solicitacao s = new Solicitacao(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getString("endereco"),
                        rs.getLong("id"),
                        rs.getString("descricao"),
                        StatusSolicitacao.valueOf(rs.getString("status"))
                );

                lista.add(s);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar solicitações", e);
        }

        return lista;
    }

    public Solicitacao buscarPorProtocolo(long id) {

        String sql = "SELECT * FROM solicitacao WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Solicitacao(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getString("endereco"),
                        rs.getLong("id"),
                        rs.getString("descricao"),
                        StatusSolicitacao.valueOf(rs.getString("status"))
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar protocolo", e);
        }

        return null;
    }

    public void atualizarStatus(long id, StatusSolicitacao novoStatus) {

        String sql = "UPDATE solicitacao SET status = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoStatus.name());
            stmt.setLong(2, id);

            stmt.executeUpdate();

            System.out.println("Status atualizado com sucesso.");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar status", e);
        }
    }

    public void registrarComentario(long id, String comentario) {

        String sql = "UPDATE solicitacao SET comentario = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, comentario);
            stmt.setLong(2, id);

            stmt.executeUpdate();

            System.out.println("Comentário registrado com sucesso.");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao registrar comentário", e);
        }
    }
}