package com.example.test.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Float priceInCents;
    private String description;

    public Product(String name, Float priceInCents, String description){
        this.name = name;
        this.priceInCents = priceInCents;
        this.description = description;
    }
}
