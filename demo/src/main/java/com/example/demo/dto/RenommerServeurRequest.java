package com.example.demo.dto;

public class RenommerServeurRequest {

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

