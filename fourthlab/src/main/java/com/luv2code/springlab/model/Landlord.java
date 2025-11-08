package com.luv2code.springlab.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.stereotype.Component;

@Schema(description = "Landlord actor who owns or manages apartments")
@Component
public class Landlord {

    @Schema(description = "Identifier of the landlord", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private int id;

    @Schema(description = "Display name of the landlord", example = "Main Landlord")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
