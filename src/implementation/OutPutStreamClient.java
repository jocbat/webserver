package implementation;

import java.io.OutputStream;
import java.io.PrintWriter;

import abstraction.Client;
import abstraction.Server;

/**
 * Représente un client wrappant un flux de sortie pour gérer les actions dûes aux réponses du serveur
 * @author jocelyn.batton
 *
 */
public class OutPutStreamClient extends Client
{

	public OutPutStreamClient(Server server, OutputStream outPutStream) 
	{
		super(server);
		this.outPutStream = outPutStream;
	}
	
	private OutputStream outPutStream;
	
	public OutputStream getOutPutStream() {
		return outPutStream;
	}

	@Override
	public void handleResponse() throws Exception 
	{
		if(currentResponse == null)
		{
			throw new Exception("Aucune réponse n'a été générée");
		}
		PrintWriter out = new PrintWriter(outPutStream);
		String firstLine = currentResponse.getVersion() + " " + currentResponse.getStatus() + " " + currentResponse.getStatusMeaning();
		out.println(firstLine);
		out.println(currentResponse.getContentType());
		out.println("");
		int i;
		for (i=0; i<currentResponse.getBody().size(); i++) {
			String line = currentResponse.getBody().get(i);
		   out.println(line);
		}
		out.flush();
	}

	

}
