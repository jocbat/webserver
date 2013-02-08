package abstraction;

public class HttpRequest 
{
	// la m�thode invoqu�e (GET, POST...)
	protected String method;
	
	// l'url du fichier demand�
	protected String URL;
	
	// version http de la requete
	protected String version;
	
	public String getMethod() {
		return method;
	}
	public String getURL() {
		return URL;
	}
	public String getVersion() {
		return version;
	}
	
	/**
	 *  La requete est-elle bien norm�e ?
	 * 
	 */
	public boolean isWellFormed()
	{
		boolean isGoodMethod = (method == "GET") || (method == "POST") || (method == "PUT") || (method == "DELETE");
		boolean isGoodVersion = (method == "0.9") || (method == "1.0") || (method == "1.1");
		return isGoodMethod && isGoodVersion;
	}
	
}
