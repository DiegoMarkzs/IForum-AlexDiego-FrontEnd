package dac;

import dao.DacException;
import dao.UserDAO;
import model.Aluno;
import model.User;

public class MainDeleteUser {
    public static void main(String[] args) throws DacException {
		UserDAO dao = new UserDAO();
		try {
			// Primeiro salvar
			User user = new Aluno();

            user.setEmail("testeDelete");
			user.setSenha("123");
			
			user.setNome("teste");
			user.setSobrenome("Delete");

			dao.save(user);

			System.out.println(dao.getAll().size());

			// Depois apagar

			dao.delete(user);

			System.out.println(dao.getAll().size());
		} finally {
			dao.close();
		}
	}


}
