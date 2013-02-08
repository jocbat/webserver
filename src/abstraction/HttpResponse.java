package abstraction;

public abstract class HttpResponse 
{
	// requete dont la reponse courante sera issue
	protected HttpRequest initialRequest;
	
	protected String version;
	
	protected String body;
	
	protected String contentType;
	
	public String getVersion() {
		return version;
	}

	public String getBody() {
		return body;
	}

	public String getContentType() {
		return contentType;
	}

	public String getStatus() {
		return status;
	}

	public String getStatusMeaning() {
		return statusMeaning;
	}

	protected String status;
	
	protected String statusMeaning;
	
	public HttpRequest getInitialRequest() {
		return initialRequest;
	}
	
	// genere la reponse courante en fonction de la requete initiale initialRequest
	public abstract void generate();
	
	// envoie la reponse au demandeur ayant fait la requete initialRequest
	public abstract void send(); 

}
