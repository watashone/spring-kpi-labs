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

    @Schema(description = "Human-readable description", example = "Cozy, sunny flat")
    private String description;

    @Schema(description = "Number of rooms", example = "2")
    private int rooms;

    @Schema(description = "Area in square meters", example = "55.0")
    private double area;

    @Schema(description = "Presence of a balcony", example = "true")
    private boolean hasBalcony;

    @Schema(description = "Whether the apartment is furnished", example = "false")
    private boolean furnished;

    @Schema(description = "Landlord ID", example = "1")
    private int landlordId;

    @Schema(description = "Client ID", example = "1")
    private int clientId;

    public Apartment() {
    }

    public Apartment(int id, String address, double price, int rooms, double area, boolean hasBalcony, boolean furnished, String description, int landlordId, int clientId) {
        this.id = id;
        this.address = address;
        this.price = price;
        this.rooms = rooms;
        this.area = area;
        this.hasBalcony = hasBalcony;
        this.furnished = furnished;
        this.description = description;
        this.landlordId = landlordId;
        this.clientId = clientId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public boolean isHasBalcony() {
        return hasBalcony;
    }

    public void setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }

    public boolean isFurnished() {
        return furnished;
    }

    public void setFurnished(boolean furnished) {
        this.furnished = furnished;
    }

    public int getLandlordId() {
        return landlordId;
    }

    public void setLandlordId(int landlordId) {
        this.landlordId = landlordId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
