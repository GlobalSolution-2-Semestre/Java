package br.com.mindjava.dao;

import br.com.mindjava.conexoes.ConexaoFactory;
import br.com.mindjava.beans.CheckinHumor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CheckinDAO {


    public void inserir(CheckinHumor ch, Connection cn) {
        String sql = "INSERT INTO TB_CHECKIN (ID_COLABORADOR, HUMOR, COMENTARIO) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConexaoFactory.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, ch.getIdColaborador());
            ps.setString(2, ch.getHumor());
            ps.setString(3, ch.getComentario());
            ps.executeUpdate();

            System.out.println(" Check-in inserido com sucesso!");
        } catch (SQLException e) {
            System.err.println(" Erro ao inserir check-in: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<CheckinHumor> listar(Connection cn) {
        List<CheckinHumor> lista = new ArrayList<>();
        String sql = "SELECT * FROM TB_CHECKIN ORDER BY DATA_REGISTRO DESC";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConexaoFactory.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                CheckinHumor ch = new CheckinHumor();
                ch.setId(rs.getInt("ID_CHECKIN"));
                ch.setIdColaborador(rs.getInt("ID_COLABORADOR"));
                ch.setDataRegistro(rs.getDate("DATA_REGISTRO").toLocalDate());
                ch.setHumor(rs.getString("HUMOR"));
                ch.setComentario(rs.getString("COMENTARIO"));
                lista.add(ch);
            }

        } catch (SQLException e) {
            System.err.println(" Erro ao listar check-ins: " + e.getMessage());
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
        String sql = "DELETE FROM TB_CHECKIN WHERE ID_CHECKIN = ?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConexaoFactory.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println(" Check-in removido com sucesso!");
        } catch (SQLException e) {
            System.err.println(" Erro ao deletar check-in: " + e.getMessage());
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