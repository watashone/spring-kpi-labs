package com.luv2code.springlab.service;

import com.luv2code.springlab.model.Apartment;
import com.luv2code.springlab.model.ApartmentParams;
import com.luv2code.springlab.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentService {
    private final ApartmentRepository apartmentRepository;

    @Autowired
    public ApartmentService(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    public List<Apartment> findAll() {
        return apartmentRepository.findAll();
    }

    // setter injection
//    @Autowired
//    public void setRepository(ApartmentRepository repository) {
//        this.apartmentRepository = repository;
//    }

    public List<Apartment> searchByParams(ApartmentParams apartmentParams) {
        return apartmentRepository.searchByParams(apartmentParams);
    }

    public Apartment findById(int id) {
        return apartmentRepository.findById(id);
    }

    public void save(Apartment apartment) {
        apartmentRepository.save(apartment);
    }

    public void delete(int id) {
        apartmentRepository.delete(id);
    }


}
