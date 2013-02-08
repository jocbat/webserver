package abstraction;


public abstract class HttpServer 
{
	int internalPort;
	
	// Chemin d'accès des fichiers que gère le serveur
	private String filesPath;
	
	public String getFilesPath() {
		return filesPath;
	}

	public void setFilesPath(String filesPath) {
		this.filesPath = filesPath;
	}

	/**
	 * fzefezf
	 */
	public abstract void start();

	public abstract void stop();
	
	public void handleRequest(HttpRequest request)
	{
		HttpResponse response = generateResponse(request);
		sendResponse(response);
	}
	
	public abstract HttpResponse generateResponse(HttpRequest request);
		
	public void sendResponse(HttpResponse response)
	{
		response.send();
	}
}

