package com.luv2code.springlab.service;

import com.luv2code.springlab.model.Apartment;
import com.luv2code.springlab.model.Client;
import com.luv2code.springlab.model.Landlord;
import com.luv2code.springlab.repository.ApartmentRepository;
import com.luv2code.springlab.repository.ClientRepository;
import com.luv2code.springlab.repository.LandlordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final LandlordRepository landlordRepository;
    private final ClientRepository clientRepository;

    public ApartmentService(ApartmentRepository apartmentRepository,
                            LandlordRepository landlordRepository,
                            ClientRepository clientRepository) {
        this.apartmentRepository = apartmentRepository;
        this.landlordRepository = landlordRepository;
        this.clientRepository = clientRepository;
    }


    @Transactional(readOnly = true)
    public List<Apartment> findAll() {
        return apartmentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Apartment findById(int id) {
        return apartmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apartment not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Apartment> searchByParams(int rooms, double area, boolean hasBalcony, boolean furnished) {
        return apartmentRepository.searchByParams(rooms, area, hasBalcony, furnished);
    }

    @Transactional(readOnly = true)
    public List<Apartment> findByPriceRange(double min, double max) {
        return apartmentRepository.findByPriceRange(min, max);
    }

    @Transactional(readOnly = true)
    public List<Apartment> findByRooms(int rooms) {
        return apartmentRepository.findByRooms(rooms);
    }


    @Transactional
    public int save(Apartment apartment) {
        if (apartment.getLandlord() != null && apartment.getLandlord().getId() != null) {
            Landlord l = landlordRepository.findById(apartment.getLandlord().getId())
                    .orElseThrow(() -> new RuntimeException("Landlord not found"));
            apartment.setLandlord(l);
        }

        if (apartment.getClient() != null && apartment.getClient().getId() != null) {
            Client c = clientRepository.findById(apartment.getClient().getId())
                    .orElseThrow(() -> new RuntimeException("Client not found"));
            apartment.setClient(c);
        }

        return apartmentRepository.save(apartment).getId();
    }

    @Transactional
    public void update(Apartment updatedApartment) {
        Apartment existing = apartmentRepository.findById(updatedApartment.getId())
                .orElseThrow(() -> new RuntimeException("Not found"));

        existing.setAddress(updatedApartment.getAddress());
        existing.setPrice(updatedApartment.getPrice());
        existing.setRooms(updatedApartment.getRooms());
        existing.setArea(updatedApartment.getArea());
        existing.setHasBalcony(updatedApartment.getHasBalcony());
        existing.setFurnished(updatedApartment.getFurnished());
        existing.setDescription(updatedApartment.getDescription());

        apartmentRepository.save(existing);
    }

    @Transactional
    public void delete(int id) {
        if (!apartmentRepository.existsById(id)) {
            throw new RuntimeException("Apartment not found");
        }
        apartmentRepository.deleteById(id);
    }

    @Transactional
    public void createAndFail(Apartment apartment) {
        save(apartment);
        if (true) {
            throw new RuntimeException("Simulated error for transaction rollback check");
        }
    }
}