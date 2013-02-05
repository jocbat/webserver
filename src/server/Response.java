package server;

// représente une réponse envoyé par un serveur
public class Response 
{
	// version du protocole
	private String version;
	
	// était de la réponse
	private String status;
	
	// explication du statut
	private String status_meaning;
	
	// requete dont la reponse courante est issue
	private Request initialRequest;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus_meaning() {
		return status_meaning;
	}

	public void setStatus_meaning(String status_meaning) {
		this.status_meaning = status_meaning;
	}
	
	
	
	
}
