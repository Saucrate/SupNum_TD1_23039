package com.example.demo.soap;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id"
})
@XmlRootElement(name = "recupererStatutRequest", namespace = "http://example.com/demo/soap/servers")
public class RecupererStatutRequest {

    @XmlElement(namespace = "http://example.com/demo/soap/servers")
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long value) {
        this.id = value;
    }
}

