package com.example.demo.soap;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "message",
    "id"
})
@XmlRootElement(name = "supprimerServeurResponse", namespace = "http://example.com/demo/soap/servers")
public class SupprimerServeurResponse {

    @XmlElement(namespace = "http://example.com/demo/soap/servers", required = true)
    protected String message;

    @XmlElement(namespace = "http://example.com/demo/soap/servers")
    protected Long id;

    public String getMessage() {
        return message;
    }

    public void setMessage(String value) {
        this.message = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long value) {
        this.id = value;
    }
}

