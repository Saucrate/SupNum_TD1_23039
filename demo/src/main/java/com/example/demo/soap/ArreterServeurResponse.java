package com.example.demo.soap;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "message",
    "server"
})
@XmlRootElement(name = "arreterServeurResponse", namespace = "http://example.com/demo/soap/servers")
public class ArreterServeurResponse {

    @XmlElement(namespace = "http://example.com/demo/soap/servers", required = true)
    protected String message;

    @XmlElement(namespace = "http://example.com/demo/soap/servers", required = true)
    protected ServerSoap server;

    public String getMessage() {
        return message;
    }

    public void setMessage(String value) {
        this.message = value;
    }

    public ServerSoap getServer() {
        return server;
    }

    public void setServer(ServerSoap value) {
        this.server = value;
    }
}

