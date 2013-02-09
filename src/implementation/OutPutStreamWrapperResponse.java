package implementation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import abstraction.Request;
import abstraction.Response;
/**
 * Reponse wrappant un flux
 * @author jocelyn.batton
 *
 */
public class OutPutStreamWrapperResponse extends Response
{
	
	public OutPutStreamWrapperResponse(Request request) 
	{
		super(request);
		// TODO Auto-generated constructor stub
	}

	@Override
	// génère la reponse en fonction de la requete initialRequest
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
			//}
			//else
			// la requete n'est pas valide du à une erreur de traitement coté serveur => Internal Server Error
			/*{
				this.version = "HTTP/1.1";
				this.status = "500";
				this.statusMeaning = "Internal Server Error";
				this.contentType = "Content-Type : text/plain";
				this.body.add("Le serveur a planté...:)");
			}*/
		}
	}

	/*@Override
	public void send()
	{
		PrintWriter out = new PrintWriter(outPutStream);
		String firstLine = this.version + " " + this.status + " " + this.statusMeaning;
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
		
	}*/
	
	
}
