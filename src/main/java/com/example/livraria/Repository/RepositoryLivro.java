package com.example.livraria.Repository;

import com.example.livraria.Model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryLivro extends JpaRepository<Livro, String> {


    List<Livro> findByDeletedIsNull();
}
