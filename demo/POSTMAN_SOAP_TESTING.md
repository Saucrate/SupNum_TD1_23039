# Guide de Test SOAP avec Postman

## Prérequis
1. L'application Spring Boot doit être démarrée
2. Le service SOAP doit être accessible sur `http://localhost:8080/ws`

## Accès au WSDL
Avant de tester, récupérez le WSDL pour comprendre la structure :
- **URL WSDL** : `http://localhost:8080/ws/servers.wsdl`
- Ouvrez cette URL dans votre navigateur pour voir la définition du service

## Configuration Postman pour SOAP

### ⚠️ Configuration CRITIQUE dans Postman

**Le problème le plus courant est le mauvais Content-Type !**

1. **Méthode** : `POST`
2. **URL** : `http://localhost:8080/ws`
3. **Headers** (à ajouter manuellement dans l'onglet "Headers") :
   - **Clé** : `Content-Type`
   - **Valeur** : `text/xml; charset=utf-8` 
   - ⚠️ **CRITIQUE** : DOIT être `text/xml` et **PAS** `application/xml`
   - ⚠️ Si vous voyez l'erreur "Invalid Content-Type:application/xml", c'est que Postman a automatiquement changé votre header !
   - **Clé** : `SOAPAction` (optionnel)
   - **Valeur** : `""` (vide ou laissez vide)
4. **Body** :
   - Sélectionnez l'onglet "Body"
   - Cochez "raw"
   - Dans le menu déroulant à droite (qui dit "Text" par défaut), choisissez **"XML"**
   - ⚠️ **Ne choisissez PAS "Text"** - cela enverra le mauvais Content-Type

### 1. Créer un serveur (creerServeur)

**Méthode** : `POST`  
**URL** : `http://localhost:8080/ws`  
**Headers** :
```
Content-Type: text/xml; charset=utf-8
SOAPAction: ""
```

**⚠️ IMPORTANT** : Le Content-Type doit être exactement `text/xml` (pas `application/xml`). SOAP rejette `application/xml`.

**Body** (raw XML) :
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
                  xmlns:ser="http://example.com/demo/soap/servers">
   <soapenv:Header/>
   <soapenv:Body>
      <ser:creerServeurRequest>
         <ser:nom>Serveur-Web-01</ser:nom>
      </ser:creerServeurRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

### 2. Lister tous les serveurs (listerTousLesServeurs)

**Méthode** : `POST`  
**URL** : `http://localhost:8080/ws`  
**Headers** :
```
Content-Type: text/xml; charset=utf-8
SOAPAction: ""
```

**⚠️ IMPORTANT** : Le Content-Type doit être exactement `text/xml` (pas `application/xml`). SOAP rejette `application/xml`.

**Body** (raw XML) :
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
                  xmlns:ser="http://example.com/demo/soap/servers">
   <soapenv:Header/>
   <soapenv:Body>
      <ser:listerTousLesServeursRequest/>
   </soapenv:Body>
</soapenv:Envelope>
```

### 3. Récupérer un serveur par ID (recupererServeur)

**Méthode** : `POST`  
**URL** : `http://localhost:8080/ws`  
**Headers** :
```
Content-Type: text/xml; charset=utf-8
SOAPAction: ""
```

**⚠️ IMPORTANT** : Le Content-Type doit être exactement `text/xml` (pas `application/xml`). SOAP rejette `application/xml`.

**Body** (raw XML) :
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
                  xmlns:ser="http://example.com/demo/soap/servers">
   <soapenv:Header/>
   <soapenv:Body>
      <ser:recupererServeurRequest>
         <ser:id>1</ser:id>
      </ser:recupererServeurRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

### 4. Récupérer le statut d'un serveur (recupererStatut)

**Méthode** : `POST`  
**URL** : `http://localhost:8080/ws`  
**Headers** :
```
Content-Type: text/xml; charset=utf-8
SOAPAction: ""
```

**⚠️ IMPORTANT** : Le Content-Type doit être exactement `text/xml` (pas `application/xml`). SOAP rejette `application/xml`.

**Body** (raw XML) :
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
                  xmlns:ser="http://example.com/demo/soap/servers">
   <soapenv:Header/>
   <soapenv:Body>
      <ser:recupererStatutRequest>
         <ser:id>1</ser:id>
      </ser:recupererStatutRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

### 5. Renommer un serveur (renommerServeur)

**Méthode** : `POST`  
**URL** : `http://localhost:8080/ws`  
**Headers** :
```
Content-Type: text/xml; charset=utf-8
SOAPAction: ""
```

**⚠️ IMPORTANT** : Le Content-Type doit être exactement `text/xml` (pas `application/xml`). SOAP rejette `application/xml`.

**Body** (raw XML) :
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
                  xmlns:ser="http://example.com/demo/soap/servers">
   <soapenv:Header/>
   <soapenv:Body>
      <ser:renommerServeurRequest>
         <ser:id>1</ser:id>
         <ser:nouveauNom>Serveur-Web-02</ser:nouveauNom>
      </ser:renommerServeurRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

### 6. Démarrer un serveur (demarrerServeur)

**Méthode** : `POST`  
**URL** : `http://localhost:8080/ws`  
**Headers** :
```
Content-Type: text/xml; charset=utf-8
SOAPAction: ""
```

**⚠️ IMPORTANT** : Le Content-Type doit être exactement `text/xml` (pas `application/xml`). SOAP rejette `application/xml`.

**Body** (raw XML) :
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
                  xmlns:ser="http://example.com/demo/soap/servers">
   <soapenv:Header/>
   <soapenv:Body>
      <ser:demarrerServeurRequest>
         <ser:id>1</ser:id>
      </ser:demarrerServeurRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

### 7. Arrêter un serveur (arreterServeur)

**Méthode** : `POST`  
**URL** : `http://localhost:8080/ws`  
**Headers** :
```
Content-Type: text/xml; charset=utf-8
SOAPAction: ""
```

**⚠️ IMPORTANT** : Le Content-Type doit être exactement `text/xml` (pas `application/xml`). SOAP rejette `application/xml`.

**Body** (raw XML) :
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
                  xmlns:ser="http://example.com/demo/soap/servers">
   <soapenv:Header/>
   <soapenv:Body>
      <ser:arreterServeurRequest>
         <ser:id>1</ser:id>
      </ser:arreterServeurRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

### 8. Supprimer un serveur (supprimerServeur)

**Méthode** : `POST`  
**URL** : `http://localhost:8080/ws`  
**Headers** :
```
Content-Type: text/xml; charset=utf-8
SOAPAction: ""
```

**⚠️ IMPORTANT** : Le Content-Type doit être exactement `text/xml` (pas `application/xml`). SOAP rejette `application/xml`.

**Body** (raw XML) :
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
                  xmlns:ser="http://example.com/demo/soap/servers">
   <soapenv:Header/>
   <soapenv:Body>
      <ser:supprimerServeurRequest>
         <ser:id>1</ser:id>
      </ser:supprimerServeurRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

## Réponses attendues

### Réponse de succès (exemple : creerServeur)
```xml
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
   <SOAP-ENV:Header/>
   <SOAP-ENV:Body>
      <ns2:creerServeurResponse xmlns:ns2="http://example.com/demo/soap/servers">
         <ns2:server>
            <ns2:id>1</ns2:id>
            <ns2:nom>Serveur-Web-01</ns2:nom>
            <ns2:statut>false</ns2:statut>
         </ns2:server>
      </ns2:creerServeurResponse>
   </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```

## Ordre de test recommandé

1. **Créer un serveur** (creerServeur) - Notez l'ID retourné
2. **Lister tous les serveurs** (listerTousLesServeurs)
3. **Récupérer un serveur** (recupererServeur) - Utilisez l'ID du serveur créé
4. **Récupérer le statut** (recupererStatut)
5. **Démarrer le serveur** (demarrerServeur)
6. **Vérifier le statut** (recupererStatut) - Devrait retourner `true`
7. **Arrêter le serveur** (arreterServeur)
8. **Vérifier le statut** (recupererStatut) - Devrait retourner `false`
9. **Supprimer le serveur** (supprimerServeur) - Note : ne fonctionne que si le serveur est arrêté

## Notes importantes

- Toutes les requêtes SOAP utilisent la méthode **POST**
- L'URL est toujours `http://localhost:8080/ws`
- ⚠️ **CRITIQUE** : Le header `Content-Type` doit être **exactement** `text/xml; charset=utf-8` (pas `application/xml`)
- Le namespace `http://example.com/demo/soap/servers` doit être utilisé pour tous les éléments
- Un serveur en cours d'exécution (statut = true) ne peut pas être supprimé

## 🔧 Résolution de l'erreur "Invalid Content-Type:application/xml"

Si vous voyez cette erreur dans les logs :
```
Invalid Content-Type:application/xml. Is this an error message instead of a SOAP response?
```

**Solution** :
1. Dans Postman, allez dans l'onglet "Headers"
2. Vérifiez que vous avez bien un header `Content-Type` avec la valeur `text/xml; charset=utf-8`
3. ⚠️ **Si Postman a automatiquement changé votre header en `application/xml`**, supprimez-le et recréez-le manuellement
4. Assurez-vous que dans l'onglet "Body", vous avez sélectionné "raw" et **"XML"** (pas "Text")
5. Si le problème persiste, désactivez l'auto-détection de Content-Type dans les paramètres de Postman (Settings → General → Automatically follow redirects)

