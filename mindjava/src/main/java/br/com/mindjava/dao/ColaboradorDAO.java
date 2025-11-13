package br.com.mindjava.dao;

import br.com.mindjava.beans.Colaborador;
import br.com.mindjava.excecoes.ExcecoesConexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColaboradorDAO {

    public void inserir(Colaborador colab, Connection con) throws ExcecoesConexao {
        String sql = "INSERT INTO TB_COLABORADOR (NOME, EMAIL, CARGO) VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, colab.getNome());
            ps.setString(2, colab.getEmail());
            ps.setString(3, colab.getCargo());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ExcecoesConexao("Erro ao inserir colaborador.", e);
        }
    }

    public void atualizar(Colaborador colab, Connection con) throws ExcecoesConexao {
        String sql = "UPDATE TB_COLABORADOR SET NOME = ?, EMAIL = ?, CARGO = ? WHERE ID_COLABORADOR = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, colab.getNome());
            ps.setString(2, colab.getEmail());
            ps.setString(3, colab.getCargo());
            ps.setInt(4, colab.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ExcecoesConexao("Erro ao atualizar colaborador.", e);
        }
    }

    public void deletar(int id, Connection con) throws ExcecoesConexao {
        String sql = "DELETE FROM TB_COLABORADOR WHERE ID_COLABORADOR = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ExcecoesConexao("Erro ao deletar colaborador.", e);
        }
    }

    public List<Colaborador> listar(Connection con) throws ExcecoesConexao {
        String sql = "SELECT ID_COLABORADOR, NOME, EMAIL, CARGO FROM TB_COLABORADOR";
        List<Colaborador> lista = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Colaborador c = new Colaborador();
                c.setId(rs.getInt("ID_COLABORADOR"));
                c.setNome(rs.getString("NOME"));
                c.setEmail(rs.getString("EMAIL"));
                c.setCargo(rs.getString("CARGO"));
                lista.add(c);
            }

        } catch (SQLException e) {
            throw new ExcecoesConexao("Erro ao listar colaboradores.", e);
        }
        return lista;
    }
}
