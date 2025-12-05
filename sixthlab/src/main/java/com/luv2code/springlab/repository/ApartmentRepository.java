package com.luv2code.springlab.repository;

import com.luv2code.springlab.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {

    // --- Методы findAll, findById, save, delete уже есть внутри JpaRepository! ---

    // 1. Для NamedQuery (Пункт 5.1.2)
    // Имя метода должно совпадать с именем в аннотации @NamedQuery в Entity (после точки)
    List<Apartment> findByPriceRange(@Param("minPrice") Double minPrice,
                                     @Param("maxPrice") Double maxPrice);

    // 2. Автоматический метод по названию (Пункт 5.2)
    List<Apartment> findByRooms(Integer rooms);

    // 3. JPQL @Query (Пункт 5.1.1)
    @Query("SELECT a FROM Apartment a " +
            "WHERE (:rooms = 0 OR a.rooms = :rooms) " +
            "AND (:area = 0.0 OR a.area >= :area) " +
            "AND (:hasBalcony = false OR a.hasBalcony = :hasBalcony) " +
            "AND (:furnished = false OR a.furnished = :furnished)")
    List<Apartment> searchByParams(@Param("rooms") int rooms,
                                   @Param("area") double area,
                                   @Param("hasBalcony") boolean hasBalcony,
                                   @Param("furnished") boolean furnished);
}