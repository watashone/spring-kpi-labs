package com.luv2code.springlab.controller;

import com.luv2code.springlab.model.Apartment;
import com.luv2code.springlab.service.ApartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apartments")
@Tag(name = "Apartment Management", description = "Operations for managing apartments")
public class ApartmentController {

    private final ApartmentService service;

    public ApartmentController(ApartmentService service) {
        this.service = service;
    }

    @Operation(summary = "Search apartments", description = "Get all apartments or filter by parameters (rooms, area, etc.)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<Apartment> getAll(@RequestParam(required = false) Integer rooms,
                                  @RequestParam(required = false) Double area,
                                  @RequestParam(required = false) Boolean hasBalcony,
                                  @RequestParam(required = false) Boolean furnished) {
        return service.searchByParams(
                rooms != null ? rooms : 0,
                area != null ? area : 0,
                hasBalcony != null && hasBalcony,
                furnished != null && furnished);
    }

    @Operation(summary = "Get apartment by ID", description = "Returns a single apartment by its identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the apartment"),
            @ApiResponse(responseCode = "404", description = "Apartment not found")
    })
    @GetMapping("/{id}")
    public Apartment getById(@Parameter(description = "ID of the apartment to be obtained") @PathVariable int id) {
        return service.findById(id);
    }

    @Operation(summary = "Create apartment", description = "Add a new apartment to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Apartment created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int create(@RequestBody Apartment apartment) {
        return service.save(apartment);
    }

    @Operation(summary = "Update apartment", description = "Update an existing apartment by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Apartment updated successfully"),
            @ApiResponse(responseCode = "404", description = "Apartment not found")
    })
    @PutMapping("/{id}")
    public void update(@Parameter(description = "ID of the apartment to update") @PathVariable int id,
                       @RequestBody Apartment updatedApartment) {
        updatedApartment.setId(id);
        service.update(updatedApartment);
    }

    @Operation(summary = "Delete apartment", description = "Deletes an apartment by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Apartment deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Apartment not found")
    })
    @DeleteMapping("/{id}")
    public void delete(@Parameter(description = "ID of the apartment to delete") @PathVariable int id) {
        service.delete(id);
    }

    @Operation(summary = "Test Transaction Rollback", description = "Tries to create an apartment but throws an exception to test rollback")
    @PostMapping("/test-transaction")
    public void testTransaction(@RequestBody Apartment apartment) {
        service.createAndFail(apartment);
    }
}