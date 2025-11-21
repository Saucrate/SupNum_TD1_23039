package com.example.demo.soap;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "nom"
})
@XmlRootElement(name = "creerServeurRequest", namespace = "http://example.com/demo/soap/servers")
public class CreerServeurRequest {

    @XmlElement(namespace = "http://example.com/demo/soap/servers", required = true)
    protected String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String value) {
        this.nom = value;
    }
}

