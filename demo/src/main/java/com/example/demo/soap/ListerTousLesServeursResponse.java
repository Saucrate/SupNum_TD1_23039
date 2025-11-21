package com.example.demo.soap;

import jakarta.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "servers"
})
@XmlRootElement(name = "listerTousLesServeursResponse", namespace = "http://example.com/demo/soap/servers")
public class ListerTousLesServeursResponse {

    @XmlElement(namespace = "http://example.com/demo/soap/servers", required = true)
    protected List<ServerSoap> servers;

    public List<ServerSoap> getServers() {
        if (servers == null) {
            servers = new ArrayList<>();
        }
        return this.servers;
    }
}

