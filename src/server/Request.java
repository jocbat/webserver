package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

// Repr�sente une requ�te effectu�e sur un serveur
public class Request 
{
	public Request(Socket socket)
	{
		this.socketduserveur = socket;
	}
	
	// Socket d'ou provient la requete courante
	private Socket socketduserveur;
	
	public Socket getSocketduserveur() {
		return socketduserveur;
	}

	// M�thode appel�e par le client (GET, POST...)
	private String method;
	
	// URL demand�e
	private String URL;
	
	// version du protocole (1.0, 1.1...)
	private String version;
	
	private boolean internalIsValid = false;
	
	// la requete courante est-elle valide ?
	public boolean isValid()
	{
		return internalIsValid;
	}
	
	// Permet de "remplir" la requete � l'aide du flux interne inputStream
	public void fill()
	{
		
		try {
			BufferedReader buff = new BufferedReader (new InputStreamReader (socketduserveur.getInputStream()));
			String chainePremierLigne = "";
			chainePremierLigne = buff.readLine();
			
			// Si l'on a rien en premi�re ligne, la requete est mal faite donc inutile de continuer
			if((chainePremierLigne == null) || (chainePremierLigne == "")) return;
			
			
			System.out.println(chainePremierLigne);
			
			//if (chainePremierLigne == null) return;
			
			int positionPremierBlanc = chainePremierLigne.indexOf(" ");
			int positionDeuxiemeBlanc = chainePremierLigne.lastIndexOf(" ");
			
			String methode = chainePremierLigne.substring(0,positionPremierBlanc);
			String url = chainePremierLigne.substring(positionPremierBlanc + 1 , positionDeuxiemeBlanc);
			String version = chainePremierLigne.substring(positionDeuxiemeBlanc + 1);
			
			this.method = methode;
			this.URL = url;
			this.version = version;
		} catch (IOException e) {
			// une erreur est survenue, la requete est invalide
			this.internalIsValid = false;
		}
	}
	
	// la requete courante respecte la norme http ?
	public boolean isWellFormed()
	{
		// l'url doit �tre de la forme "/toto/tutu/titi.xml"
		return true;
	}
	
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}
