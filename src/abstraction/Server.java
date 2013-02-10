package abstraction;

import java.io.File;


public abstract class Server 
{
	int internalPort;
	
	protected Client client;
	
	// Chemin d'acc�s des fichiers que g�re le serveur
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
	 * D�finit currentReponse en fonction de currentRequest
	 * @param request
	 * @return
	 */
	protected abstract Response generateReponse();
	
	/**
	 * Envoie la reponse au client � l'origine de la requete
	 * @param response
	 * @throws Exception
	 */
	public void sendResponse() throws Exception
	{
		if(currentReponse == null)
		{
			throw new Exception("Aucune r�ponse n'a �t� g�n�r�e");
		}
		currentReponse.send();
	}
	
	
	/**
	 * Renvoie vrai si l'url sp�cifi�e pointe vers un fichier
	 * @param url
	 * @return
	 */
	protected boolean isFileURL(String url)
	{
		return true;
	}
	
	/**
	 * R�cup�re un fichier � l'url donn�. Doit v�rifier isFileURL(url)
	 * @param url
	 * @return
	 */
	protected File findFile(String url)
	{
		return null;
	}
	
	
}

