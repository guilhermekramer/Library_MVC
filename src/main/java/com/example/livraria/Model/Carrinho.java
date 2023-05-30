package com.example.livraria.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carrinho {
    @Id
    private Long id;
    private double preçoUnitario;
    private int Quantidade;
}
