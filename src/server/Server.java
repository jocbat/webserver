package server;

import java.util.ArrayList;

public class Server 
{
	public Server(String url, int port)
	{
		waitingRequests = new ArrayList<>();
	}
	
	// requ�tes en attente � traiter par le serveur
	private volatile  ArrayList<Request> waitingRequests;
	
	public void handleRequest(Request request)
	{
		// On regarde si le pool de thread est plein
		// Si �a n'est pas le cas, on utilise un thread pour traiter la requete "request"
		
		
		// Dans le cas contraire on met cette requ�te en attente
		waitingRequests.add(request);
	}
	
	public Response returnResponseFromRequest(Request request)
	{
		return null;
	}
}
