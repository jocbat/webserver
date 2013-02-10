package abstraction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Response 
{
	// requete dont la reponse courante sera issue
	protected Request initialRequest;
	
	// client pour qui la réponse est destinée
	protected Client getClient()
	{
		return initialRequest.getClient();
	}
	
	public Response(Request request)
	{
		this.initialRequest = request;
		this.body = new ArrayList<>();
	}
	
	protected String version;
	
	protected ArrayList<String> body;
	
	protected String contentType;
	
	public String getVersion() {
		return version;
	}

	public ArrayList<String> getBody() {
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
	
	public Request getInitialRequest() {
		return initialRequest;
	}
	
	/** 
	 * genere la reponse courante en fonction de la requete initiale initialRequest
	 */
	public void generate()
	{
		// TODO : tres moche pour la gestion du favicon.ico...
		if (!"/favicon.ico".equals(initialRequest.getURL()))
		{
			if(initialRequest.isWellFormed())
			{
				// On demande au serveur si l'url de la requete correspond à l'url d'un fichier stocké
				if(getClient().getServer().isFileURL(initialRequest.getURL()))
				{
					this.version = "HTTP/1.1";
					this.status = "200";
					this.statusMeaning = "OKEEEE";
					this.contentType = "Content-Type : text/plain";
											
					String path = getClient().getServer().getFilesPath();
					String filePath = path + initialRequest.getURL();
					
					ArrayList<String> fileLines = new ArrayList<>();
					try 
					{
						fileLines = getClient().getServer().getFileLines(filePath);
						int i;
						for (i = 0; i < fileLines.size(); i++) 
						{
						   System.out.println(fileLines.get(i));
						   body.add(fileLines.get(i));
						}
					} 
					catch (IOException e) 
					{
						this.version = "HTTP/1.1";
						this.status = "500";
						this.statusMeaning = "Internal Server Error";
						this.contentType = "Content-Type : text/plain";
						this.body.add("Erreur interne du serveur...");
					}
				}
				else
				{
					this.version = "HTTP/1.1";
					this.status = "404";
					this.statusMeaning = "File Not Found";
					this.contentType = "Content-Type : text/plain";
					this.body.add("Fichier non trouvé :(");
				}
			}
			else
			{
				this.version = "HTTP/1.1";
				this.status = "300";
				this.statusMeaning = "Bad Request";
				this.contentType = "Content-Type : text/plain";
				this.body.add("Requete mal formée");
			}
		}
	}
	
	
	/**
	 *  envoie la reponse au demandeur ayant fait la requete initialRequest
	 * @throws Exception
	 */
	public void send() throws Exception
	{
		getClient().receiveResponse(this);
	}

}
