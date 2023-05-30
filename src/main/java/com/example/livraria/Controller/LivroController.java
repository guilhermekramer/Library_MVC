package com.example.livraria.Controller;


import com.example.livraria.Model.Livro;
import com.example.livraria.Repository.RepositoryLivro;
import com.example.livraria.Service.LivroService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.livraria.Service.LivroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class LivroController {


    LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @GetMapping("/index")
    public String getIndex(Model model){
        List<Livro> ListaLivros = new ArrayList<>();
        model.addAttribute("ListaDeLivros", ListaLivros);
        return "index.html";
    }

    @GetMapping("/cadastrarLivro")
    public String getCadastrarLivro(Model model){
        Livro livro = new Livro();
        model.addAttribute("Livro", livro);
        return "cadastrarLivro";
    }

//    @PostMapping("/livros/{id}/imageuri")
//    public void salvarImagem(@ModelAttribute Livro livro, @RequestParam("imagem") MultipartFile imageuri)  {
//        livro = service.findById(String.valueOf(id)).orElseThrow(() -> new RuntimeException("Livro não encontrado"));
//
//        livro.setImageuri(imageuri.getOriginalFilename());
//
//        service.salvar(livro);
//    }

    @PostMapping("/criarLivro")
    public String criarLivro(@ModelAttribute Livro livro){
        service.salvar(livro);
        return "redirect:/index";
    }

    @GetMapping("/listarLivro")
    public String listarLivro(Model model){
        List<Livro> listaLivro = service.findAll();

        model.addAttribute("listaLivro", listaLivro);
        return "listarLivro";
    }

    @GetMapping("/deletarLivro/{id}")
    public String deletarLivro(@PathVariable("id")  Long id){
        if(service.findById(String.valueOf(id)) != null){
            service.delete(id);
        }
        return "redirect:/listarLivro";
    }






}
