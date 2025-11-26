package com.luv2code.springlab.service;

import com.luv2code.springlab.model.Apartment;
import com.luv2code.springlab.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApartmentService {
    private final ApartmentRepository apartmentRepository;

    @Autowired
    public ApartmentService(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    @Transactional(readOnly = true)
    public List<Apartment> findAll() { return apartmentRepository.findAll(); }

    @Transactional(readOnly = true)
    public List<Apartment> searchByParams(int rooms, double area, boolean hasBalcony, boolean furnished) {
        return apartmentRepository.searchByParams(rooms, area, hasBalcony, furnished);
    }

    @Transactional(readOnly = true)
    public Apartment findById(int id) { return apartmentRepository.findById(id); }

    @Transactional
    public int save(Apartment apartment) {
        return apartmentRepository.save(apartment);
    }

    @Transactional
    public void update(Apartment apartment) { apartmentRepository.update(apartment); }

    @Transactional
    public void delete(int id) { apartmentRepository.delete(id); }

    @Transactional
    public void createAndFail(Apartment apartment) {
        apartmentRepository.save(apartment);
        if (true) {
            throw new RuntimeException("Simulated error for transaction rollback check");
        }
    }
}