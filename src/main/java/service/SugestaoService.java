package service;

import dao.PersistenciaDacException;
import dao.SugestaoDAO;
import dao.UserDAO;
import model.Sugestao;

public class SugestaoService {


    private SugestaoDAO sugestaoDAO = new SugestaoDAO();
    private UserDAO UserDAO = new UserDAO();

    private void teste() throws PersistenciaDacException{
        Sugestao sugestao = new Sugestao();
        sugestao.setStatus("PENDENTE");
        sugestao.setTexto("Teste");
        sugestao.setUsuario(UserDAO.getByID("diego.marqueaaasdads@academico.ifpb.edu.br"));
        sugestaoDAO.save(sugestao);
        
        
    }

    public static void main(String[]args) throws PersistenciaDacException{
        SugestaoService sugestao = new SugestaoService();
        sugestao.teste();
    }

}
