package abstraction;

import java.io.File;


public class Server 
{
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
		generateCurrentReponse();
		sendResponse(currentResponse);
	}
	
	protected Request currentRequest;
	
	protected Response currentResponse;
		
	/**
	 * D�finit currentReponse en fonction de currentRequest
	 * @param request
	 * @return
	 */
	protected void generateCurrentReponse()
	{
		currentResponse = new Response(currentRequest);
		currentResponse.generate();
	}
	
	/**
	 * Envoie la reponse courante au client
	 * @param response
	 * @throws Exception
	 */
	protected void sendResponse(Response response) throws Exception
	{
		response.send();
	}
	
	
	/**
	 * Renvoie vrai si l'url sp�cifi�e pointe vers un fichier
	 * @param url
	 * @return
	 */
	public boolean isFileURL(String url)
	{
		return true;
	}
	
	/**
	 * R�cup�re un fichier � l'url donn�. Doit v�rifier isFileURL(url)
	 * @param url
	 * @return
	 */
	public File getFile(String url)
	{
		return null;
	}
	
	
}

