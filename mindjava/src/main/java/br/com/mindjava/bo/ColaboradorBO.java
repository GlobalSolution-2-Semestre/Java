package br.com.mindjava.bo;

import br.com.mindjava.dao.ColaboradorDAO;
import br.com.mindjava.excecoes.BusinessException;
import br.com.mindjava.excecoes.ExcecoesConexao;
import br.com.mindjava.beans.Colaborador;
import br.com.mindjava.conexoes.ConexaoFactory;
import java.sql.Connection;
import java.util.List;

public class ColaboradorBO {

    private final ColaboradorDAO dao = new ColaboradorDAO();

    public List<Colaborador> listar() throws ExcecoesConexao {
        try (Connection con = ConexaoFactory.getConnection()) {
            return dao.listar(con);
        } catch (Exception e) {
            throw new ExcecoesConexao("Erro ao listar colaboradores na camada BO", e);
        }
    }

    public void inserir(Colaborador c) throws ExcecoesConexao, BusinessException {
        validarColaborador(c, false);
        try (Connection con = ConexaoFactory.getConnection()) {
            dao.inserir(c, con);
        } catch (Exception e) {
            throw new ExcecoesConexao("Erro ao inserir colaborador na camada BO", e);
        }
    }

    public void atualizar(Colaborador c) throws ExcecoesConexao, BusinessException {
        validarColaborador(c, true);
        try (Connection con = ConexaoFactory.getConnection()) {
            dao.atualizar(c, con);
        } catch (Exception e) {
            throw new ExcecoesConexao("Erro ao atualizar colaborador na camada BO", e);
        }
    }

    public void deletar(int id) throws ExcecoesConexao, BusinessException {
        if (id <= 0) throw new BusinessException("ID inválido para exclusão.");
        try (Connection con = ConexaoFactory.getConnection()) {
            dao.deletar(id, con);
        } catch (Exception e) {
            throw new ExcecoesConexao("Erro ao deletar colaborador na camada BO", e);
        }
    }

    private void validarColaborador(Colaborador c, boolean exigirId) {
        if (c == null) throw new BusinessException("Objeto Colaborador não pode ser nulo.");
        if (exigirId && c.getId() <= 0) throw new BusinessException("ID inválido para atualização.");
        if (c.getNome() == null || c.getNome().trim().isEmpty()) throw new BusinessException("Nome é obrigatório.");
        if (c.getEmail() == null || c.getEmail().trim().isEmpty()) throw new BusinessException("E-mail é obrigatório.");
        if (c.getCargo() == null || c.getCargo().trim().isEmpty()) throw new BusinessException("Cargo é obrigatório.");
    }
}
