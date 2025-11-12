package br.com.mindjava.bo;

import br.com.mindjava.beans.Relatorio;
import br.com.mindjava.conexoes.ConexaoFactory;
import br.com.mindjava.dao.RelatorioDAO;
import br.com.mindjava.excecoes.BusinessException;
import br.com.mindjava.excecoes.ExcecoesConexao;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.Connection;
import java.util.List;

@ApplicationScoped
public class RelatorioBO {

    private final RelatorioDAO dao = new RelatorioDAO();

    // LISTAR RELATÓRIOS
    public List<Relatorio> listar() throws BusinessException, ExcecoesConexao {
        Connection cn = null;
        try {
            cn = ConexaoFactory.getConnection();
            return dao.listar(cn);
        } catch (Exception e) {
            throw new BusinessException("Erro ao listar relatórios: " + e.getMessage());
        } finally {
            try { if (cn != null) cn.close(); } catch (Exception ignored) {}
        }
    }

    // INSERIR RELATÓRIO
    public void inserir(Relatorio relatorio) throws BusinessException, ExcecoesConexao {
        validarRelatorio(relatorio, false);
        Connection cn = null;
        try {
            cn = ConexaoFactory.getConnection();
            dao.inserir(relatorio, cn);
        } catch (Exception e) {
            throw new BusinessException("Erro ao inserir relatório: " + e.getMessage());
        } finally {
            try { if (cn != null) cn.close(); } catch (Exception ignored) {}
        }
    }


    // EXCLUIR RELATÓRIO
    public void deletar(int idRelatorio) throws BusinessException, ExcecoesConexao {
        if (idRelatorio <= 0) throw new BusinessException("ID de relatório inválido.");
        Connection cn = null;
        try {
            cn = ConexaoFactory.getConnection();
            dao.deletar(idRelatorio, cn);
        } catch (Exception e) {
            throw new BusinessException("Erro ao excluir relatório: " + e.getMessage());
        } finally {
            try { if (cn != null) cn.close(); } catch (Exception ignored) {}
        }
    }

    // VALIDAÇÃO
    private void validarRelatorio(Relatorio r, boolean exigirId) throws BusinessException {
        if (r == null) throw new BusinessException("Objeto Relatório não pode ser nulo.");
        if (exigirId && r.getId() <= 0)
            throw new BusinessException("ID de relatório inválido para atualização.");
        if (r.getIdColaborador() <= 0)
            throw new BusinessException("ID de colaborador é obrigatório.");
        if (r.getMediaHumor() < 0 || r.getMediaHumor() > 10)
            throw new BusinessException("A média de humor deve estar entre 0 e 10.");
    }
}
