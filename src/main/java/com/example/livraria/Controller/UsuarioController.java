package com.example.livraria.Controller;

import com.example.livraria.Model.Livro;
import com.example.livraria.Model.Usuario;
import com.example.livraria.Service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class UsuarioController {

    UsuarioService service;

    public UsuarioController(UsuarioService service){
        this.service = service;
    }



    @GetMapping("/cadastrarUsuario")
    public String doCadastrarUsuario(Model model){
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        return "cadastrarUsuario";
    }

    @PostMapping("/criarUsuario")
    public String criarUsuario(@ModelAttribute Usuario usuario){
        service.Salvar(usuario);
        return "redirect:/";
    }

}
