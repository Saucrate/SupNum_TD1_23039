package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "servers")
public class Server {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom du serveur est obligatoire")
    @Column(nullable = false, unique = true)
    private String nom;

    @NotNull
    @Column(nullable = false)
    private Boolean statut = false; // false = arrêté, true = démarré

    // Constructeurs
    public Server() {
    }

    public Server(String nom) {
        this.nom = nom;
        this.statut = false;
    }

    public Server(String nom, Boolean statut) {
        this.nom = nom;
        this.statut = statut;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Boolean getStatut() {
        return statut;
    }

    public void setStatut(Boolean statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Server{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", statut=" + statut +
                '}';
    }
}

