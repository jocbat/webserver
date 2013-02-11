package abstraction;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Repr�sente un serveur de fichier i.e capable de recevoir des requetes, d'effectuer des actions en cons�quence (ici actions sur une des fichiers)
 * et de renvoyer une r�ponse � l'entit� ayant fait la requ�te sur l'�tat des actions men�es
 * @author jocelyn.batton
 *
 */
public class Server 
{
	public Server(String path, SafeFileAccessor safeFileAccessor)
	{
		this.filesPath = path;
		this.safeFileAccessor = safeFileAccessor;
	}
	
	// permet d'acc�der, �crire, mettre � jour les fichiers de mani�re "thread-safe"
	protected SafeFileAccessor safeFileAccessor;
	
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
		return safeFileAccessor.isPathPointedOnFile(url);
	}
	
	public ArrayList<String> getFileLines(String url) throws IOException 
	{
		return safeFileAccessor.getLines(url);
	}
	
	
}

