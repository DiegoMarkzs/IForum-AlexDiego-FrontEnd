package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "TB_SUGESTAO")
public class Sugestao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String texto;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @Column(length = 20)
    private Status status; 

    @ManyToOne
    @JoinColumn(name = "email_usuario")
    private User usuario;

    public Sugestao() {
        this.dataCriacao = new Date();
        this.status = "PENDENTE";
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }
}

