package com.test.developertest.models;


import javax.persistence.*;

@Entity
@Table(name = "items")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name")
    private String name;


    public Product() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Наименование продукта{" +
                "name='" + name + '\'' +
                '}';
    }
}
