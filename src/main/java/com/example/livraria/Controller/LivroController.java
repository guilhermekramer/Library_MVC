package com.example.livraria.Controller;


import com.example.livraria.Model.Livro;
import com.example.livraria.Service.FileStorageServer;
import com.example.livraria.Service.LivroService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class LivroController {



    private final LivroService service;
    private final FileStorageServer fileStorageServer;

    @GetMapping(value ={ "/index", "/"})
    public String getIndex(Model model, HttpServletResponse response, HttpServletRequest request){
        HttpSession sessao = request.getSession();

        ArrayList<Livro> arraylivro = (ArrayList<Livro>) service.findByOnCar(Boolean.parseBoolean("TRUE"));
        List<Livro> listaLivro = service.findAll();
        int qntlivros = 0;
        if(arraylivro!=null){
            qntlivros = arraylivro.size();
        }
        model.addAttribute("listaLivro", listaLivro);
        model.addAttribute("qntlivros", qntlivros);
        return "index";
    }

    @GetMapping("/cadastrarLivro")
    public String getCadastrarLivro(Model model){
        Livro livro = new Livro();
        model.addAttribute("Livro", livro);
        return "cadastrarLivro";
    }


    @PostMapping("/criarLivro")
    public String criarLivro(@ModelAttribute Livro livro, @RequestParam("file") MultipartFile file) throws IOException {
        String caminho = this.fileStorageServer.salvar(file);
        livro.setImageuri("/images/".concat(file.getOriginalFilename()));
        this.service.salvar(livro);

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




