package br.com.mindjava.bo;

import br.com.mindjava.beans.Alerta;
import br.com.mindjava.dao.AlertaDAO;
import br.com.mindjava.excecoes.BusinessException;
import br.com.mindjava.excecoes.ExcecoesConexao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class AlertaBO {

    @Inject
    AlertaDAO dao;

    public List<Alerta> listar() throws ExcecoesConexao {
        return dao.listar();
    }

    public void inserir(Alerta alerta) throws ExcecoesConexao, BusinessException {
        validar(alerta);
        dao.inserir(alerta);
    }

    public boolean deletar(int id) throws ExcecoesConexao, BusinessException {
        if (id <= 0) {
            throw new BusinessException("ID inválido para exclusão.");
        }
        return dao.deletar(id); // retorna true ou false do DAO
    }

    private void validar(Alerta alerta) throws BusinessException {
        if (alerta == null)
            throw new BusinessException("Objeto Alerta não pode ser nulo");
        if (alerta.getTipoAlerta() == null || alerta.getTipoAlerta().trim().isEmpty())
            throw new BusinessException("Tipo do alerta é obrigatório");
        if (alerta.getIdColaborador() <= 0)
            throw new BusinessException("ID do colaborador é obrigatório e deve ser válido");
    }
}