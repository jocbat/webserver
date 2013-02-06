package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

// Représente une requête effectuée sur un serveur
public class Request 
{
	public Request(InputStream input)
	{
		this.inputStream = input;
	}
	
	private InputStream inputStream;
	
	// Méthode appelée par le client (GET, POST...)
	private String method;
	
	// URL demandée
	private String URL;
	
	// version du protocole (1.0, 1.1...)
	private String version;
	
	private boolean internalIsValid;
	
	// la requete courante est-elle valide ?
	public boolean isValid()
	{
		return internalIsValid;
	}
	
	// Permet de "remplir" la requete à l'aide du flux interne inputStream
	public void fill()
	{
		if(inputStream == null)
		{
			throw new NullPointerException("");
		}
		
		BufferedReader buff = new BufferedReader (new InputStreamReader (inputStream));
		String chainePremierLigne = "";
		try {
			chainePremierLigne = buff.readLine();
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
