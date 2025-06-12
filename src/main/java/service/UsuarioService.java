package service;

import dao.PersistenciaDacException;
import dao.UserDAO;

public class UsuarioService {

    UserDAO userDao = new UserDAO();

    public void testar() throws PersistenciaDacException{
        System.out.println(userDao.emailJaExiste("diego.marqueaaasdads@academico.ifpb.edu.r"));
    }

    public static void main(String args[]) throws PersistenciaDacException{
     UsuarioService teste = new UsuarioService();
     teste.testar();
}

}


