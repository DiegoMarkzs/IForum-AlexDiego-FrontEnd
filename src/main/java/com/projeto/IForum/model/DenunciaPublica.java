package com.projeto.IForum.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_DENUNCIA_PUBLICA")
@Data
@Getter
@Setter
public class DenunciaPublica extends Denuncia{


    public DenunciaPublica(User usuario){
        this.setUsuario(usuario);
        this.setTipoDenuncia("publica");
    }
    public DenunciaPublica() {
        this.setTipoDenuncia("publica");
        
    }

     public void setUsuario(User usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo em DenunciaPublica");
        }
        super.setUsuario(usuario);
    }

}
