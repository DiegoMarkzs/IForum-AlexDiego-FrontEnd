package dac;

import dao.DenunciaDAO;
import dao.UserDAO;
import model.Aluno;
import model.CategoriaDenuncia;
import model.Denuncia;
import model.DenunciaAnonima;
import model.DenunciaPublica;
import model.User;

public class MainSaveDenuncia {

    public static void main(String args[]) throws Exception{
        DenunciaDAO dao = new DenunciaDAO();
        UserDAO daoUser = new UserDAO();


        try{
        Denuncia denuncia = new DenunciaAnonima();
		denuncia.setAssunto("Comida da cantina poderia ter mais sal");
        denuncia.setTitulo("Cantina");
        denuncia.setCategoria(CategoriaDenuncia.ensino);

        

        dao.save(denuncia);
        }finally{
            dao.close();
        }

    }
}
