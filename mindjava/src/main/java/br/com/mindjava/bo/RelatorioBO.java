package br.com.mindjava.bo;

import br.com.mindjava.beans.Relatorio;
import br.com.mindjava.conexoes.ConexaoFactory;
import br.com.mindjava.dao.RelatorioDAO;
import br.com.mindjava.excecoes.BusinessException;
import br.com.mindjava.excecoes.ExcecoesConexao;

import java.sql.Connection;
import java.util.List;

public class RelatorioBO {

    private final RelatorioDAO dao = new RelatorioDAO();

    public List<Relatorio> listar() throws ExcecoesConexao {
        try (Connection con = ConexaoFactory.getConnection()) {
            return dao.listar(con);
        } catch (Exception e) {
            throw new ExcecoesConexao("Erro ao listar relatórios.", e);
        }
    }

    public void inserir(Relatorio r) throws BusinessException, ExcecoesConexao {
        validar(r, false);
        try (Connection con = ConexaoFactory.getConnection()) {
            dao.inserir(r, con);
        } catch (Exception e) {
            throw new ExcecoesConexao("Erro ao inserir relatório.", e);
        }
    }

    public void deletar(int id) throws BusinessException, ExcecoesConexao {
        if (id <= 0) {
            throw new BusinessException("ID inválido para exclusão.");
        }
        try (Connection con = ConexaoFactory.getConnection()) {
            dao.deletar(id, con);
        } catch (Exception e) {
            throw new ExcecoesConexao("Erro ao excluir relatório.", e);
        }
    }

    private void validar(Relatorio r, boolean exigirId) throws BusinessException {
        if (r == null)
            throw new BusinessException("Objeto Relatório não pode ser nulo.");
        if (exigirId && r.getId() <= 0)
            throw new BusinessException("ID inválido para atualização.");
        if (r.getIdColaborador() <= 0)
            throw new BusinessException("ID de colaborador é obrigatório.");
        if (r.getMediaHumor() < 0 || r.getMediaHumor() > 10)
            throw new BusinessException("A média de humor deve estar entre 0 e 10.");
    }
}
