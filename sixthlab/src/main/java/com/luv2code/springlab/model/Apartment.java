package com.luv2code.springlab.model;

import jakarta.persistence.*;

@Entity
@Table(name = "apartment")
@NamedQuery(
        name = "Apartment.findByPriceRange",
        query = "SELECT a FROM Apartment a WHERE a.price BETWEEN :minPrice AND :maxPrice"
)
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private Integer id;

    private String address;
    private Double price;
    private Integer rooms;
    private Double area;

    @Column(name = "has_balcony") // Вказуємо точну назву колонки в БД
    private Boolean hasBalcony;

    private Boolean furnished;
    private String description;

    // Зв'язок з Landlord (Many-to-One)
    @ManyToOne
    @JoinColumn(name = "landlord_id") // Це назва колонки зовнішнього ключа в таблиці apartment
    private Landlord landlord;

    // Зв'язок з Client (Many-to-One)
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    // Геттери та Сеттери
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public Integer getRooms() { return rooms; }
    public void setRooms(Integer rooms) { this.rooms = rooms; }
    public Double getArea() { return area; }
    public void setArea(Double area) { this.area = area; }
    public Boolean getHasBalcony() { return hasBalcony; }
    public void setHasBalcony(Boolean hasBalcony) { this.hasBalcony = hasBalcony; }
    public Boolean getFurnished() { return furnished; }
    public void setFurnished(Boolean furnished) { this.furnished = furnished; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Landlord getLandlord() { return landlord; }
    public void setLandlord(Landlord landlord) { this.landlord = landlord; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
}