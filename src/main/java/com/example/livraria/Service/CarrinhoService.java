package com.example.livraria.Service;

import com.example.livraria.Model.Carrinho;
import com.example.livraria.Repository.RepositoryCarrinho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarrinhoService {
    @Autowired
    RepositoryCarrinho repositoryCarrinho;

    public CarrinhoService(RepositoryCarrinho repositoryCarrinho){
        this.repositoryCarrinho = repositoryCarrinho;
    }

    public Optional<Carrinho> findById(String id) {
        return repositoryCarrinho.findById(id);

    }
}
