package com.example.livraria.Repository;

import com.example.livraria.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryUsuario extends JpaRepository<Usuario, String> {
    Optional<Usuario> findUsuarioByLogin(String login);
}
