package br.com.mindjava.conexoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {


    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    private static final String USER = "rm562396";
    private static final String PASSWORD = "230407";
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";


    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println(" Erro ao conectar ao banco: " + e.getMessage());
            throw new RuntimeException("Erro na conexão com o banco de dados", e);
        } catch (ClassNotFoundException e) {
            System.err.println(" Driver JDBC não encontrado: " + e.getMessage());
            throw new RuntimeException("Driver JDBC não encontrado", e);
        }
    }
}