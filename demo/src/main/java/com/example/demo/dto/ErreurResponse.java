package com.example.demo.dto;

public class ErreurResponse {

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

