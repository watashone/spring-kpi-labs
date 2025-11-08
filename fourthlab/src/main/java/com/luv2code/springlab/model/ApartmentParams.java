package com.luv2code.springlab.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Filterable and descriptive parameters of the apartment")
public class ApartmentParams {
    @Schema(description = "Number of rooms", example = "2")
    private int rooms;

    @Schema(description = "Area in square meters", example = "55.0")
    private double area;

    @Schema(description = "Presence of a balcony", example = "true")
    private boolean hasBalcony;

    @Schema(description = "Whether the apartment is furnished", example = "false")
    private boolean furnished;

    public ApartmentParams() {
    }

    public ApartmentParams(int rooms, double area, boolean hasBalcony, boolean furnished) {
        this.rooms = rooms;
        this.area = area;
        this.hasBalcony = hasBalcony;
        this.furnished = furnished;
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
}
