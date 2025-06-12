package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_COORDENADOR")
public class Coordenador{

    @Id
	@Column(name = "ID_Email")
	private String email;
    
}
