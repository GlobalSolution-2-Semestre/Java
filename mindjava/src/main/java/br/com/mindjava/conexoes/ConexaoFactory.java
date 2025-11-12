package br.com.mindjava.conexoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

    private static final String URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";


    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (URL == null || USER == null || PASSWORD == null) {
            System.err.println("ERRO FATAL: Variáveis de ambiente DB_URL, DB_USER, ou DB_PASSWORD não estão configuradas no Render!");
            throw new RuntimeException("Erro de configuração: Variáveis de ambiente não encontradas.");
        }

        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}