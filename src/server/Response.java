package server;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

// représente une réponse envoyé par un serveur
public class Response 
{
	public Response(Request request)
	{
		this.initialRequest = request;
	}
	
	// version du protocole
	private String version;
	
	// était de la réponse
	private String status;
	
	// explication du statut
	private String status_meaning;
	
	// requete dont la reponse courante est issue
	private Request initialRequest;
	
	// envoit la reponse courante au client ayant fait la requete "initialRequest"
	public void send()
	{
		try {
			PrintWriter out = new PrintWriter(initialRequest.getServer().getSocketduserveur().getOutputStream());
			
			if (!"/favicon.ico".equals(initialRequest.getURL()))
			{
				if(initialRequest.isValid())
				{
					if(initialRequest.isWellFormed())
					{
						String pathFile = "D:" + initialRequest.getURL();
						File file = new File(pathFile);
						if(file.isFile())
						{
							BufferedReader br = null;
							
							try {
								
								String sCurrentLine;
					 
								br = new BufferedReader(new FileReader(pathFile));
								
								while ((sCurrentLine = br.readLine()) != null) {
									System.out.println(sCurrentLine);
									
									out.println(sCurrentLine);									
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
							out.println("HTTP/1.1 404 File Not Found");
					        out.println("Content-Type : text/plain");
					        out.println("");
					        out.println("Le fichier n'existe pas");
						}
					}
					else
					{
						out.println("HTTP/1.1 400 Bad request");
				        out.println("Content-Type : text/plain");
				        out.println("");
				        out.println("Requete mal formée");
					}
				}
				else
				{
					out.println("HTTP/1.1 500 Internal Server Error");
			        out.println("Content-Type : text/plain");
			        out.println("");
			        out.println("Le serveur a planté...:)");
				}
				
				
		        out.flush();
			}
			initialRequest.getServer().getSocketduserveur().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus_meaning() {
		return status_meaning;
	}

	public void setStatus_meaning(String status_meaning) {
		this.status_meaning = status_meaning;
	}
	
}
