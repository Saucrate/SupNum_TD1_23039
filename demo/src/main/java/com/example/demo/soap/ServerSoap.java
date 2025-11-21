package com.example.demo.soap;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "server", propOrder = {
    "id",
    "nom",
    "statut"
})
@XmlRootElement(name = "server", namespace = "http://example.com/demo/soap/servers")
public class ServerSoap {

    @XmlElement(namespace = "http://example.com/demo/soap/servers")
    protected Long id;

    @XmlElement(namespace = "http://example.com/demo/soap/servers", required = true)
    protected String nom;

    @XmlElement(namespace = "http://example.com/demo/soap/servers")
    protected Boolean statut;

    public Long getId() {
        return id;
    }

    public void setId(Long value) {
        this.id = value;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String value) {
        this.nom = value;
    }

    public Boolean getStatut() {
        return statut;
    }

    public void setStatut(Boolean value) {
        this.statut = value;
    }
}

