package com.luv2code.springlab.model;

public class Apartment {
    private int id;
    private String address;
    private double price;
    private ApartmentParams params;
    private String description;

    public Apartment() {
    }

    public Apartment(int id, String address, double price, ApartmentParams params, String description) {
        this.id = id;
        this.address = address;
        this.price = price;
        this.params = params;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ApartmentParams getParams() {
        return params;
    }

    public void setParams(ApartmentParams params) {
        this.params = params;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
