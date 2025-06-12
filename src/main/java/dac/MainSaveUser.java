package dac;

import dao.UserDAO;
import model.Aluno;
import model.User;

public class MainSaveUser {

    public static void main(String args[]) throws Exception{
        UserDAO dao = new UserDAO();

        try{
        User usuario = new Aluno();
       //User usuario = new Funcionario();

        usuario.setEmail("diego.marqueaaasdads@academico.ifpb.edu.br");
        usuario.setNome("Alex");
        usuario.setSobrenome("Marques");
        usuario.setSenha("123");
        
        System.out.println(usuario);

        dao.save(usuario);
        }finally{
            dao.close();
        }

    }
}
