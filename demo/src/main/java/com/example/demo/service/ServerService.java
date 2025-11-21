package com.example.demo.service;

import com.example.demo.model.Server;
import com.example.demo.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServerService {

    private final ServerRepository serverRepository;

    @Autowired
    public ServerService(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    /**
     * Créer un nouveau serveur
     * @param nom Le nom du serveur
     * @return Le serveur créé
     * @throws IllegalArgumentException si le nom existe déjà
     */
    public Server creerServeur(String nom) {
        if (serverRepository.existsByNom(nom)) {
            throw new IllegalArgumentException("Un serveur avec le nom '" + nom + "' existe déjà");
        }
        Server serveur = new Server(nom);
        return serverRepository.save(serveur);
    }

    /**
     * Lister tous les serveurs
     * @return La liste de tous les serveurs
     */
    public List<Server> listerTousLesServeurs() {
        return serverRepository.findAll();
    }

    /**
     * Renommer un serveur
     * @param id L'identifiant du serveur
     * @param nouveauNom Le nouveau nom du serveur
     * @return Le serveur renommé
     * @throws IllegalArgumentException si le serveur n'existe pas ou si le nouveau nom existe déjà
     */
    public Server renommerServeur(Long id, String nouveauNom) {
        Server serveur = serverRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serveur avec l'id " + id + " introuvable"));
        
        if (serverRepository.existsByNom(nouveauNom) && !serveur.getNom().equals(nouveauNom)) {
            throw new IllegalArgumentException("Un serveur avec le nom '" + nouveauNom + "' existe déjà");
        }
        
        serveur.setNom(nouveauNom);
        return serverRepository.save(serveur);
    }

    /**
     * Récupérer le statut d'un serveur
     * @param id L'identifiant du serveur
     * @return Le statut du serveur (true = démarré, false = arrêté)
     * @throws IllegalArgumentException si le serveur n'existe pas
     */
    public Boolean recupererStatut(Long id) {
        Server serveur = serverRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serveur avec l'id " + id + " introuvable"));
        return serveur.getStatut();
    }

    /**
     * Démarrer un serveur (mettre son statut à true)
     * @param id L'identifiant du serveur
     * @return Le serveur démarré
     * @throws IllegalArgumentException si le serveur n'existe pas
     */
    public Server demarrerServeur(Long id) {
        Server serveur = serverRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serveur avec l'id " + id + " introuvable"));
        
        serveur.setStatut(true);
        return serverRepository.save(serveur);
    }

    /**
     * Arrêter un serveur (mettre son statut à false)
     * @param id L'identifiant du serveur
     * @return Le serveur arrêté
     * @throws IllegalArgumentException si le serveur n'existe pas
     */
    public Server arreterServeur(Long id) {
        Server serveur = serverRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serveur avec l'id " + id + " introuvable"));
        
        serveur.setStatut(false);
        return serverRepository.save(serveur);
    }

    /**
     * Supprimer un serveur
     * Un serveur en cours d'exécution ne peut pas être supprimé
     * @param id L'identifiant du serveur
     * @throws IllegalArgumentException si le serveur n'existe pas ou s'il est en cours d'exécution
     */
    public void supprimerServeur(Long id) {
        Server serveur = serverRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serveur avec l'id " + id + " introuvable"));
        
        if (serveur.getStatut()) {
            throw new IllegalStateException("Impossible de supprimer le serveur '" + serveur.getNom() + 
                    "' car il est en cours d'exécution. Veuillez l'arrêter d'abord.");
        }
        
        serverRepository.delete(serveur);
    }

    /**
     * Récupérer un serveur par son ID
     * @param id L'identifiant du serveur
     * @return Le serveur trouvé
     * @throws IllegalArgumentException si le serveur n'existe pas
     */
    public Server recupererServeur(Long id) {
        return serverRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serveur avec l'id " + id + " introuvable"));
    }
}

