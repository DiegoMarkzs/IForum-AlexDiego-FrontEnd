package model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TB_FUNCIONARIO")
public class Funcionario extends User {

    private String setor;

    public Funcionario() {
        super();
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }
}

