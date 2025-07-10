package com.projeto.IForum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.projeto.IForum.model.Funcionario;
import com.projeto.IForum.model.User;
import com.projeto.IForum.service.UserService;


@SpringBootApplication
public class ProjetoDacApplication implements CommandLineRunner{

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoDacApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User funcionario = new Funcionario();
		funcionario.setNome("Teste");
		funcionario.setEmail("diego@gmail.com");

		userService.salvar(funcionario);
		Iterable<User> lista = userService.listarTodos();
		System.out.println(lista);
		
	}

	

}



