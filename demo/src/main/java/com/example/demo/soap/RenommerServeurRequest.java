package com.example.demo.soap;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "nouveauNom"
})
@XmlRootElement(name = "renommerServeurRequest", namespace = "http://example.com/demo/soap/servers")
public class RenommerServeurRequest {

    @XmlElement(namespace = "http://example.com/demo/soap/servers")
    protected Long id;

    @XmlElement(namespace = "http://example.com/demo/soap/servers", required = true)
    protected String nouveauNom;

    public Long getId() {
        return id;
    }

    public void setId(Long value) {
        this.id = value;
    }

    public String getNouveauNom() {
        return nouveauNom;
    }

    public void setNouveauNom(String value) {
        this.nouveauNom = value;
    }
}

