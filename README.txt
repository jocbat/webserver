***** Notes sur la conception : *****

- Je me suis servi des sockets afin de r�aliser ce mini web server

- J'ai tent� de trouver les meilleures abstractions (i.e mod�lisation) afin 
	o d'affranchir le plus possible les fonctionnalit�s du syst�me des contraintes physiques des donn�es manipul�es par celui-ci 
	(ici typiquement les InputStream et OutputStream)
	
	o d'obtenir un ensemble coh�rent dans lequel la "place" des nouvelles fonctionnalit�s des �volutions sera assez simple � trouver
		-> Corollaire : cela rend plus simple l'approche multithreading
		
***** Que fait ce programme ? *****

Il permet d'afficher au travers d'un navigateur (ou tout autre client utilisant un flux de sortie) un fichier enregistr� sur le disque (param�trable 
lors de l'instanciation du serveur)
Seulement deux cas sont g�r�s :

	- Le fichier est � l'url correspondante => on affiche les lignes de ce fichier
	- L'url n'est pas valide ou bien elle est valide mais le fichier n'existe pas => on obtient une r�ponse indiquant que le fichier n'a pu �tre trouv�
	- Dans le cas o� la lecture se passerait mal (IOException) celle-ci est catch�e et renvoit un message "Internal Server Error"
	
	
***** Description de la mod�lisation *****

Avant de penser � g�rer le multithreading, j'ai cherch� � d�gager les types (= classes) importants.
Dans cet exemple, nous avons une entit� pouvant poser des questions � une autre entit� qui lui r�pond (lui rend un service).
J'obtiens alors une classe "Server" et une classe qui est cliente de ce serveur => "Client".
Je me demande alors quelles sont les op�rations applicables � un client :

- envoyer une requ�te � un serveur
- recevoir une requete du serveur suite � ma requ�te
- g�rer une r�ponse = faire des actions en rapport avec la r�ponse obtenue

J'ai pris le parti qu'un client n'a, � un instant donn�, qu'une requete (requete courante) et n'obtiendra qu'une seule reponse (reponse courante)
J'obtiens alors les m�thodes suivantes pour client :

- sendRequest
- receiveResponse
- handleResponse
- getServer

Remarque : la classe "Client" est abstraite car la fa�on de g�rer une r�ponse ne peut �tre connue "� l'avance" d'o� la sp�cialisation avec 
OutPutStreamClient. 
On pourrait effet par exemple, cr�er un PrinterClient qui serait une imprimante cliente d'un serveur et dont les op�rations � effectuer
suite � une r�ponse serait d'imprimer la r�ponse (modulo de la mise en page) en utilisant les fonctions natives de l'imprimante et non un flux.

De m�me, un serveur peut 

- recevoir une requete
- envoyer une r�ponse
- g�n�rer une r�ponse � partir d'une requete
- acc�der aux fichiers physiques : 
	o savoir si une url pointe vers un fichier
	o renvoyer les lignes d'un fichier � partir de son url

J'ai, l� encore, pris le parti qu'� un instant donn�, un serveur n'a qu'une seule requ�te � traiter (requete courante) et n'a donc qu'une seule 
r�ponse � renvoyer (r�ponse courante).

Il �merge alors les types "Request" et "Response".

Une requete :

- poss�de une m�thode "GET", "POST" etc...
- poss�de une version, une url etc...
- sait v�rifier qu'elle est bien form�e -> isWellFormed
- peut �tre envoy�e -> send


Une r�ponse :

- poss�de un corps, une version, un content-type etc...
- sait �tre g�n�r�e � partir de la requ�te initiale -> generate
- peut �tre envoy�e -> send


Il reste alors � faire la jointure entre les flux physiques et le m�tier, ce dernier ne travaillant qu'avec une abstraction des donn�es recues
via les flux.
Ceci donne naissance � StreamHandler qui g�re les flux d'entr�es et de sorties :

- capte les flux d'entr�e afin de "remplir" les requ�tes
- initialiser un client particulier utilisant un flux (OutPutStreamClient)

PASSAGE EN MULTITHREAD :

Pour passer en multithread, j'ai : 

- effectu� les traitements en parall�le pour tout ce qui est gestion des flux
- cr�� une classe thread-safe pour tout ce qui est relatif � la manipulation des donn�es en utilisant le mot-clef synchronized -> SafeFileAccessor
- cr�� plusieurs "threads de StreamHandler" en passant � chacun d'eux la m�me instance de SafeFileAccessor


***** Plusieurs points � am�liorer : *****

- Mieux g�rer les exceptions : en cr�er des customisables afin de mieux g�rer les cas limites (ici seules des "Exceptions" sont lev�es
ce qui n'est pas du tout bon

- Rajouter des classes de tests

- Faire un Regex pour que par exemple l'url "/titi//toto[[//tato.tx" soit reconnue comme non valide (ce qui permet d'obtenir un 
code retour "Bad request")

- Mieux g�rer le "favicon" qui est mis dans Response (alors qu'il devrait �tre dans StreamHandler)

- Mettre dans un fichier de l'arborescence du projet le chemin physique des fichiers du serveur (� l'heure actuelle, il est en dur dans la m�thode
run de StreamHandler)

- Il doit y avoir un moyen plus fin de g�rer l'acc�s au fichier : � l'heure actuelle si l'on veut effectuer des traitements deux fichiers diff�rents,
SafeFileAccessor force les thread � le faire les uns apr�s les autres
 