package com.example.demo.soap;

import com.example.demo.model.Server;
import com.example.demo.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class ServerEndpoint {

    private static final String NAMESPACE_URI = "http://example.com/demo/soap/servers";

    private final ServerService serverService;

    @Autowired
    public ServerEndpoint(ServerService serverService) {
        this.serverService = serverService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "creerServeurRequest")
    @ResponsePayload
    public CreerServeurResponse creerServeur(@RequestPayload CreerServeurRequest request) {
        CreerServeurResponse response = new CreerServeurResponse();
        try {
            Server server = serverService.creerServeur(request.getNom());
            response.setServer(convertToServerSoap(server));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Erreur lors de la création du serveur: " + e.getMessage());
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "listerTousLesServeursRequest")
    @ResponsePayload
    public ListerTousLesServeursResponse listerTousLesServeurs(@RequestPayload ListerTousLesServeursRequest request) {
        ListerTousLesServeursResponse response = new ListerTousLesServeursResponse();
        List<Server> servers = serverService.listerTousLesServeurs();
        for (Server server : servers) {
            response.getServers().add(convertToServerSoap(server));
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "renommerServeurRequest")
    @ResponsePayload
    public RenommerServeurResponse renommerServeur(@RequestPayload RenommerServeurRequest request) {
        RenommerServeurResponse response = new RenommerServeurResponse();
        try {
            Server server = serverService.renommerServeur(request.getId(), request.getNouveauNom());
            response.setServer(convertToServerSoap(server));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Erreur lors du renommage du serveur: " + e.getMessage());
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "recupererStatutRequest")
    @ResponsePayload
    public RecupererStatutResponse recupererStatut(@RequestPayload RecupererStatutRequest request) {
        RecupererStatutResponse response = new RecupererStatutResponse();
        try {
            Boolean statut = serverService.recupererStatut(request.getId());
            response.setId(request.getId());
            response.setStatut(statut);
            response.setMessage(statut ? "Le serveur est démarré" : "Le serveur est arrêté");
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Erreur lors de la récupération du statut: " + e.getMessage());
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "demarrerServeurRequest")
    @ResponsePayload
    public DemarrerServeurResponse demarrerServeur(@RequestPayload DemarrerServeurRequest request) {
        DemarrerServeurResponse response = new DemarrerServeurResponse();
        try {
            Server server = serverService.demarrerServeur(request.getId());
            response.setMessage("Serveur démarré avec succès");
            response.setServer(convertToServerSoap(server));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Erreur lors du démarrage du serveur: " + e.getMessage());
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "arreterServeurRequest")
    @ResponsePayload
    public ArreterServeurResponse arreterServeur(@RequestPayload ArreterServeurRequest request) {
        ArreterServeurResponse response = new ArreterServeurResponse();
        try {
            Server server = serverService.arreterServeur(request.getId());
            response.setMessage("Serveur arrêté avec succès");
            response.setServer(convertToServerSoap(server));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Erreur lors de l'arrêt du serveur: " + e.getMessage());
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "supprimerServeurRequest")
    @ResponsePayload
    public SupprimerServeurResponse supprimerServeur(@RequestPayload SupprimerServeurRequest request) {
        SupprimerServeurResponse response = new SupprimerServeurResponse();
        try {
            serverService.supprimerServeur(request.getId());
            response.setMessage("Serveur supprimé avec succès");
            response.setId(request.getId());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Erreur lors de la suppression du serveur: " + e.getMessage());
        } catch (IllegalStateException e) {
            throw new RuntimeException("Erreur lors de la suppression du serveur: " + e.getMessage());
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "recupererServeurRequest")
    @ResponsePayload
    public RecupererServeurResponse recupererServeur(@RequestPayload RecupererServeurRequest request) {
        RecupererServeurResponse response = new RecupererServeurResponse();
        try {
            Server server = serverService.recupererServeur(request.getId());
            response.setServer(convertToServerSoap(server));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Erreur lors de la récupération du serveur: " + e.getMessage());
        }
        return response;
    }

    private ServerSoap convertToServerSoap(Server server) {
        ServerSoap serverSoap = new ServerSoap();
        serverSoap.setId(server.getId());
        serverSoap.setNom(server.getNom());
        serverSoap.setStatut(server.getStatut());
        return serverSoap;
    }
}

