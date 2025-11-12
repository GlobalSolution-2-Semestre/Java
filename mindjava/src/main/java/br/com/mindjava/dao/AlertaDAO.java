package br.com.mindjava.dao;

import br.com.mindjava.beans.Alerta;
import br.com.mindjava.excecoes.ExcecoesConexao;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AlertaDAO {

    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static final String USER = "RM562396";
    private static final String PASSWORD = "230407";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // ---------------------- LISTAR ----------------------
    public List<Alerta> listar() throws ExcecoesConexao {
        List<Alerta> lista = new ArrayList<>();
        String sql = "SELECT ID_ALERTA, ID_COLABORADOR, TIPO_ALERTA, DESCRICAO, DATA_ENVIO FROM TB_ALERTA ORDER BY ID_ALERTA";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Alerta a = new Alerta();
                a.setId(rs.getInt("ID_ALERTA"));
                a.setIdColaborador(rs.getInt("ID_COLABORADOR"));
                a.setTipoAlerta(rs.getString("TIPO_ALERTA"));
                a.setDescricao(rs.getString("DESCRICAO"));
                Timestamp ts = rs.getTimestamp("DATA_ENVIO");
                if (ts != null)
                    a.setDataEnvio(ts.toLocalDateTime());
                lista.add(a);
            }

        } catch (SQLException e) {
            throw new ExcecoesConexao("Erro ao listar alertas", e);
        } finally {
            fecharConexao(rs, ps, con);
        }

        return lista;
    }


    public void inserir(Alerta alerta) throws ExcecoesConexao {
        String sql = "INSERT INTO TB_ALERTA (ID_COLABORADOR, TIPO_ALERTA, DESCRICAO) VALUES (?, ?, ?)";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            ps = con.prepareStatement(sql, new String[]{"ID_ALERTA"});

            ps.setInt(1, alerta.getIdColaborador());
            ps.setString(2, alerta.getTipoAlerta());
            ps.setString(3, alerta.getDescricao());
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                alerta.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new ExcecoesConexao("Erro ao inserir alerta", e);
        } finally {
            fecharConexao(rs, ps, con);
        }
    }


    public boolean deletar(int id) throws ExcecoesConexao {
        String sql = "DELETE FROM TB_ALERTA WHERE ID_ALERTA = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int linhas = ps.executeUpdate();

            System.out.println("[DEBUG] DELETE TB_ALERTA id=" + id + " | Linhas afetadas: " + linhas);
            return linhas > 0;

        } catch (SQLException e) {
            System.err.println("[ERRO] Falha ao deletar alerta: " + e.getMessage());
            throw new ExcecoesConexao("Erro ao deletar alerta", e);
        }
    }


    private void fecharConexao(ResultSet rs, PreparedStatement ps, Connection con) {
        try {
            if (rs != null && !rs.isClosed()) rs.close();
        } catch (SQLException e) {
            System.err.println(" Erro ao fechar ResultSet: " + e.getMessage());
        }
        try {
            if (ps != null && !ps.isClosed()) ps.close();
        } catch (SQLException e) {
            System.err.println("️ Erro ao fechar PreparedStatement: " + e.getMessage());
        }
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println(" Conexão Oracle fechada com sucesso.");
            }
        } catch (SQLException e) {
            System.err.println("️ Erro ao fechar conexão: " + e.getMessage());
        }
    }
}