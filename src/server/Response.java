package server;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

// représente une réponse envoyé par un serveur
public class Response 
{
	public Response(Request request, ListenerStream listenerStream)
	{
		this.initialRequest = request;
		this.body = new ArrayList<String>();
	}
	
	// version du protocole
	private String version;
	
	// était de la réponse
	private String status;
	
	// explication du statut
	private String status_meaning;
	
	// requete dont la reponse courante est issue
	private Request initialRequest;
	
	// type de contenu renvoyé
	private String contentType;
	
	// corps de la reponse
	private ArrayList<String> body;
	
	public void send()
	{
		try {
			PrintWriter out = new PrintWriter(initialRequest.getServer().getSocketduserveur().getOutputStream());
			String firstLine = this.version + " " + this.status + " " + this.status_meaning;
			out.println(firstLine);
			out.println(this.contentType);
			out.println("");
			int i;
			for (i=0; i<body.size(); i++) {
				String line = body.get(i);
				System.out.println(line);
			   out.println(body.get(i));
			}
			//out.println(this.body);
			out.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// génère la reponse en fonction de la requete initialRequest
	public void generate()
	{
		// TODO : tres moche pour la gestion du favicon.ico...
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
						this.version = "HTTP/1.1";
						this.status = "200";
						this.status_meaning = "OKEEEE";
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
						this.status_meaning = "File Not Found";
						this.contentType = "Content-Type : text/plain";
						this.body.add("Le fichier n'existe pas");
					}
				}
				else
				{
					this.version = "HTTP/1.1";
					this.status = "400";
					this.status_meaning = "Le fichier n'existe pas";
					this.contentType = "Content-Type : text/plain";
					this.body.add("Requete mal formée");
				}
			}
			else
			// la requete n'est pas valide du à une erreur de traitement coté serveur => Internal Server Error
			{
				this.version = "HTTP/1.1";
				this.status = "500";
				this.status_meaning = "Internal Server Error";
				this.contentType = "Content-Type : text/plain";
				this.body.add("Le serveur a planté...:)");
			}
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
