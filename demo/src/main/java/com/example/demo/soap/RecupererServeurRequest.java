package com.example.demo.soap;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id"
})
@XmlRootElement(name = "recupererServeurRequest", namespace = "http://example.com/demo/soap/servers")
public class RecupererServeurRequest {

    @XmlElement(namespace = "http://example.com/demo/soap/servers")
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long value) {
        this.id = value;
    }
}

