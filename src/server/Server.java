package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server 
{

    ServerSocket socketserver;
    Socket socketduserveur;
	int internalPort;
	
	public Server(int port)
	{
		//waitingRequests = new ArrayList<>();
		internalPort = port;
	}
	
	public void start() throws IOException, InterruptedException
	{
		while(true)
		{
			System.out.println("Début attente 10s");
			
			Thread.sleep(10000);
			
			System.out.println("Fin attente 10s");
			
			socketserver = new ServerSocket(internalPort);
			socketduserveur = socketserver.accept(); 
			InputStream input = socketduserveur.getInputStream();
			Request request = new Request(input);
			request.fill();
	 
			PrintWriter out = new PrintWriter(socketduserveur.getOutputStream());
	        
	        out.println("HTTP/1.1 200 OK");
	        out.println("Content-Type : text/plain");
	        out.println("");
	        out.println("Hello !!");
	        
	        out.flush();
	        socketduserveur.close();
	        socketserver.close();
		}
		
	}
	
	public void stop() throws IOException
	{
		socketduserveur.close();
        socketserver.close();
	}
	
	// requêtes en attente à traiter par le serveur
	//private volatile  ArrayList<Request> waitingRequests;
	
	public void handleRequest(Request request)
	{
		// On regarde si le pool de thread est plein
		// Si ça n'est pas le cas, on utilise un thread pour traiter la requete "request"
		
		
		// Dans le cas contraire on met cette requête en attente
		//waitingRequests.add(request);
	}
	
	public Response returnResponseFromRequest(Request request)
	{
		return null;
	}
}
