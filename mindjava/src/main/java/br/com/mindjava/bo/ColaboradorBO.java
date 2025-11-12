package br.com.mindjava.bo;

import br.com.mindjava.dao.ColaboradorDAO;
import br.com.mindjava.excecoes.BusinessException;
import br.com.mindjava.excecoes.ExcecoesConexao;
import br.com.mindjava.beans.Colaborador;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ColaboradorBO {

    @Inject
    ColaboradorDAO dao;

    public List<Colaborador> listar() throws ExcecoesConexao {
        return dao.listar();
    }

    public void inserir(Colaborador c) throws ExcecoesConexao, BusinessException {
        System.out.println("[DEBUG] BO.inserir() chamado com: " + c);

        validarColaborador(c, false);
        dao.inserir(c);
    }

    public void atualizar(Colaborador c) throws ExcecoesConexao, BusinessException {
        validarColaborador(c, true);
        dao.atualizar(c);
    }

    public void deletar(int id) throws ExcecoesConexao, BusinessException {
        if (id <= 0) throw new BusinessException("ID inválido para exclusão.");
        dao.deletar(id);
    }

    // ---------------------- Validação centralizada ----------------------
    private void validarColaborador(Colaborador c, boolean exigirId) {
        if (c == null) throw new BusinessException("Objeto Colaborador não pode ser nulo.");
        if (exigirId && c.getId() <= 0) throw new BusinessException("ID inválido para atualização.");
        if (c.getNome() == null || c.getNome().trim().isEmpty()) throw new BusinessException("Nome é obrigatório.");
        if (c.getEmail() == null || c.getEmail().trim().isEmpty()) throw new BusinessException("E-mail é obrigatório.");
        if (c.getCargo() == null || c.getCargo().trim().isEmpty()) throw new BusinessException("Cargo é obrigatório.");
    }
}