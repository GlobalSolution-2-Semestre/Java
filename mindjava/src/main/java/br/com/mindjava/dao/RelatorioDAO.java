package br.com.mindjava.dao;

import br.com.mindjava.conexoes.ConexaoFactory;
import br.com.mindjava.beans.Relatorio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDAO {

    public void inserir(Relatorio r, Connection cn) {
        String sql = "INSERT INTO TB_RELATORIO (ID_COLABORADOR, MEDIA_HUMOR, RESUMO) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConexaoFactory.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, r.getIdColaborador());
            ps.setDouble(2, r.getMediaHumor());
            ps.setString(3, r.getResumoAnalise());
            ps.executeUpdate();

            System.out.println(" Relatório inserido com sucesso!");
        } catch (SQLException e) {
            System.err.println(" Erro ao inserir relatório: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Relatorio> listar(Connection cn) {
        List<Relatorio> lista = new ArrayList<>();
        String sql = "SELECT * FROM TB_RELATORIO ORDER BY DATA_GERACAO DESC";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConexaoFactory.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Relatorio r = new Relatorio();
                r.setId(rs.getInt("ID_RELATORIO"));
                r.setIdColaborador(rs.getInt("ID_COLABORADOR"));
                r.setDataGeracao(rs.getDate("DATA_GERACAO").toLocalDate());
                r.setMediaHumor(rs.getDouble("MEDIA_HUMOR"));
                r.setResumoAnalise(rs.getString("RESUMO"));
                lista.add(r);
            }

        } catch (SQLException e) {
            System.err.println(" Erro ao listar relatórios: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

    public void deletar(int id, Connection cn) {
        String sql = "DELETE FROM TB_RELATORIO WHERE ID_RELATORIO = ?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConexaoFactory.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println(" Relatório removido com sucesso!");
        } catch (SQLException e) {
            System.err.println(" Erro ao deletar relatório: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}