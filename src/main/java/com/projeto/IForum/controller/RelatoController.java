package com.projeto.IForum.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.IForum.model.Relato;
import com.projeto.IForum.model.RelatoAnonimo;
import com.projeto.IForum.model.RelatoPublico;
import com.projeto.IForum.service.RelatoService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
@RequestMapping("/relatos")
public class RelatoController {

    @Autowired
    private RelatoService relatoService;

   @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Relato relato) {
        relatoService.salvar(relato);
         return ResponseEntity.status(HttpStatus.CREATED).body(relato);
}

 @GetMapping("/{id}")
    public ResponseEntity<Relato> getRelatoById(@PathVariable Long id) {
        Optional<Relato> relato = relatoService.buscarPorId(id);
        if (relato.isPresent()) {
            return ResponseEntity.ok(relato.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
public ResponseEntity<Relato> atualizarRelato(
        @PathVariable Long id,
        @RequestBody Relato relatoAtualizado) {

    if (relatoService.buscarPorId(id) == null) {
        return ResponseEntity.notFound().build();
    }

    relatoAtualizado.setId(id);
    Relato relatoSalvo = relatoService.salvar(relatoAtualizado);
    return ResponseEntity.ok(relatoSalvo);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deletarRelato(@PathVariable Long id) {
    if (relatoService.buscarPorId(id) == null) {
        return ResponseEntity.notFound().build();
    }
    relatoService.deletarPorId(id);
    return ResponseEntity.noContent().build();
}

@GetMapping
public ResponseEntity<List<Relato>> listarTodos() {
    List<Relato> relatos = relatoService.buscarTodos();
    return ResponseEntity.ok(relatos);
}




    
}