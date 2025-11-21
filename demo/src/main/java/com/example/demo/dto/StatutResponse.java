package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Réponse contenant le statut d'un serveur")
public class StatutResponse {

    @Schema(description = "Identifiant du serveur", example = "1")
    private Long id;

    @Schema(description = "Statut du serveur (true = démarré, false = arrêté)", example = "true")
    private Boolean statut;

    @Schema(description = "Message descriptif du statut", example = "Le serveur est démarré")
    private String message;

    public StatutResponse() {
    }

    public StatutResponse(Long id, Boolean statut, String message) {
        this.id = id;
        this.statut = statut;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getStatut() {
        return statut;
    }

    public void setStatut(Boolean statut) {
        this.statut = statut;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

