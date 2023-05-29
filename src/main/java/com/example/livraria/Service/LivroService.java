package com.example.livraria.Service;

import com.example.livraria.Model.Livro;
import com.example.livraria.Repository.RepositoryLivro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;


@Service
public class LivroService {


    @Autowired
    private RepositoryLivro repository;

    public LivroService(RepositoryLivro repository) {
        this.repository = repository;
    }

    public void salvar(Livro livro){


        repository.save(livro);
    }

    public List<Livro> findAll(){
        return repository.findAll();
    }

    public void delete(String id){

        Optional<Livro> livroOptional = repository.findById(id);
        Livro livro = livroOptional.get();
        livro.setDeleted(Boolean.TRUE);
        repository.save(livro);
    }

    public Optional<Livro> findById(String id){
       return repository.findById(id);
    }
}
