package com.luv2code.springlab.repository;

import com.luv2code.springlab.model.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ApartmentJdbcRepository implements ApartmentRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ApartmentJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Apartment> findAll() {
        String sql = "SELECT * FROM apartment";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Apartment.class));
    }

    @Override
    public Apartment findById(int id) {
        String sql = "SELECT * FROM apartment WHERE id = ?";
        List<Apartment> apartments = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Apartment.class), id);
        return apartments.isEmpty() ? null : apartments.get(0);
    }

    @Override
    public int save(Apartment apartment) {
        String sql = "INSERT INTO apartment (address, price, rooms, area, has_balcony, furnished, description, landlord_id, client_id) VALUES(?,?,?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, apartment.getAddress());
            ps.setDouble(2, apartment.getPrice());
            ps.setInt(3, apartment.getRooms());
            ps.setDouble(4, apartment.getArea());
            ps.setBoolean(5, apartment.isHasBalcony());
            ps.setBoolean(6, apartment.isFurnished());
            ps.setString(7, apartment.getDescription());
            ps.setInt(8, apartment.getLandlordId());
            ps.setInt(9, apartment.getClientId());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void update(Apartment apartment) {
        String sql = "UPDATE apartment SET address=?, price=?, rooms=?, area=?, has_balcony=?, furnished=?, description=?, landlord_id=?, client_id=? WHERE id=?";
        jdbcTemplate.update(sql, apartment.getAddress(), apartment.getPrice(), apartment.getRooms(), apartment.getArea(),
                apartment.isHasBalcony(), apartment.isFurnished(), apartment.getDescription(),
                apartment.getLandlordId(), apartment.getClientId(), apartment.getId());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM apartment WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Apartment> searchByParams(int rooms, double area, boolean hasBalcony, boolean furnished) {
        String sql = "SELECT * FROM apartment WHERE (rooms=? OR ?=0) AND (area>=? OR ?=0) AND (has_balcony=? OR ?=false) AND (furnished=? OR ?=false)";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Apartment.class),
                rooms, rooms, area, area, hasBalcony, hasBalcony, furnished, furnished);
    }
}
