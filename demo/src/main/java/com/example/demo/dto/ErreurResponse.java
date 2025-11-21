package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Réponse d'erreur")
public class ErreurResponse {

    @Schema(description = "Message d'erreur", example = "Serveur avec l'id 1 introuvable")
    private String erreur;

    public ErreurResponse() {
    }

    public ErreurResponse(String erreur) {
        this.erreur = erreur;
    }

    public String getErreur() {
        return erreur;
    }

    public void setErreur(String erreur) {
        this.erreur = erreur;
    }
}

