package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Requête pour renommer un serveur")
public class RenommerServeurRequest {

    @Schema(description = "Nouveau nom du serveur", example = "Serveur-Web-02", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nom;

    public RenommerServeurRequest() {
    }

    public RenommerServeurRequest(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}

