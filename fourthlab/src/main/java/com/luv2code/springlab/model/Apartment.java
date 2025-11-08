package com.luv2code.springlab.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Apartment entity with attributes and nested parameters")
public class Apartment {
    @Schema(description = "Identifier", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private int id;

    @Schema(description = "Street address", example = "Kyiv, Khreshchatyk 1")
    private String address;

    @Schema(description = "Monthly price", example = "950.0")
    private double price;

    @Schema(description = "Structured parameters of the apartment")
    private ApartmentParams params;

    @Schema(description = "Human-readable description", example = "Cozy, sunny flat")
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
