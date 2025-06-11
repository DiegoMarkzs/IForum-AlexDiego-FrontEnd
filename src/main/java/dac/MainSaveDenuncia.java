package dac;

import dao.DenunciaDAO;
import model.Denuncia;

public class MainSaveDenuncia {

    public static void main(String args[]) throws Exception{
        DenunciaDAO dao = new DenunciaDAO();

        try{
        Denuncia denuncia = new Denuncia();
		denuncia.setAssunto("Comida da cantina poderia ter mais sal");
        denuncia.setTitulo("Cantina");

        
        
        System.out.println(denuncia);

        dao.save(denuncia);
        }finally{
            dao.close();
        }

    }
}
