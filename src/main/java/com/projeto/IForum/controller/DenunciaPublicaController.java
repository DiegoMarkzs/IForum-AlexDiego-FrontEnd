package com.projeto.IForum.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.IForum.model.Denuncia;
import com.projeto.IForum.model.DenunciaAnonima;
import com.projeto.IForum.model.DenunciaPublica;
import com.projeto.IForum.service.DenunciaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/denuncias-publicas")
public class DenunciaPublicaController {

    @Autowired
    private DenunciaService denunciaService;

   @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody DenunciaPublica denuncia) {
        denunciaService.salvar(denuncia);
         return ResponseEntity.status(HttpStatus.CREATED).body(denuncia);
}

 @GetMapping("/{id}")
    public ResponseEntity<Denuncia> getDenunciaPublicaById(@PathVariable Long id) {
        Optional<Denuncia> denuncia = denunciaService.buscarPorId(id);
        if (denuncia.isPresent()) {
            return ResponseEntity.ok(denuncia.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
public ResponseEntity<Denuncia> atualizarDenunciaPublica(
        @PathVariable Long id,
        @RequestBody DenunciaPublica denunciaAtualizada) {

    if (denunciaService.buscarPorId(id) == null) {
        return ResponseEntity.notFound().build();
    }

    denunciaAtualizada.setId(id);
    Denuncia denunciaSalva = denunciaService.salvar(denunciaAtualizada);
    return ResponseEntity.ok(denunciaSalva);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deletarDenunciaPublica(@PathVariable Long id) {
    if (denunciaService.buscarPorId(id) == null) {
        return ResponseEntity.notFound().build();
    }
    denunciaService.deletarPorId(id);
    return ResponseEntity.noContent().build();
}




    
}