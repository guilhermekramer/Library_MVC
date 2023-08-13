package com.example.livraria.Model;


import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


import java.util.Date;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "bigint")
    private Long id;
    private String nome;
    private String autor;
    private Integer ano;
    private Integer valor;
    private String imageuri;
    private Date deleted;
    private Boolean onCar;


}    