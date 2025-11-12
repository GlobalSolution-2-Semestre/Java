package br.com.mindjava.main;

import java.sql.Connection;

import br.com.mindjava.conexoes.ConexaoFactory;
import br.com.mindjava.excecoes.ExcecoesConexao;

public class TesteConexoes {
    public static void main(String[] args) {
        Connection cn = null;

        try {
            cn = ConexaoFactory.getConnection();
            System.out.println(" Conectado com sucesso ao banco Oracle!");
        } catch (Exception e) {
            System.err.println(" Erro ao conectar: " + e.getMessage());
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                    System.out.println(" Conexão encerrada.");
                } catch (Exception e) {
                    System.err.println("️ Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }
    }
}
