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
	public void receiveResponse(Response response) throws Exception 
	{
		// TODO Auto-generated method stub
		PrintWriter out = new PrintWriter(outPutStream);
		String firstLine = response.getVersion() + " " + response.getStatus() + " " + response.getStatusMeaning();
		out.println(firstLine);
		out.println(response.getContentType());
		out.println("");
		int i;
		for (i=0; i<response.getBody().size(); i++) {
			String line = response.getBody().get(i);
			System.out.println(line);
		   out.println(response.getBody().get(i));
		}
		//out.println(this.body);
		out.flush();
	}

	

}
