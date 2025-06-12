package model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TB_DENUNCIA_PUBLICA")
public class DenunciaPublica extends Denuncia{


    public DenunciaPublica(User usuario){
        this.setUsuario(usuario);
    }
    public DenunciaPublica() {
        
    }

     public void setUsuario(User usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo em DenunciaPublica");
        }
        super.setUsuario(usuario);
    }

}
