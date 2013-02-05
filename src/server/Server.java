package server;

import java.util.ArrayList;

public class Server 
{
	public Server(String url, int port)
	{
		requests = new ArrayList<>();
	}
	
	// requêtes à traiter par le serveur
	private ArrayList<Request> requests;
	
	public void receiveRequest(Request request)
	{
		requests.add(request);
	}
	
	public Response returnResponse(Request request)
	{
		return null;
	}
}
