package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import abstraction.HttpRequest;

/**
 * Requete http wrappant un flux
 * @author jocelyn.batton
 *
 */
public class SocketRequest extends HttpRequest
{
	private InputStream inputStreamWrapped;
	
	public SocketRequest(InputStream inputStream)
	{
		this.inputStreamWrapped = inputStream;
	}
	
	/**
	 * Remplir la requete courante à partir de inputStreamWrapped
	 */
	public void fill()
	{
		
		try {
			
			BufferedReader buff;			
			
			buff = new BufferedReader (new InputStreamReader(inputStreamWrapped));	
			String chainePremierLigne = "";
			chainePremierLigne = buff.readLine();
			
			// Si l'on a rien en première ligne, la requete est mal faite donc inutile de continuer
			if((chainePremierLigne == null) || (chainePremierLigne == "")) return;
			
			
			System.out.println(chainePremierLigne);
			
			int positionPremierBlanc = chainePremierLigne.indexOf(" ");
			int positionDeuxiemeBlanc = chainePremierLigne.lastIndexOf(" ");
			
			String methode = chainePremierLigne.substring(0,positionPremierBlanc);
			String url = chainePremierLigne.substring(positionPremierBlanc + 1 , positionDeuxiemeBlanc);
			String version = chainePremierLigne.substring(positionDeuxiemeBlanc + 1);
			
			this.method = methode;
			this.URL = url;
			this.version = version;
			//this.internalIsValid = true;
		} catch (IOException e) {
			// une erreur est survenue, la requete est invalide
			//this.internalIsValid = false;
		}
	}
}
