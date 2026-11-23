# TP 1 — Accès direct à l'API Gemini

Ismail LAHLOU — EMSI Rabat — RABAT-IA — numéro 16.

Ce projet reprend l'interface JSF du TP 0 et envoie les questions à Gemini avec un client REST Jakarta, sans SDK Gemini ni LangChain4j.

## Lancement

- Java 25, Maven et Payara 7 / Jakarta EE 11.
- Définir la variable d'environnement `GEMINI_KEY` avec une clé Gemini personnelle.
- Redémarrer IntelliJ et Payara si la variable vient d'être ajoutée.
- Construire le projet avec `mvnw.cmd package` sous Windows, ou `sh mvnw package` sous Linux/macOS.
- Déployer `target/tp1-lahlou-1.0-SNAPSHOT.war` dans Payara.
- Ouvrir `http://localhost:8080/tp1-lahlou-1.0-SNAPSHOT/`.

La clé est lue avec `System.getenv("GEMINI_KEY")` et transmise dans l'en-tête `x-goog-api-key`. Elle n'est pas dans le code ou dans les JSON du mode debug.
Le modèle utilisé est `gemini-3.5-flash-lite`, indiqué dans l'énoncé consulté.

## Fichiers principaux

- `index.xhtml` : formulaire, réponse, conversation et interrupteur du mode debug.
- `Bb.java` : état de la page, validation de la question et appel à l'adaptateur.
- `JsonAdapterPourGemini.java` : code fourni par le professeur pour construire le JSON, conserver l'historique et extraire la réponse.
- `LlmClientPourGemini.java` : squelette du professeur complété avec la clé et l'endpoint REST.
- `LlmInteraction.java` : record contenant la requête JSON, la réponse JSON et le texte extrait.
- `RequeteException.java` : exception du cours pour les réponses HTTP en erreur.

Dans le formulaire, choisir un rôle, saisir une question et cliquer sur « Envoyer la question ».
L'interrupteur « Mode debug » agit lors de la prochaine soumission du formulaire.
« Nouveau chat » réinitialise la vue et l'historique. Les boutons de copie et d'effacement proviennent du TP 0.

## Rôle ajouté pour le bonus

**Dépanneur Wi-Fi à la maison** : répondre en français simple, proposer une seule vérification concrète à la fois, puis demander le résultat. Le rôle tient compte des vérifications précédentes et ne demande pas de mot de passe.

Exemples de deux questions à tester dans une même conversation :

1. « Mon ordinateur capte mal le Wi-Fi dans ma chambre, mais il fonctionne près du routeur. Que dois-je vérifier ? »
2. « J'ai déplacé le routeur en hauteur et le signal s'est amélioré. Que puis-je vérifier ensuite ? »

Les réponses et le JSON à joindre au rendu doivent être ceux réellement reçus de Gemini.

## Contenu demandé pour le rendu

L'email doit contenir, dans son texte :

1. L'URL du dépôt GitHub, après publication.
2. L'explication du rôle ajouté et une conversation avec exactement deux questions utilisateur.
3. Le JSON de la deuxième requête et l'explication de `system_instruction`, `contents`, `role`, `parts` et `text`.
4. Les deux messages d'erreur réellement obtenus : clé incorrecte, puis rôle `system` à la place de `user` dans `creerRequeteJson`.
5. La commande utilisée pour restaurer le code fonctionnel après ces deux essais.

`JsonAdapterPourGemini` conserve l'historique envoyé à Gemini : à la deuxième requête, `contents` contient la première question, la première réponse du modèle et la nouvelle question. L'API ne conserve pas elle-même cette conversation pour les appels indépendants de ce TP.

Avant les essais d'erreur, conserver un commit fonctionnel. Tester chaque erreur séparément, puis restaurer le fichier modifié avec `git restore --source=HEAD -- chemin/du/fichier.java`, reconstruire et redéployer. Ne pas committer le code volontairement incorrect.

Le dépôt Git reste local à ce stade ; le push sera effectué plus tard.

## Sources

- [Énoncé du TP 1](http://richard.grin.free.fr/emsi/rabat-ia/tp/tp1-api-direct/index.html)
- [Adaptateur JSON fourni](http://richard.grin.free.fr/emsi/rabat-ia/tp/tp1-api-direct/jsonadapter.html)
- [Client REST à compléter](http://richard.grin.free.fr/emsi/rabat-ia/tp/tp1-api-direct/llmclient.html)
- [Panneau debug fourni](http://richard.grin.free.fr/emsi/rabat-ia/tp/tp1-api-direct/paneldebug.html)
- [Clé Gemini](https://aistudio.google.com/api-keys)
