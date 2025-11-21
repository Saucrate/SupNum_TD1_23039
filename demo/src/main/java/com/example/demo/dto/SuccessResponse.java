package com.example.demo.dto;

import com.example.demo.model.Server;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Réponse de succès pour les opérations")
public class SuccessResponse {

    @Schema(description = "Message de succès", example = "Serveur démarré avec succès")
    private String message;

    @Schema(description = "Serveur concerné par l'opération")
    private Server serveur;

    @Schema(description = "Identifiant du serveur (pour les opérations de suppression)")
    private Long id;

    public SuccessResponse() {
    }

    public SuccessResponse(String message, Server serveur) {
        this.message = message;
        this.serveur = serveur;
    }

    public SuccessResponse(String message, Long id) {
        this.message = message;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Server getServeur() {
        return serveur;
    }

    public void setServeur(Server serveur) {
        this.serveur = serveur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

