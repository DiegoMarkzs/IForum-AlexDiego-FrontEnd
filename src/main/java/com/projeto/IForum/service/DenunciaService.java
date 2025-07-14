package com.projeto.IForum.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.IForum.model.Denuncia;
import com.projeto.IForum.model.DenunciaAnonima;
import com.projeto.IForum.model.DenunciaPublica;
import com.projeto.IForum.model.User;
import com.projeto.IForum.repositorios.DenunciaRepository;

@Service
@Transactional
public class DenunciaService {

    @Autowired
    private DenunciaRepository denunciaRepository;

    public Denuncia salvar(Denuncia denuncia) {
        return denunciaRepository.save(denuncia);
    }

   
    public Optional<Denuncia> buscarPorId(Long id) {
        return denunciaRepository.findById(id);
    }

    
    public List<Denuncia> listarTodas() {
        Iterable<Denuncia> iterable = denunciaRepository.findAll();
        List<Denuncia> lista = new ArrayList<>();
        iterable.forEach(lista::add);
        return lista;
    }

   
    public void deletarPorId(Long id) {
        denunciaRepository.deleteById(id);
    }

    
    public List<Denuncia> buscarPorCategoria(String categoria) {
        return denunciaRepository.findByCategoria(categoria);
    }

     public void fazerDenunciaAnonima(DenunciaAnonima denuncia) {
        denunciaRepository.save(denuncia);
    }

    public void fazerDenunciaPublica(DenunciaPublica denuncia, User usuario) {
        denuncia.setUsuario(usuario);
        denunciaRepository.save(denuncia);
    }

}
