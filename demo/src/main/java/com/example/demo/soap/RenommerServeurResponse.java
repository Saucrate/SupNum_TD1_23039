package com.example.demo.soap;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "server"
})
@XmlRootElement(name = "renommerServeurResponse", namespace = "http://example.com/demo/soap/servers")
public class RenommerServeurResponse {

    @XmlElement(namespace = "http://example.com/demo/soap/servers", required = true)
    protected ServerSoap server;

    public ServerSoap getServer() {
        return server;
    }

    public void setServer(ServerSoap value) {
        this.server = value;
    }
}

