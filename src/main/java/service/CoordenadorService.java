package service;

import dao.CordenadorDAO;
import dao.PersistenciaDacException;
import model.Coordenador;

public class CoordenadorService {

    private CordenadorDAO cordenadorDAO = new CordenadorDAO();

    public void createCoordenador(Coordenador cordenador) throws PersistenciaDacException {
        cordenadorDAO.save(cordenador);
    }


    public void updateCoordenador(String email, String nome, String senha) throws PersistenciaDacException {

        Coordenador coordenadorAntigo = cordenadorDAO.getByID(email);
        coordenadorAntigo.setEmail(email);
        coordenadorAntigo.setNome(nome);
        coordenadorAntigo.setSenha(senha);
        this.cordenadorDAO.save(coordenadorAntigo);
    }

    public void getByIdCoordenador(String email) throws PersistenciaDacException {
        this.cordenadorDAO.getByID(email);
    }

    public void getALL() throws PersistenciaDacException {
        this.cordenadorDAO.getAll();
    }


    public void delete(Coordenador cordenador) throws PersistenciaDacException {
        this.cordenadorDAO.delete(cordenador);
    }

    public boolean login(String email, String senha)  {

        Coordenador coordenador;
        try{
            coordenador = this.cordenadorDAO.getByID(email);
        }
        catch (PersistenciaDacException e ){
         return false;
        }

        if(coordenador.getSenha().equals(senha))
            return true;
        return false;
    }

}
