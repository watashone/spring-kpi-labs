package com.luv2code.springlab.repository;

import com.luv2code.springlab.model.Apartment;

import java.util.List;

public interface ApartmentRepository {
    List<Apartment> findAll();
    Apartment findById(int id);
    int save(Apartment apartment);
    void update(Apartment apartment);
    void delete(int id);
    List<Apartment> searchByParams(int rooms, double area, boolean hasBalcony, boolean furnished);
}

