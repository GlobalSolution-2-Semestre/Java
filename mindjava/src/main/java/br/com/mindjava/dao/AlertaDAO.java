package br.com.mindjava.dao;

import br.com.mindjava.beans.Alerta;
import br.com.mindjava.excecoes.ExcecoesConexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlertaDAO {

    public void inserir(Alerta alerta, Connection cn) throws ExcecoesConexao {
        String sql = "INSERT INTO TB_ALERTA ( TIPO_ALERTA, ID_COLABORADOR , DESCRICAO) VALUES (?, ?, ?)";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, alerta.getTipoAlerta());
            ps.setInt(2, alerta.getIdColaborador());
            ps.setString(3, alerta.getDescricao());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ExcecoesConexao("Erro ao inserir alerta.", e);
        }
    }

    public void deletar(int id, Connection cn) throws ExcecoesConexao {
        String sql = "DELETE FROM TB_ALERTA WHERE ID_ALERTA = ?";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ExcecoesConexao("Erro ao deletar alerta.", e);
        }
    }

    public List<Alerta> listar(Connection cn) throws ExcecoesConexao {
        String sql = "SELECT ID_ALERTA, TIPO_ALERTA, ID_COLABORADOR, DESCRICAO, DATA_ENVIO FROM TB_ALERTA";
        List<Alerta> lista = new ArrayList<>();

        try (PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Alerta a = new Alerta();
                a.setId(rs.getInt("ID_ALERTA"));
                a.setTipoAlerta(rs.getString("TIPO_ALERTA"));
                a.setIdColaborador(rs.getInt("ID_COLABORADOR"));

                a.setDescricao(rs.getString("DESCRICAO"));
                lista.add(a);
            }

        } catch (SQLException e) {
            throw new ExcecoesConexao("Erro ao listar alertas.", e);
        }

        return lista;
    }
}