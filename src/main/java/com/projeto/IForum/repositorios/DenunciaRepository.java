package com.projeto.IForum.repositorios;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projeto.IForum.model.Denuncia;

@Repository
public interface DenunciaRepository extends CrudRepository<Denuncia, Long>{

    List<Denuncia> findByCategoria(String categoria);

}
