package com.example.livraria.Controller;

import com.example.livraria.Model.Usuario;
import com.example.livraria.Service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class UsuarioController {

    UsuarioService service;

    public UsuarioController(UsuarioService service){
        this.service = service;
    }

    @GetMapping("/cadastrarUsuario")
    public String doCadastrarUsuario(Model model){

        Usuario u = new Usuario();
        model.addAttribute("usuario", u);

        return "cadastrarUsuario";
    }

}
