package server;

// Représente une requête effectuée sur un serveur
public class Request 
{
	// Méthode appelée par le client (GET, POST...)
	private String method;
	
	// URL demandée
	private String URL;
	
	// version du protocole
	private String version;
	
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
