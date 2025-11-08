package com.luv2code.springlab.model;


import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.stereotype.Component;

@Schema(description = "Client actor participating in apartment interactions")
@Component
public class Client {

    @Schema(description = "Identifier of the client", example = "101", accessMode = Schema.AccessMode.READ_ONLY)
    private int id;

    @Schema(description = "Display name of the client", example = "Alice Johnson")
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
