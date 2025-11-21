package com.example.demo.soap;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "statut",
    "message"
})
@XmlRootElement(name = "recupererStatutResponse", namespace = "http://example.com/demo/soap/servers")
public class RecupererStatutResponse {

    @XmlElement(namespace = "http://example.com/demo/soap/servers")
    protected Long id;

    @XmlElement(namespace = "http://example.com/demo/soap/servers")
    protected Boolean statut;

    @XmlElement(namespace = "http://example.com/demo/soap/servers", required = true)
    protected String message;

    public Long getId() {
        return id;
    }

    public void setId(Long value) {
        this.id = value;
    }

    public Boolean getStatut() {
        return statut;
    }

    public void setStatut(Boolean value) {
        this.statut = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String value) {
        this.message = value;
    }
}

