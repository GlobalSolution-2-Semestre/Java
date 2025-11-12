package br.com.mindjava.dao;

import br.com.mindjava.excecoes.ExcecoesConexao;
import br.com.mindjava.beans.Colaborador;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ColaboradorDAO {

    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static final String USER = "RM562396";
    private static final String PASSWORD = "230407";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


    public List<Colaborador> listar() throws ExcecoesConexao {
        List<Colaborador> lista = new ArrayList<>();
        String sql = "SELECT ID_COLABORADOR, NOME, EMAIL, CARGO FROM TB_COLABORADOR";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Colaborador c = new Colaborador();
                c.setId(rs.getInt("ID_COLABORADOR"));
                c.setNome(rs.getString("NOME"));
                c.setEmail(rs.getString("EMAIL"));
                c.setCargo(rs.getString("CARGO"));
                lista.add(c);
            }

            System.out.println("[DEBUG] Colaboradores listados com sucesso. Total: " + lista.size());

        } catch (SQLException e) {
            System.err.println("[ERRO] Falha ao listar colaboradores: " + e.getMessage());
            throw new ExcecoesConexao("Erro ao listar colaboradores", e);
        }

        return lista;
    }


    public void inserir(Colaborador c) throws ExcecoesConexao {
        String sql = "INSERT INTO TB_COLABORADOR (NOME, EMAIL, CARGO) VALUES (?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql, new String[]{"ID_COLABORADOR"})) {

            ps.setString(1, c.getNome());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getCargo());

            int linhas = ps.executeUpdate();
            System.out.println("[DEBUG] Inserção realizada. Linhas afetadas: " + linhas);

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    c.setId(rs.getInt(1));
                    System.out.println("[DEBUG] ID gerado: " + c.getId());
                }
            }

        } catch (SQLException e) {
            System.err.println("[ERRO] Falha ao inserir colaborador: " + e.getMessage());
            throw new ExcecoesConexao("Erro ao inserir colaborador", e);
        }
    }


    public void atualizar(Colaborador c) throws ExcecoesConexao {
        String sql = "UPDATE TB_COLABORADOR SET NOME = ?, EMAIL = ?, CARGO = ? WHERE ID_COLABORADOR = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNome());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getCargo());
            ps.setInt(4, c.getId());

            int linhas = ps.executeUpdate();
            System.out.println("[DEBUG] Atualização concluída. Linhas afetadas: " + linhas);

        } catch (SQLException e) {
            System.err.println("[ERRO] Falha ao atualizar colaborador: " + e.getMessage());
            throw new ExcecoesConexao("Erro ao atualizar colaborador", e);
        }
    }


    public void deletar(int id) throws ExcecoesConexao {
        String sql = "DELETE FROM TB_COLABORADOR WHERE ID_COLABORADOR = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int linhas = ps.executeUpdate();

            System.out.println("[DEBUG] Exclusão realizada. Linhas afetadas: " + linhas);
            if (linhas == 0) {
                System.out.println("[WARN] Nenhum colaborador encontrado com ID " + id);
            }

        } catch (SQLException e) {
            System.err.println("[ERRO] Falha ao deletar colaborador: " + e.getMessage());
            throw new ExcecoesConexao("Erro ao deletar colaborador", e);
        }
    }
}