package com.example.livraria.Repository;

import com.example.livraria.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryUsuario extends JpaRepository<Usuario, String> {

}
