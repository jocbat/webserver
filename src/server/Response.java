package server;

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
			PrintWriter out = new PrintWriter(initialRequest.getSocketduserveur().getOutputStream());
			
			if (!"/favicon.ico".equals(initialRequest.getURL()))
			{
				/*out.println("HTTP/1.1 200 OK");
		        out.println("Content-Type : text/plain");
		        out.println("");
		        out.println("Hello !!");*/
				if(initialRequest.isValid())
				{
					if(initialRequest.isWellFormed())
					{
						
					}
				}
				
				
		        out.flush();
			}
			initialRequest.getSocketduserveur().close();
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
