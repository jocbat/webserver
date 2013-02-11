***** Notes sur la conception : *****

- Je me suis servi des sockets afin de réaliser ce mini web server

- J'ai tenté de trouver les meilleures abstractions (i.e modélisation) afin 
	o d'affranchir le plus possible les fonctionnalités du système des contraintes physiques des données manipulées par celui-ci 
	(ici typiquement les InputStream et OutputStream)
	
	o d'obtenir un ensemble cohérent dans lequel la "place" des nouvelles fonctionnalités des évolutions sera assez simple à trouver
		-> Corollaire : cela rend plus simple l'approche multithreading
		
***** Que fait ce programme ? *****

Il permet d'afficher au travers d'un navigateur (ou tout autre client utilisant un flux de sortie) un fichier enregistré sur le disque (paramétrable 
lors de l'instanciation du serveur)
Seulement deux cas sont gérés :

	- Le fichier est à l'url correspondante => on affiche les lignes de ce fichier
	- L'url n'est pas valide ou bien elle est valide mais le fichier n'existe pas => on obtient une réponse indiquant que le fichier n'a pu être trouvé
	- Dans le cas où la lecture se passerait mal (IOException) celle-ci est catchée et renvoit un message "Internal Server Error"
	
	
***** Description de la modélisation *****

Avant de penser à gérer le multithreading, j'ai cherché à dégager les types (= classes) importants.
Dans cet exemple, nous avons une entité pouvant poser des questions à une autre entité qui lui répond (lui rend un service).
J'obtiens alors une classe "Server" et une classe qui est cliente de ce serveur => "Client".
Je me demande alors quelles sont les opérations applicables à un client :

- envoyer une requête à un serveur
- recevoir une requete du serveur suite à ma requête
- gérer une réponse = faire des actions en rapport avec la réponse obtenue

J'ai pris le parti qu'un client n'a, à un instant donné, qu'une requete (requete courante) et n'obtiendra qu'une seule reponse (reponse courante)
J'obtiens alors les méthodes suivantes pour client :

- sendRequest
- receiveResponse
- handleResponse
- getServer

Remarque : la classe "Client" est abstraite car la façon de gérer une réponse ne peut être connue "à l'avance" d'où la spécialisation avec 
OutPutStreamClient. 
On pourrait effet par exemple, créer un PrinterClient qui serait une imprimante cliente d'un serveur et dont les opérations à effectuer
suite à une réponse serait d'imprimer la réponse (modulo de la mise en page) en utilisant les fonctions natives de l'imprimante et non un flux.

De même, un serveur peut 

- recevoir une requete
- envoyer une réponse
- générer une réponse à partir d'une requete
- accèder aux fichiers physiques : 
	o savoir si une url pointe vers un fichier
	o renvoyer les lignes d'un fichier à partir de son url

J'ai, là encore, pris le parti qu'à un instant donné, un serveur n'a qu'une seule requête à traiter (requete courante) et n'a donc qu'une seule 
réponse à renvoyer (réponse courante).

Il émerge alors les types "Request" et "Response".

Une requete :

- possède une méthode "GET", "POST" etc...
- possède une version, une url etc...
- sait vérifier qu'elle est bien formée -> isWellFormed
- peut être envoyée -> send


Une réponse :

- possède un corps, une version, un content-type etc...
- sait être générée à partir de la requête initiale -> generate
- peut être envoyée -> send


Il reste alors à faire la jointure entre les flux physiques et le métier, ce dernier ne travaillant qu'avec une abstraction des données recues
via les flux.
Ceci donne naissance à StreamHandler qui gère les flux d'entrées et de sorties :

- capte les flux d'entrée afin de "remplir" les requêtes
- initialiser un client particulier utilisant un flux (OutPutStreamClient)

PASSAGE EN MULTITHREAD :

Pour passer en multithread, j'ai : 

- effectué les traitements en parallèle pour tout ce qui est gestion des flux
- créé une classe thread-safe pour tout ce qui est relatif à la manipulation des données en utilisant le mot-clef synchronized -> SafeFileAccessor
- créé plusieurs "threads de StreamHandler" en passant à chacun d'eux la même instance de SafeFileAccessor


***** Plusieurs points à améliorer : *****

- Mieux gérer les exceptions : en créer des customisables afin de mieux gérer les cas limites (ici seules des "Exceptions" sont levées
ce qui n'est pas du tout bon

- Rajouter des classes de tests

- Faire un Regex pour que par exemple l'url "/titi//toto[[//tato.tx" soit reconnue comme non valide (ce qui permet d'obtenir un 
code retour "Bad request")

- Mieux gérer le "favicon" qui est mis dans Response (alors qu'il devrait être dans StreamHandler)

- Mettre dans un fichier de l'arborescence du projet le chemin physique des fichiers du serveur (à l'heure actuelle, il est en dur dans la méthode
run de StreamHandler)

- Il doit y avoir un moyen plus fin de gérer l'accès au fichier : à l'heure actuelle si l'on veut effectuer des traitements deux fichiers différents,
SafeFileAccessor force les thread à le faire les uns après les autres
 