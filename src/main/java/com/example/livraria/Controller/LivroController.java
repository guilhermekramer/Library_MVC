package com.example.livraria.Controller;


import com.example.livraria.Model.Livro;
import com.example.livraria.Repository.RepositoryLivro;
import com.example.livraria.Service.FileStorageServer;
import com.example.livraria.Service.LivroService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.ModelAndView;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class LivroController {


    @Autowired
    LivroService service;

    @Autowired
    FileStorageServer fileStorageServer;


    public LivroController( LivroService service, FileStorageServer fileStorageServer) {
        this.service = service;
        this.fileStorageServer = fileStorageServer;
    }

    public LivroController() {
    }

    @GetMapping("/index")
    public String getIndex(Model model) {
        List<Livro> ListaLivros = new ArrayList<>();
        model.addAttribute("ListaDeLivros", ListaLivros);
        return "index.html";
    }

    @GetMapping("/cadastrarLivro")
    public String getCadastrarLivro(Model model) {
        Livro livro = new Livro();
        model.addAttribute("livro", livro);
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
    public String criarLivro(@ModelAttribute Livro livro, @RequestParam(name = "file") MultipartFile file) throws IOException {
        livro.setImageuri(new String(file.getBytes()));
        this.service.salvar(livro);
        this.fileStorageServer.salvar(file);
        return "redirect:/index";
    }

    @GetMapping("/listarLivro")
    public String listarLivro(Model model) {
        List<Livro> listaLivro = service.findAll();
        model.addAttribute("listaLivro", listaLivro);
        return "listarLivro";
    }

    @GetMapping("/deletarLivro/{id}")
    public String deletarLivro(@PathVariable("id") Long id) {
        if (service.findById(String.valueOf(id)) != null) {
            service.delete(id);
        }
        return "redirect:/listarLivro";
    }
}
