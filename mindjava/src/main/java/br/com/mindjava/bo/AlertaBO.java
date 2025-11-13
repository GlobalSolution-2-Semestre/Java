package br.com.mindjava.bo;

import br.com.mindjava.beans.Alerta;
import br.com.mindjava.conexoes.ConexaoFactory;
import br.com.mindjava.dao.AlertaDAO;
import br.com.mindjava.excecoes.BusinessException;
import br.com.mindjava.excecoes.ExcecoesConexao;

import java.sql.Connection;
import java.util.List;

public class AlertaBO {

    private final AlertaDAO dao = new AlertaDAO();

    public List<Alerta> listar() throws ExcecoesConexao {
        try (Connection con = ConexaoFactory.getConnection()) {
            return dao.listar(con);
        } catch (Exception e) {
            throw new ExcecoesConexao("Erro ao listar alertas na camada BO", e);
        }
    }

    public void inserir(Alerta alerta) throws BusinessException, ExcecoesConexao {
        validar(alerta);
        try (Connection con = ConexaoFactory.getConnection()) {
            dao.inserir(alerta, con);
        } catch (Exception e) {
            throw new ExcecoesConexao("Erro ao inserir alerta na camada BO", e);
        }
    }

    public boolean deletar(int id) throws BusinessException, ExcecoesConexao {
        if (id <= 0) {
            throw new BusinessException("ID inválido para exclusão.");
        }
        try (Connection con = ConexaoFactory.getConnection()) {
            dao.deletar(id, con);
        } catch (Exception e) {
            throw new ExcecoesConexao("Erro ao deletar alerta na camada BO", e);
        }
        return false;
    }

    private void validar(Alerta alerta) throws BusinessException {
        if (alerta == null)
            throw new BusinessException("Objeto Alerta não pode ser nulo.");
        if (alerta.getTipoAlerta() == null || alerta.getTipoAlerta().trim().isEmpty())
            throw new BusinessException("Tipo do alerta é obrigatório.");
        if (alerta.getIdColaborador() <= 0)
            throw new BusinessException("ID do colaborador é obrigatório.");
    }
}
