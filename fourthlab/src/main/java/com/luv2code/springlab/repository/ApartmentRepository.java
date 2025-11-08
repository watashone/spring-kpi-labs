package com.luv2code.springlab.repository;


import com.luv2code.springlab.model.Apartment;
import com.luv2code.springlab.model.ApartmentParams;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ApartmentRepository {
    private final List<Apartment> apartments = new ArrayList<>();
    private int nextId = 1;

    public List<Apartment> findAll() {
        return apartments;
    }

    public Apartment findById(int id) {
        for (Apartment apartment : apartments) {
            if (apartment.getId() == id) {
                return apartment;
            }
        }
        return null;
    }

    public void save(Apartment apartment) {
        if (apartment.getId() == 0) {
            apartment.setId(nextId++);
            apartments.add(apartment);
        } else {
            for (int i = 0; i < apartments.size(); i++) {
                if (apartments.get(i).getId() == apartment.getId()) {
                    apartments.set(i, apartment);
                    return;
                }
            }
            apartments.add(apartment);
        }
    }


    public void delete(int id) {
        apartments.removeIf(apartment -> apartment.getId() == id);
    }

    public List<Apartment> searchByParams(ApartmentParams params) {
        return apartments.stream()
                .filter(a -> params.getRooms() == 0 || a.getParams().getRooms() == params.getRooms())
                .filter(a -> params.getArea() == 0 || a.getParams().getArea() >= params.getArea())
                .filter(a -> !params.isHasBalcony() || a.getParams().isHasBalcony())
                .filter(a -> !params.isFurnished() || a.getParams().isFurnished())
                .toList();
    }


}
