package abstraction;

import java.io.File;


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
		currentRequest = request;
		currentReponse = generateReponse();
		//sendResponse(currentReponse);
	}
	
	protected Request currentRequest;
	
	protected Response currentReponse;
		
	/**
	 * Définit currentReponse en fonction de currentRequest
	 * @param request
	 * @return
	 */
	protected abstract Response generateReponse();
	
	/**
	 * Envoie la reponse au client à l'origine de la requete
	 * @param response
	 * @throws Exception
	 */
	public void sendResponse() throws Exception
	{
		if(currentReponse == null)
		{
			throw new Exception("Aucune réponse n'a été générée");
		}
		currentReponse.send();
	}
	
	protected File findFile(String url)
	{
		return null;
	}
}

