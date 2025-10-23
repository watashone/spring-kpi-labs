package com.luv2code.springlab.model;

public class ApartmentParams {
    private int rooms;
    private double area;
    private boolean hasBalcony;
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
