package com.example.livraria.Service;

import com.example.livraria.Model.Livro;
import com.example.livraria.Repository.RepositoryLivro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import java.util.Optional;


@Service
public class LivroService {


    @Autowired
    private RepositoryLivro repositoryLivro;

    public LivroService(RepositoryLivro repositoryLivro) {
        this.repositoryLivro = repositoryLivro;
    }

    public void salvar(Livro livro){

        repositoryLivro.save(livro);
    }

    public List<Livro> findAll() {
        return repositoryLivro.findByDeletedIsNull();
    }


    public void delete(Long id){

        Optional<Livro> livroOptional = repositoryLivro.findById(String.valueOf(id));
        Livro livro = livroOptional.get();
        Date dataDeletado = new Date();
        livro.setDeleted(dataDeletado);
        repositoryLivro.save(livro);
    }


    public Optional<Livro> findById(String id){
       return repositoryLivro.findById(id);
    }


    public List<Livro> findByOnCar(boolean b) {
        return repositoryLivro.findByOnCar(b);
    }
}
