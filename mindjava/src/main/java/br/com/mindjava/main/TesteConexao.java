package br.com.mindjava.main;

import br.com.mindjava.conexoes.ConexaoFactory;
import br.com.mindjava.excecoes.ExcecoesConexao;

import java.sql.Connection;

public class TesteConexao {

    public static void main(String[] args) throws ExcecoesConexao {

        Connection cn = null;

        try {
            cn = ConexaoFactory.getConnection();
            System.out.println(" Conectado com sucesso ao banco Oracle!");
        } catch (Exception e) {
            System.err.println(" Erro ao conectar:");
            e.printStackTrace();
            throw new ExcecoesConexao(e);
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                    System.out.println(" Conexão encerrada.");
                } catch (Exception e) {
                    System.err.println(" Erro ao fechar conexão:");
                    e.printStackTrace();
                }
            }
        }
    }
}
