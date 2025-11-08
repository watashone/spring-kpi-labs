package com.luv2code.springlab.controller;

import com.luv2code.springlab.model.Apartment;
import com.luv2code.springlab.model.ApartmentParams;
import com.luv2code.springlab.service.ApartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/apartments")
@Tag(name = "Apartments", description = "REST API for managing apartments")
public class ApartmentController {

    private final ApartmentService service;

    @Autowired
    public ApartmentController(ApartmentService service) {
        this.service = service;
    }

    // READ ALL (with filtering and pagination)
    @Operation(
            summary = "Get list of apartments",
            description = "Returns all apartments with optional filtering (rooms, area, balcony, furnished) and pagination."
    )
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of apartments")
    @GetMapping
    public ResponseEntity<List<Apartment>> getAll(
            @Parameter(description = "Number of rooms") @RequestParam(required = false) Integer rooms,
            @Parameter(description = "Minimum apartment area") @RequestParam(required = false) Double area,
            @Parameter(description = "Has balcony") @RequestParam(required = false) Boolean hasBalcony,
            @Parameter(description = "Is furnished") @RequestParam(required = false) Boolean furnished,
            @Parameter(description = "Page number (starting from 0)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page") @RequestParam(defaultValue = "5") int size
    ) {
        ApartmentParams params = new ApartmentParams(
                rooms != null ? rooms : 0,
                area != null ? area : 0,
                hasBalcony != null && hasBalcony,
                furnished != null && furnished
        );

        List<Apartment> filtered = service.searchByParams(params);
        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, filtered.size());
        if (fromIndex > filtered.size()) {
            return ResponseEntity.ok(List.of());
        }

        return ResponseEntity.ok(filtered.subList(fromIndex, toIndex));
    }

    // READ by ID
    @Operation(summary = "Get apartment by ID", description = "Returns an apartment by the specified ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Apartment found"),
            @ApiResponse(responseCode = "404", description = "Apartment not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Apartment> getById(@PathVariable int id) {
        Apartment apartment = service.findById(id);
        if (apartment == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(apartment);
    }

    // CREATE
    @Operation(summary = "Create a new apartment", description = "Adds a new Apartment object to the list.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Apartment successfully created",
                    content = @Content(schema = @Schema(implementation = Apartment.class)))
    })
    @PostMapping
    public ResponseEntity<Apartment> create(@RequestBody Apartment apartment) {
        service.save(apartment);
        return ResponseEntity.status(HttpStatus.CREATED).body(apartment);
    }

    // UPDATE (PUT)
    @Operation(summary = "Update apartment data", description = "Fully updates an existing apartment by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Apartment updated"),
            @ApiResponse(responseCode = "404", description = "Apartment not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Apartment> update(@PathVariable int id, @RequestBody Apartment updated) {
        Apartment existing = service.findById(id);
        if (existing == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        updated.setId(id);
        service.save(updated);
        return ResponseEntity.ok(updated);
    }

    // PARTIAL UPDATE (PATCH)
    @Operation(summary = "Partially update apartment", description = "Updates only selected fields of an apartment (RFC 7386).")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Partial update successful"),
            @ApiResponse(responseCode = "404", description = "Apartment not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Apartment> partialUpdate(@PathVariable int id, @RequestBody Map<String, Object> updates) {
        Apartment apartment = service.findById(id);
        if (apartment == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        updates.forEach((key, value) -> {
            switch (key) {
                case "address" -> apartment.setAddress((String) value);
                case "price" -> apartment.setPrice(Double.parseDouble(value.toString()));
                case "description" -> apartment.setDescription((String) value);
            }
        });

        service.save(apartment);
        return ResponseEntity.ok(apartment);
    }

    // DELETE
    @Operation(summary = "Delete apartment", description = "Deletes an apartment by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Apartment successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Apartment not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Apartment existing = service.findById(id);
        if (existing == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
