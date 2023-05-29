package com.example.livraria.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE table_product SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Livro {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private String id;
    private String nome;
    private String autor;
    private Integer ano;
    private float valor;
  //  private String Imageuri;
    private Boolean deleted = Boolean.FALSE;

    public void paraMaiusculo(){
        this.nome.toUpperCase();
    }

}