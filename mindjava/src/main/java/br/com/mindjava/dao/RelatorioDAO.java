package br.com.mindjava.dao;

import br.com.mindjava.beans.Relatorio;
import br.com.mindjava.excecoes.ExcecoesConexao;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDAO {

    public void inserir(Relatorio r, Connection con) throws ExcecoesConexao {
        String sql = "INSERT INTO TB_RELATORIO (ID_COLABORADOR, MEDIA_HUMOR, RESUMO) VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, r.getIdColaborador());
            ps.setDouble(2, r.getMediaHumor());
            ps.setString(3, r.getResumoAnalise());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ExcecoesConexao("Erro ao inserir relatório.", e);
        }
    }

    public void deletar(int id, Connection con) throws ExcecoesConexao {
        String sql = "DELETE FROM TB_RELATORIO WHERE ID_RELATORIO = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ExcecoesConexao("Erro ao deletar relatório.", e);
        }
    }

    public List<Relatorio> listar(Connection con) throws ExcecoesConexao {
        String sql = "SELECT ID_RELATORIO, ID_COLABORADOR, DATA_GERACAO, MEDIA_HUMOR, RESUMO FROM TB_RELATORIO";
        List<Relatorio> lista = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Relatorio r = new Relatorio();
                r.setId(rs.getInt("ID_RELATORIO"));
                r.setIdColaborador(rs.getInt("ID_COLABORADOR"));
                r.setMediaHumor(rs.getDouble("MEDIA_HUMOR"));
                r.setResumoAnalise(rs.getString("RESUMO"));

                Date dt = rs.getDate("DATA_GERACAO");
                if (dt != null) {
                    r.setDataGeracao(LocalDate.from(LocalDateTime.ofInstant(dt.toInstant(), ZoneId.systemDefault())));
                }

                lista.add(r);
            }

        } catch (SQLException e) {
            throw new ExcecoesConexao("Erro ao listar relatórios.", e);
        }

        return lista;
    }
}
