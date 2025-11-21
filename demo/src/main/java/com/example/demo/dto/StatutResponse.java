package com.example.demo.dto;

public class StatutResponse {

    private Long id;

    private Boolean statut;

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

