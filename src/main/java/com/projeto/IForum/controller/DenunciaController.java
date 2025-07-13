package com.projeto.IForum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.projeto.IForum.repositorios.DenunciaRepository;

@Controller
public class DenunciaController {

    @Autowired
    private DenunciaRepository denunciaRepository;

    @GetMapping("/denuncias")
    public String listarDenuncias(Model model) {
        model.addAttribute("denuncias", denunciaRepository.findAll());
        return "index";
    }
}