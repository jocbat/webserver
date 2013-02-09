package abstraction;


public abstract class Server 
{
	int internalPort;
	
	protected Client client;
	
	// Chemin d'accès des fichiers que gère le serveur
	private String filesPath;
	
	public String getFilesPath() {
		return filesPath;
	}

	public void setFilesPath(String filesPath) {
		this.filesPath = filesPath;
	}
	
	public void receiveRequest(Request request) throws Exception
	{
		//Response response = generateResponse(request);
		currentRequest = request;
		generateResponse();
		sendResponse(currentReponse);
	}
	
	protected Request currentRequest;
	
	protected Response currentReponse;
	
	/**
	 * Génère une réponse pour la requete courante "currentRequest"
	 * Doit modifier currentReponse
	 */
	protected abstract void generateResponse();
	
	
		
	/**
	 * Envoie la reponse au client à l'origine de la requete
	 * @param response
	 * @throws Exception
	 */
	public void sendResponse(Response response) throws Exception
	{
		response.send();
	}
}

