package com.example.demo.dto;

import com.example.demo.model.Server;

public class SuccessResponse {

    private String message;

    private Server serveur;

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

