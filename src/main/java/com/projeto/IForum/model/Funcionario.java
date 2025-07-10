package com.projeto.IForum.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_FUNCIONARIO")
@Data
@Getter
@Setter
public class Funcionario extends User {

    @Column(name = "setor")
    private String setor;

    public Funcionario() {
        super();
    }

}

