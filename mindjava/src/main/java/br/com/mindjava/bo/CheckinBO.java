package br.com.mindjava.bo;

import br.com.mindjava.beans.CheckinHumor;
import br.com.mindjava.conexoes.ConexaoFactory;
import br.com.mindjava.dao.CheckinDAO;
import br.com.mindjava.excecoes.BusinessException;
import br.com.mindjava.excecoes.ExcecoesConexao;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.Connection;
import java.util.List;

@ApplicationScoped
public class CheckinBO {

    private final CheckinDAO dao = new CheckinDAO();

    public List<CheckinHumor> listar() throws BusinessException, ExcecoesConexao {
        Connection cn = null;
        try {
            cn = ConexaoFactory.getConnection();
            return dao.listar(cn);
        } catch (Exception e) {
            throw new BusinessException("Erro ao listar check-ins: " + e.getMessage());
        } finally { try { if (cn != null) cn.close(); } catch (Exception ignored) {} }
    }

    public void inserir(CheckinHumor checkin) throws BusinessException, ExcecoesConexao {
        validarCheckin(checkin, false);
        Connection cn = null;
        try {
            cn = ConexaoFactory.getConnection();
            dao.inserir(checkin, cn);
        } catch (Exception e) {
            throw new BusinessException("Erro ao inserir check-in: " + e.getMessage());
        } finally { try { if (cn != null) cn.close(); } catch (Exception ignored) {} }
    }



    public void deletar(int idCheckin) throws BusinessException, ExcecoesConexao {
        if (idCheckin <= 0) throw new BusinessException("ID de check-in inválido.");
        Connection cn = null;
        try {
            cn = ConexaoFactory.getConnection();
            dao.deletar(idCheckin, cn);
        } catch (Exception e) {
            throw new BusinessException("Erro ao excluir check-in: " + e.getMessage());
        } finally { try { if (cn != null) cn.close(); } catch (Exception ignored) {} }
    }

    private void validarCheckin(CheckinHumor c, boolean exigirId) {
        if (c == null) throw new BusinessException("Objeto CheckinHumor não pode ser nulo.");
        if (exigirId && c.getId() <= 0) throw new BusinessException("ID inválido para atualização.");
        if (c.getIdColaborador() <= 0) throw new BusinessException("Colaborador obrigatório.");
        if (c.getHumor() == null || c.getHumor().trim().isEmpty())
            throw new BusinessException("Humor é obrigatório.");
    }
}
