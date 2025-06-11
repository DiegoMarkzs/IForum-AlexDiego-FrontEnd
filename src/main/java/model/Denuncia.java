package model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "TB_DENUNCIA")
public class Denuncia {


    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    
    
	@Column(name = "data")
    private Date data;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "assunto")
    private String assunto;

    @Column(name = "deferido")
    private String deferido = "Pendente";

    public void setDeferido(boolean condicao){
        if(condicao == true){
        deferido = "Aceita";
        }
        else{
            deferido = "Negada";
        }

    }

    public String getDeferido(){

        return deferido;
    }


    public int GetID(){
        return id;
        
    }

    public Date getData(){
        return data;
    }

    public String getAssunto(){
        return assunto;
    }

    public String getTitulo(){
        return titulo;
    }

    public void setData(Date data){
        this.data = data;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    } 

    public void setAssunto(String assunto){
        this.assunto = assunto;
    }

    public String toString(){
        return ("\n ID: " +String.valueOf(id)+ 
                "\n Titulo:"+titulo+
                "\n Assunto:"+assunto
                ) ;
    }

}


