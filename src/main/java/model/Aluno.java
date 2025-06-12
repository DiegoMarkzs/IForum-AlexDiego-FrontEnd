package model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ALUNO")
public class Aluno extends User{

    private String curso;

    public Aluno() {
        super();
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
