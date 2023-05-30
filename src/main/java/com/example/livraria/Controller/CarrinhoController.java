package com.example.livraria.Controller;

import com.example.livraria.Model.Carrinho;
import com.example.livraria.Model.Livro;
import com.example.livraria.Service.CarrinhoService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class CarrinhoController {

    private CarrinhoService service;
    private final HttpSession session;
    private List<Livro> ListaCompra = new ArrayList<>();

    public CarrinhoController(CarrinhoService service, HttpSession session, List<Livro> listaCompra) {
        this.service = service;
        this.session = session;
        ListaCompra = listaCompra;
    }

    @GetMapping("/verCarrinho")
    public String verCarrinho(Model model, @Autowired HttpServletResponse response, Long id) {
        Optional<Livro> carrinho = service.findById(String.valueOf(id));
        model.addAttribute("carrinho", carrinho);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd--MM--yyyy_HH::mm:ss");
        String visitaDate = dateFormat.format(new Date());

        Cookie cookie = new Cookie("visita", visitaDate);
        cookie.setMaxAge(24 * 60 * 60);
        response.addCookie(cookie);

        return "verCarrinho";
    }





    @GetMapping("/adicionarCarrinho/{id}")
    public String adicionarCarrinho(@PathVariable("id") Long id, HttpSession session) {
        Optional<Carrinho> livroOptional = service.findById(String.valueOf(id));
        if (livroOptional.isPresent()) {
            Carrinho livro = livroOptional.get();

            List<Livro> carrinho = (List<Livro>) session.getAttribute("carrinho");
            if (carrinho == null) {
                carrinho = new ArrayList<>();
            }

            carrinho.add(livro);

            session.setAttribute("carrinho", carrinho);
        }
        return "redirect:/index";
    }


}
