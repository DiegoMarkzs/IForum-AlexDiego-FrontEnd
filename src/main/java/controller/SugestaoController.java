package controller;

import java.util.List;

import dao.PersistenciaDacException;
import dao.SugestaoDAO;
import model.Sugestao;

public class SugestaoController {

    private SugestaoDAO sugestaoDAO = new SugestaoDAO();

    public void enviarSugestao(Sugestao sugestao) throws Exception {
        if (sugestao == null || sugestao.getTexto() == null || sugestao.getUsuario() == null) {
            throw new Exception("Sugestão inválida.");
        }

        try {
            sugestaoDAO.save(sugestao);
        } catch (PersistenciaDacException e) {
            throw new Exception("Erro ao enviar sugestão.", e);
        }
    }

    public List<Sugestao> listarTodas() throws Exception {
        try {
            return sugestaoDAO.getAll();
        } catch (PersistenciaDacException e) {
            throw new Exception("Erro ao listar sugestões.", e);
        }
    }

    public List<Sugestao> listarDoUsuario(String emailUsuario) throws Exception {
        try {
            return sugestaoDAO.getByUsuario(emailUsuario);
        } catch (PersistenciaDacException e) {
            throw new Exception("Erro ao buscar sugestões do usuário.", e);
        }
    }

    public void atualizarStatus(Long id, String novoStatus) throws Exception {
        try {
            Sugestao s = sugestaoDAO.getById(id);
            if (s == null) {
                throw new Exception("Sugestão não encontrada.");
            }
            s.setStatus(novoStatus);
            sugestaoDAO.update(s);
        } catch (PersistenciaDacException e) {
            throw new Exception("Erro ao atualizar status da sugestão.", e);
        }
    }
}

