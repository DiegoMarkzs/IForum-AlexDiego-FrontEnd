package com.projeto.IForum.repositorios;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projeto.IForum.model.Sugestao;
import com.projeto.IForum.model.User;

@Repository
public interface SugestaoRepository extends CrudRepository<Sugestao, Long>{
    List<Sugestao> findByUsuario(User usuario);
}
