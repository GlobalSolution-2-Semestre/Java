package br.com.mindjava.dao;
import br.com.mindjava.excecoes.ExcecoesConexao;
import br.com.mindjava.beans.Colaborador;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColaboradorDAO {



    public List<Colaborador> listar(Connection con) throws ExcecoesConexao {
        List<Colaborador> lista = new ArrayList<>();
        String sql = "SELECT ID_COLABORADOR, NOME, EMAIL, CARGO FROM TB_COLABORADOR";
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
            throw new ExcecoesConexao("Erro ao listar colaboradores", e);
        }
        return lista;
    }

    public void inserir(Colaborador c, Connection con) throws ExcecoesConexao {
        String sql = "INSERT INTO TB_COLABORADOR (NOME, EMAIL, CARGO) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, new String[]{"ID_COLABORADOR"})) {
            ps.setString(1, c.getNome());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getCargo());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    c.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new ExcecoesConexao("Erro ao inserir colaborador", e);
        }
    }

    public void atualizar(Colaborador c, Connection con) throws ExcecoesConexao {
        String sql = "UPDATE TB_COLABORADOR SET NOME = ?, EMAIL = ?, CARGO = ? WHERE ID_COLABORADOR = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getNome());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getCargo());
            ps.setInt(4, c.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ExcecoesConexao("Erro ao atualizar colaborador", e);
        }
    }

    public void deletar(int id, Connection con) throws ExcecoesConexao {
        String sql = "DELETE FROM TB_COLABORADOR WHERE ID_COLABORADOR = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ExcecoesConexao("Erro ao deletar colaborador", e);
        }
    }
}