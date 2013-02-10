package implementation;

import java.io.OutputStream;
import java.io.PrintWriter;

import abstraction.Client;
import abstraction.Response;
import abstraction.Server;

public class HttpClient extends Client
{

	public HttpClient(Server server, OutputStream outPutStream) 
	{
		super(server);
		// TODO Auto-generated constructor stub
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
			System.out.println(line);
		   out.println(currentResponse.getBody().get(i));
		}
		//out.println(this.body);
		out.flush();
	}

	

}
