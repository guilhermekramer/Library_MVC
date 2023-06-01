package com.example.livraria.Controller;


import com.example.livraria.Model.Livro;
import com.example.livraria.Service.FileStorageServer;
import com.example.livraria.Service.LivroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class LivroController {



    LivroService service;
    FileStorageServer fileStorageServer;

    public LivroController(LivroService service, FileStorageServer fileStorageServer) {
        this.service = service;
        this.fileStorageServer = fileStorageServer;
    }

    @GetMapping("/index")
    public String getIndex(Model model){
        List<Livro> ListaLivros = new ArrayList<>();
        model.addAttribute("livros", ListaLivros);
        return "index.html";
    }

    @GetMapping("/cadastrarLivro")
    public String getCadastrarLivro(Model model){
        Livro livro = new Livro();
        model.addAttribute("Livro", livro);
        return "cadastrarLivro";
    }


    @PostMapping("/criarLivro")
    public String criarLivro(@ModelAttribute Livro livro) {
        //livro.setImageuri(file.getOriginalFilename());
        this.service.salvar(livro);
        //this.fileStorageServer.salvar(file);
        return "redirect:/index";
    }


    @GetMapping("/verCarrinho")
    public String verCarrinho(Model model) {
        List<Livro> carrinho = service.findByOnCar(true); // Supondo que você tenha um serviço para lidar com os livros
        model.addAttribute("carrinho", carrinho);
        return "verCarrinho";
    }


    @GetMapping("/listarLivro/{id}")
    public String adicionarCarrinho(@PathVariable("id") Long id) {
        Optional<Livro> livroOptional = service.findById(String.valueOf(id));

        if (livroOptional.isPresent()) {
            Livro livro = livroOptional.get();
            livro.setOnCar(true);
            service.salvar(livro);
            System.out.println("Adicionou");
        }
        return "redirect:/listarLivro";
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




