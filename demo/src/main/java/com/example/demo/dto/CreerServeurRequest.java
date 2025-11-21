package com.example.demo.dto;

public class CreerServeurRequest {

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

