package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import service.CriptografiaAES;

@Entity
@Table(name = "TB_USER")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User implements Serializable {
	private static final long serialVersionUID = -6518853480190451215L;
	

	

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Denuncia> denuncias = new ArrayList<>();

public List<Denuncia> getDenuncias() {
    return denuncias;
}

public void setDenuncias(List<Denuncia> denuncias) {
    this.denuncias = denuncias;
}
	
	

	@Id
	@Column(name = "ID_Email")
	private String email;
	
	@Column(name = "Nome")
	private String nome;
	
	@Column(name = "Sobrenome")
	private String sobrenome;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "Nascimento")
	private Date nascimento;
	
	@Column(name = "Senha")
	private String senha;
	
	public User() {
		
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
		
	}
	

	

}
