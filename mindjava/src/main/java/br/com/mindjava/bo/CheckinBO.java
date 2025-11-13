package br.com.mindjava.bo;

import br.com.mindjava.beans.CheckinHumor;
import br.com.mindjava.conexoes.ConexaoFactory;
import br.com.mindjava.dao.CheckinDAO;
import br.com.mindjava.excecoes.BusinessException;
import br.com.mindjava.excecoes.ExcecoesConexao;

import java.sql.Connection;
import java.util.List;

public class CheckinBO {

    private final CheckinDAO dao = new CheckinDAO();

    public List<CheckinHumor> listar() throws ExcecoesConexao {
        try (Connection con = ConexaoFactory.getConnection()) {
            return dao.listar(con);
        } catch (Exception e) {
            throw new ExcecoesConexao("Erro ao listar check-ins.", e);
        }
    }

    public void inserir(CheckinHumor checkin) throws BusinessException, ExcecoesConexao {
        validar(checkin, false);
        try (Connection con = ConexaoFactory.getConnection()) {
            dao.inserir(checkin, con);
        } catch (Exception e) {
            throw new ExcecoesConexao("Erro ao inserir check-in.", e);
        }
    }

    public void deletar(int id) throws BusinessException, ExcecoesConexao {
        if (id <= 0) {
            throw new BusinessException("ID inválido para exclusão.");
        }
        try (Connection con = ConexaoFactory.getConnection()) {
            dao.deletar(id, con);
        } catch (Exception e) {
            throw new ExcecoesConexao("Erro ao excluir check-in.", e);
        }
    }

    private void validar(CheckinHumor c, boolean exigirId) throws BusinessException {
        if (c == null)
            throw new BusinessException("Objeto CheckinHumor não pode ser nulo.");
        if (exigirId && c.getId() <= 0)
            throw new BusinessException("ID inválido para atualização.");
        if (c.getIdColaborador() <= 0)
            throw new BusinessException("ID do colaborador é obrigatório.");
        if (c.getHumor() == null || c.getHumor().trim().isEmpty())
            throw new BusinessException("Humor é obrigatório.");
    }
}
