package abstraction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Server 
{
	public Server(String path)
	{
		this.filesPath = path;
	}
	
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
		generateCurrentReponse();
		sendResponse(currentResponse);
	}
	
	protected Request currentRequest;
	
	protected Response currentResponse;
		
	/**
	 * Définit currentReponse en fonction de currentRequest
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
	 * Renvoie vrai si l'url spécifiée pointe vers un fichier
	 * @param url
	 * @return
	 */
	public boolean isFileURL(String url)
	{
		String completePath = filesPath + url;
		File file = new File(completePath);
		return file.isFile();
	}
	
	public ArrayList<String> getFileLines(String filePath) throws IOException 
	{
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		ArrayList<String> returnedList = new ArrayList<>();
		try
		{
			
			String sCurrentLine;
			
			while ((sCurrentLine = br.readLine()) != null) 
			{
				System.out.println(sCurrentLine);
				returnedList.add(sCurrentLine);
			} 
		}
		finally
		{
			br.close();
		}
		
		return returnedList;
	}
	
	
}

