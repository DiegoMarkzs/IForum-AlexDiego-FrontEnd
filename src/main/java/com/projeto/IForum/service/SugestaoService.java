package com.projeto.IForum.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projeto.IForum.model.Sugestao;
import com.projeto.IForum.model.User;
import com.projeto.IForum.repositorios.SugestaoRepository;

@Service
public class SugestaoService {

    private final SugestaoRepository sugestaoRepository;

    public SugestaoService(SugestaoRepository sugestaoRepository) {
        this.sugestaoRepository = sugestaoRepository;
    }

    public Sugestao salvar(Sugestao sugestao) {
        sugestao.setDataCriacao(new Date());
        sugestao.setStatus("PENDENTE");
        return sugestaoRepository.save(sugestao);
    }

   
    public Optional<Sugestao> buscarPorId(Long id) {
        return sugestaoRepository.findById(id);
    }

    public List<Sugestao> listarTodas() {
        Iterable<Sugestao> iterable = sugestaoRepository.findAll();
        List<Sugestao> lista = new ArrayList<>();
        iterable.forEach(lista::add);
        return lista;
    }

  
    public void atualizarStatus(Long id, String novoStatus) {
        Optional<Sugestao> opt = sugestaoRepository.findById(id);
        if (opt.isPresent()) {
            Sugestao sugestao = opt.get();
            sugestao.setStatus(novoStatus);
            sugestaoRepository.save(sugestao);
        }
    }

    public void deletar(Long id) {
        sugestaoRepository.deleteById(id);
    }

    
    public List<Sugestao> buscarPorUsuario(User usuario) {
        return sugestaoRepository.findByUsuario(usuario);
    }
}
