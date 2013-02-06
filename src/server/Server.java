package server;

import java.util.ArrayList;

public class Server 
{
	public Server(String url, int port)
	{
		waitingRequests = new ArrayList<>();
	}
	
	// requêtes en attente à traiter par le serveur
	private volatile  ArrayList<Request> waitingRequests;
	
	public void handleRequest(Request request)
	{
		// On regarde si le pool de thread est plein
		// Si ça n'est pas le cas, on utilise un thread pour traiter la requete "request"
		
		
		// Dans le cas contraire on met cette requête en attente
		waitingRequests.add(request);
	}
	
	public Response returnResponseFromRequest(Request request)
	{
		return null;
	}
}
