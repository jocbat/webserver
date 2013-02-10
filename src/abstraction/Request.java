package abstraction;

import junit.runner.Version;

public class Request 
{
	protected Client client;
	
	public Client getClient() {
		return client;
	}

	// la méthode invoquée (GET, POST...)
	protected String method;
	
	// l'url du fichier demandé
	protected String URL;
	
	public void setMethod(String method) {
		this.method = method;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public void setVersion(String version) {
		this.version = version;
	}

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
	
	public Request(Client client)
	{
		this.client = client; // client faisant la requete
	}
	
	/**
	 *  La requete est-elle bien normée ?
	 * 
	 */
	public boolean isWellFormed()
	{
		// TODO : à faire plus propre...
		boolean isGoodMethod = ("GET".equals(method)) || ("POST".equals(method)) || ("PUT".equals(method)) || ("DELETE".equals(method));
		boolean isGoodVersion = ("HTTP/1.1".equals(version));
		boolean isURLWellFormed = isURLWellFormed(URL);
		return isGoodMethod && isGoodVersion && isURLWellFormed;
	}
	
	private boolean isURLWellFormed(String url)
	{
		// TODO : Utiliser un regex (ou une librairie java ?)
		return true;
	}
	
	public void send() throws Exception
	{
		//client.sendRequest(this);
		getClient().getServer().receiveRequest(this);
	}
	
}
