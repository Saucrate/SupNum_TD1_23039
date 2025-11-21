package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.Server;
import com.example.demo.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/serveurs")
public class ServerController {

    private final ServerService serverService;

    @Autowired
    public ServerController(ServerService serverService) {
        this.serverService = serverService;
    }

    @PostMapping
    public ResponseEntity<?> creerServeur(@RequestBody CreerServeurRequest request) {
        try {
            if (request.getNom() == null || request.getNom().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(new ErreurResponse("Le nom du serveur est obligatoire"));
            }
            Server serveur = serverService.creerServeur(request.getNom());
            return ResponseEntity.status(HttpStatus.CREATED).body(serveur);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new ErreurResponse(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<Server>> listerTousLesServeurs() {
        List<Server> serveurs = serverService.listerTousLesServeurs();
        return ResponseEntity.ok(serveurs);
    }

    @PutMapping("/{id}/nom")
    public ResponseEntity<?> renommerServeur(
            @PathVariable Long id,
            @RequestBody RenommerServeurRequest request) {
        try {
            if (request.getNom() == null || request.getNom().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(new ErreurResponse("Le nouveau nom du serveur est obligatoire"));
            }
            Server serveur = serverService.renommerServeur(id, request.getNom());
            return ResponseEntity.ok(serveur);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new ErreurResponse(e.getMessage()));
        }
    }

    @GetMapping("/{id}/statut")
    public ResponseEntity<?> recupererStatut(@PathVariable Long id) {
        try {
            Boolean statut = serverService.recupererStatut(id);
            StatutResponse response = new StatutResponse(
                    id,
                    statut,
                    statut ? "Le serveur est démarré" : "Le serveur est arrêté"
            );
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new ErreurResponse(e.getMessage()));
        }
    }

    @PutMapping("/{id}/demarrer")
    public ResponseEntity<?> demarrerServeur(@PathVariable Long id) {
        try {
            Server serveur = serverService.demarrerServeur(id);
            SuccessResponse response = new SuccessResponse("Serveur démarré avec succès", serveur);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new ErreurResponse(e.getMessage()));
        }
    }

    @PutMapping("/{id}/arreter")
    public ResponseEntity<?> arreterServeur(@PathVariable Long id) {
        try {
            Server serveur = serverService.arreterServeur(id);
            SuccessResponse response = new SuccessResponse("Serveur arrêté avec succès", serveur);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new ErreurResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimerServeur(@PathVariable Long id) {
        try {
            serverService.supprimerServeur(id);
            SuccessResponse response = new SuccessResponse("Serveur supprimé avec succès", id);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new ErreurResponse(e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErreurResponse(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> recupererServeur(@PathVariable Long id) {
        try {
            Server serveur = serverService.recupererServeur(id);
            return ResponseEntity.ok(serveur);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new ErreurResponse(e.getMessage()));
        }
    }
}
