package dac;

import dao.DenunciaDAO;
import model.Aluno;
import model.Denuncia;
import model.DenunciaPublica;
import model.User;

public class MainDeleteDenuncia {
    public static void main(String[] args) throws Exception {
		DenunciaDAO dao = new DenunciaDAO();
		try {
			// Primeiro salvar
			User usuario = new Aluno();
        	usuario.setEmail("diego.marques@academico.ifpb.edu.br");
			
			Denuncia denuncia = new DenunciaPublica(usuario);
			denuncia.setAssunto("Ar condicionado pifou");
           denuncia.setTitulo("Equipamento defeituoso");

			dao.save(denuncia);

			System.out.println(dao.getAll().size());

			// Depois apagar

			dao.delete(denuncia);

			System.out.println(dao.getAll().size());
		} finally {
			dao.close();
		}
	}


}
