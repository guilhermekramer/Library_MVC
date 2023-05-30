package com.example.livraria.Service;

import com.example.livraria.Model.Usuario;
import com.example.livraria.Repository.RepositoryUsuario;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    RepositoryUsuario repository;


    public List<Usuario> listAll() {
        return repository.findAll();
    }
}