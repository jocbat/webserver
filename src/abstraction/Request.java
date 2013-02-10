package abstraction;

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
		boolean isGoodMethod = (method == "GET") || (method == "POST") || (method == "PUT") || (method == "DELETE");
		boolean isGoodVersion = (method == "0.9") || (method == "1.0") || (method == "1.1");
		return isGoodMethod && isGoodVersion;
	}
	
	public void send() throws Exception
	{
		//client.sendRequest(this);
		getClient().getServer().receiveRequest(this);
	}
	
}
