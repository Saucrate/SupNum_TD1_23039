package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.Server;
import com.example.demo.service.ServerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/serveurs")
@Tag(name = "Gestion des Serveurs", description = "API pour la gestion et le monitoring des serveurs dans un data center")
public class ServerController {

    private final ServerService serverService;

    @Autowired
    public ServerController(ServerService serverService) {
        this.serverService = serverService;
    }

    @Operation(
            summary = "Créer un nouveau serveur",
            description = "Crée un nouveau serveur avec le nom spécifié. Le serveur est créé avec le statut 'arrêté' par défaut."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Serveur créé avec succès",
                    content = @Content(schema = @Schema(implementation = Server.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requête invalide (nom manquant ou déjà existant)",
                    content = @Content(schema = @Schema(implementation = ErreurResponse.class))
            )
    })
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

    @Operation(
            summary = "Lister tous les serveurs",
            description = "Récupère la liste de tous les serveurs enregistrés dans le système."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Liste des serveurs récupérée avec succès",
            content = @Content(schema = @Schema(implementation = Server.class))
    )
    @GetMapping
    public ResponseEntity<List<Server>> listerTousLesServeurs() {
        List<Server> serveurs = serverService.listerTousLesServeurs();
        return ResponseEntity.ok(serveurs);
    }

    @Operation(
            summary = "Renommer un serveur",
            description = "Modifie le nom d'un serveur existant. Le nouveau nom doit être unique."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Serveur renommé avec succès",
                    content = @Content(schema = @Schema(implementation = Server.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requête invalide (serveur introuvable ou nom déjà existant)",
                    content = @Content(schema = @Schema(implementation = ErreurResponse.class))
            )
    })
    @PutMapping("/{id}/nom")
    public ResponseEntity<?> renommerServeur(
            @Parameter(description = "Identifiant du serveur", required = true, example = "1")
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

    @Operation(
            summary = "Récupérer le statut d'un serveur",
            description = "Récupère le statut actuel d'un serveur (démarré ou arrêté)."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Statut récupéré avec succès",
                    content = @Content(schema = @Schema(implementation = StatutResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Serveur introuvable",
                    content = @Content(schema = @Schema(implementation = ErreurResponse.class))
            )
    })
    @GetMapping("/{id}/statut")
    public ResponseEntity<?> recupererStatut(
            @Parameter(description = "Identifiant du serveur", required = true, example = "1")
            @PathVariable Long id) {
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

    @Operation(
            summary = "Démarrer un serveur",
            description = "Démarre un serveur en mettant son statut à 'true' (démarré)."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Serveur démarré avec succès",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Serveur introuvable",
                    content = @Content(schema = @Schema(implementation = ErreurResponse.class))
            )
    })
    @PutMapping("/{id}/demarrer")
    public ResponseEntity<?> demarrerServeur(
            @Parameter(description = "Identifiant du serveur", required = true, example = "1")
            @PathVariable Long id) {
        try {
            Server serveur = serverService.demarrerServeur(id);
            SuccessResponse response = new SuccessResponse("Serveur démarré avec succès", serveur);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new ErreurResponse(e.getMessage()));
        }
    }

    @Operation(
            summary = "Arrêter un serveur",
            description = "Arrête un serveur en mettant son statut à 'false' (arrêté)."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Serveur arrêté avec succès",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Serveur introuvable",
                    content = @Content(schema = @Schema(implementation = ErreurResponse.class))
            )
    })
    @PutMapping("/{id}/arreter")
    public ResponseEntity<?> arreterServeur(
            @Parameter(description = "Identifiant du serveur", required = true, example = "1")
            @PathVariable Long id) {
        try {
            Server serveur = serverService.arreterServeur(id);
            SuccessResponse response = new SuccessResponse("Serveur arrêté avec succès", serveur);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new ErreurResponse(e.getMessage()));
        }
    }

    @Operation(
            summary = "Supprimer un serveur",
            description = "Supprime un serveur du système. Un serveur en cours d'exécution ne peut pas être supprimé."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Serveur supprimé avec succès",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Serveur introuvable",
                    content = @Content(schema = @Schema(implementation = ErreurResponse.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Le serveur est en cours d'exécution et ne peut pas être supprimé",
                    content = @Content(schema = @Schema(implementation = ErreurResponse.class))
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimerServeur(
            @Parameter(description = "Identifiant du serveur", required = true, example = "1")
            @PathVariable Long id) {
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

    @Operation(
            summary = "Récupérer un serveur par son ID",
            description = "Récupère les détails complets d'un serveur à partir de son identifiant."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Serveur récupéré avec succès",
                    content = @Content(schema = @Schema(implementation = Server.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Serveur introuvable",
                    content = @Content(schema = @Schema(implementation = ErreurResponse.class))
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> recupererServeur(
            @Parameter(description = "Identifiant du serveur", required = true, example = "1")
            @PathVariable Long id) {
        try {
            Server serveur = serverService.recupererServeur(id);
            return ResponseEntity.ok(serveur);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new ErreurResponse(e.getMessage()));
        }
    }
}

