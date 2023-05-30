package com.example.livraria.Service;

import com.example.livraria.Model.Usuario;
import com.example.livraria.Repository.RepositoryUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    RepositoryUsuario repository;




    public Usuario Salvar(Usuario usuario){

        return repository.save(usuario);

    }
    public List<Usuario> listAll() {
        return repository.findAll();
    }
}