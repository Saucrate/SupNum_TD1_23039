package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Requête pour créer un nouveau serveur")
public class CreerServeurRequest {

    @Schema(description = "Nom du serveur", example = "Serveur-Web-01", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nom;

    public CreerServeurRequest() {
    }

    public CreerServeurRequest(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}

