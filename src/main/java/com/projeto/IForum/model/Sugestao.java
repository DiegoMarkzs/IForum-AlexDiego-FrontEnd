package com.projeto.IForum.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_SUGESTAO")
@Data
@Getter
@Setter
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
    private String status; 

    @ManyToOne
    @JoinColumn(name = "email_usuario")
    private User usuario;

    public Sugestao() {
        this.dataCriacao = new Date();
        this.status = "PENDENTE";
    }
}

