package br.com.mindjava.conexoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.mindjava.excecoes.ExcecoesConexao;

public class ConexaoFactory {

    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    private static final String USER = "rm562396";
    private static final String PASSWORD = "230407";
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";


    public static Connection getConnection() throws ExcecoesConexao {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new ExcecoesConexao("Driver JDBC Oracle não encontrado.", e);
        } catch (SQLException e) {
            throw new ExcecoesConexao("Erro ao conectar ao banco Oracle.", e);
        }
    }

    public Connection conexao() throws ExcecoesConexao {
        return getConnection();
    }
}
