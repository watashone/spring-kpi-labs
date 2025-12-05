package com.luv2code.springlab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Відповідає AUTO_INCREMENT
    private Integer id;

    @Column(nullable = false)
    private String name;

    // Один клієнт може орендувати багато квартир (теоретично)
    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Apartment> apartments;

    // Геттери та Сеттери
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Apartment> getApartments() { return apartments; }
    public void setApartments(List<Apartment> apartments) { this.apartments = apartments; }
}