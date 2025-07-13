package com.projeto.IForum.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "TB_DENUNCIA_ANONIMA")
@Data
public class DenunciaAnonima extends Denuncia{

    public DenunciaAnonima(){
        this.setTipoDenuncia("anonima");

    }

}
