package abstraction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Response 
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
			//if(initialRequest.isValid())
			//{
				if(initialRequest.isWellFormed())
				{
					String pathFile = "D:" + initialRequest.getURL();
					File file = new File(pathFile);
					if(file.isFile())
					{
						this.version = "HTTP/1.1";
						this.status = "200";
						this.statusMeaning = "OKEEEE";
						this.contentType = "Content-Type : text/plain";
						BufferedReader br = null;
						
						try {
							
							String sCurrentLine;
				 
							br = new BufferedReader(new FileReader(pathFile));
							while ((sCurrentLine = br.readLine()) != null) {
								System.out.println(sCurrentLine);
								body.add(sCurrentLine);
								//out.println(sCurrentLine);									
							}
				 
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							try {
								if (br != null)br.close();
							} catch (IOException ex) {
								ex.printStackTrace();
							}
						}
					}
					else
					{
						this.version = "HTTP/1.1";
						this.status = "404";
						this.statusMeaning = "File Not Found";
						this.contentType = "Content-Type : text/plain";
						this.body.add("Le fichier n'existe pas");
					}
				}
				else
				{
					this.version = "HTTP/1.1";
					this.status = "400";
					this.statusMeaning = "Le fichier n'existe pas";
					this.contentType = "Content-Type : text/plain";
					this.body.add("Requete mal formée");
				}
		}
	}
	
	// envoie la reponse au demandeur ayant fait la requete initialRequest
	public void send() throws Exception
	{
		getClient().receiveResponse(this);
	}

}
