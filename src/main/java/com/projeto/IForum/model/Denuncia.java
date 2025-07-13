package com.projeto.IForum.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.InheritanceType;


@Entity
@Table(name = "TB_DENUNCIA")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@Getter
@Setter
public abstract class Denuncia {

    @ManyToOne(optional = true)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private User usuario;

    public void setUsuario(User usuario){
        this.usuario = usuario;
    }


    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Enumerated(EnumType.STRING)
    private CategoriaDenuncia categoria;    
    
	@Column(name = "data")
    private Date data;

    private String tipoDenuncia;

    @Column(name = "assunto")
    private String assunto;

    @Column(name = "status")
    private String status = "Pendente";

    public void setStatus(boolean condicao){
        if(condicao == true){
        status = "Aceita";
        }
        else{
            status = "Negada";
        }

    }

}


