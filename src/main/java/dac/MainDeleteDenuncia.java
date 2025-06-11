package dac;

import dao.DenunciaDAO;
import model.Denuncia;

public class MainDeleteDenuncia {
    public static void main(String[] args) throws Exception {
		DenunciaDAO dao = new DenunciaDAO();
		try {
			// Primeiro salvar
			Denuncia denuncia = new Denuncia();
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
