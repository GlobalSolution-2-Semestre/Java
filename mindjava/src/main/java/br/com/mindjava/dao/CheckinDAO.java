package br.com.mindjava.dao;

import br.com.mindjava.beans.CheckinHumor;
import br.com.mindjava.excecoes.ExcecoesConexao;

import java.sql.*;
import java.time.LocalDate;
// Removidas importações desnecessárias de LocalDateTime e ZoneId
import java.util.ArrayList;
import java.util.List;

public class CheckinDAO {

    public void inserir(CheckinHumor checkin, Connection con) throws ExcecoesConexao {
        String sql = "INSERT INTO TB_CHECKIN (ID_COLABORADOR, HUMOR, COMENTARIO) VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, checkin.getIdColaborador());
            ps.setString(2, checkin.getHumor());
            ps.setString(3, checkin.getComentario());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ExcecoesConexao("Erro ao inserir check-in.", e);
        }
    }

    public void deletar(int id, Connection con) throws ExcecoesConexao {
        String sql = "DELETE FROM TB_CHECKIN WHERE ID_CHECKIN = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ExcecoesConexao("Erro ao deletar check-in.", e);
        }
    }

    public List<CheckinHumor> listar(Connection con) throws ExcecoesConexao {
        String sql = "SELECT ID_CHECKIN, ID_COLABORADOR, DATA_REGISTRO, HUMOR, COMENTARIO FROM TB_CHECKIN";
        List<CheckinHumor> lista = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CheckinHumor c = new CheckinHumor();
                c.setId(rs.getInt("ID_CHECKIN"));
                c.setIdColaborador(rs.getInt("ID_COLABORADOR"));
                c.setHumor(rs.getString("HUMOR"));
                c.setComentario(rs.getString("COMENTARIO"));

                // --- CORREÇÃO AQUI ---
                // Pega o java.sql.Date do banco
                Date dt = rs.getDate("DATA_REGISTRO");
                if (dt != null) {
                    // Converte diretamente para java.time.LocalDate
                    c.setDataRegistro(dt.toLocalDate());
                }
                // --- FIM DA CORREÇÃO ---

                lista.add(c);
            }

        } catch (SQLException e) {
            throw new ExcecoesConexao("Erro ao listar check-ins.", e);
        }

        return lista;
    }
}