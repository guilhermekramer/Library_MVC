package com.example.livraria.Controller;


import com.example.livraria.Model.Livro;
import com.example.livraria.Repository.RepositoryLivro;
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


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class LivroController {


    private final HttpSession session;
    LivroService service;
    private HttpServletResponse response;
    private List<Livro> carrinho = new ArrayList<>();

    public LivroController(LivroService service, HttpSession session) {
        this.service = service;
        this.session = session;
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
       // livro.setImageuri(file.getOriginalFilename());
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
//
//    @GetMapping("/verCarrinho")
//    public String verCarrinho(Model model, @Autowired HttpServletResponse response, Long id) {
//        Optional<Livro> carrinho = service.findById(String.valueOf(id));
//        model.addAttribute("carrinho", carrinho);
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd--MM--yyyy_HH::mm:ss");
//        String visitaDate = dateFormat.format(new Date());
//
//        Cookie cookie = new Cookie("visita", visitaDate);
//        cookie.setMaxAge(24 * 60 * 60);
//        response.addCookie(cookie);
//
//        return "verCarrinho";
//    }
//
//
//
//
//
//    @GetMapping("/adicionarCarrinho/{id}")
//    public String adicionarCarrinho(@PathVariable("id") Long id) {
//        Optional<Livro> livroOptional = service.findById(String.valueOf(id));
//        if (livroOptional.isPresent()) {
//            Livro livro = livroOptional.get();
//
//            List<Livro> carrinho = (List<Livro>) session.getAttribute("carrinho");
//            if (carrinho == null) {
//                carrinho = new ArrayList<>();
//            }
//




//    @GetMapping("/adicionarCarrinho/{id}")
//    public String adicionarCarrinho(@PathVariable("id") Long id) {
//        Optional<Livro> livroOptional = service.findById(String.valueOf(id));
//        List<Livro> carrinho = new ArrayList<>();
//        if (livroOptional.isPresent()) {
//            Livro livro = livroOptional.get();
//            carrinho.add(livro);
//        }
//        return "verCarrinho";
//    }

//    @GetMapping("/adicionarCarrinho/{id}")
//    public String adicionarCarrinho(@PathVariable("id") Long id) {
//        Optional<Livro> livroOptional = service.findById(String.valueOf(id));
//        if (livroOptional.isPresent()) {
//            Livro livro = livroOptional.get();
//            carrinho.add(livro);
//        }
//        return "redirect:/verCarrinho";
//    }