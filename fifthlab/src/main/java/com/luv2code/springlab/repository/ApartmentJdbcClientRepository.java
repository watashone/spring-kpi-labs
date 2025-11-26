package com.luv2code.springlab.repository;

import com.luv2code.springlab.model.Apartment;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class ApartmentJdbcClientRepository implements ApartmentRepository {

    private final JdbcClient jdbcClient;

    public ApartmentJdbcClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Apartment> findAll() {
        return jdbcClient.sql("SELECT * FROM apartment")
                .query(Apartment.class)
                .list();
    }

    @Override
    public Apartment findById(int id) {
        Optional<Apartment> apartment = jdbcClient.sql("SELECT * FROM apartment WHERE id = :id")
                .param("id", id)
                .query(Apartment.class)
                .optional();
        return apartment.orElse(null);
    }

    @Override
    public int save(Apartment apartment) {
        String sql = "INSERT INTO apartment (address, price, rooms, area, has_balcony, furnished, description, landlord_id, client_id) " +
                "VALUES(:address, :price, :rooms, :area, :hasBalcony, :furnished, :description, :landlordId, :clientId)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcClient.sql(sql)
                .param("address", apartment.getAddress())
                .param("price", apartment.getPrice())
                .param("rooms", apartment.getRooms())
                .param("area", apartment.getArea())
                .param("hasBalcony", apartment.isHasBalcony())
                .param("furnished", apartment.isFurnished())
                .param("description", apartment.getDescription())
                .param("landlordId", apartment.getLandlordId())
                .param("clientId", apartment.getClientId())
                .update(keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public void update(Apartment apartment) {
        String sql = "UPDATE apartment SET address=:address, price=:price, rooms=:rooms, area=:area, " +
                "has_balcony=:hasBalcony, furnished=:furnished, description=:description, " +
                "landlord_id=:landlordId, client_id=:clientId WHERE id=:id";

        jdbcClient.sql(sql)
                .paramSource(apartment)
                .update();
    }

    @Override
    public void delete(int id) {
        jdbcClient.sql("DELETE FROM apartment WHERE id=:id")
                .param("id", id)
                .update();
    }

    @Override
    public List<Apartment> searchByParams(int rooms, double area, boolean hasBalcony, boolean furnished) {
        String sql = "SELECT * FROM apartment WHERE (rooms=:rooms OR :rooms=0) " +
                "AND (area>=:area OR :area=0) " +
                "AND (has_balcony=:hasBalcony OR :hasBalcony=false) " +
                "AND (furnished=:furnished OR :furnished=false)";

        return jdbcClient.sql(sql)
                .param("rooms", rooms)
                .param("area", area)
                .param("hasBalcony", hasBalcony)
                .param("furnished", furnished)
                .query(Apartment.class)
                .list();
    }
}