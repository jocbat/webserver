package server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ListenerStream 
{
	public void start()
	{
		try {
			socketserver = new ServerSocket(port);
			while(true)
			{
				socketduserveur = socketserver.accept(); 
				
				// recuperation du flux physique
				InputStream inputStream = socketduserveur.getInputStream();
				
				handleStream(inputStream);
				
		        //socketserver.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void handleStream(InputStream inputStream)
	{
		// on wrappe le flux physique
		Request request = new Request(inputStream);
		request.fill();
		
		// on demande au serveur logique de traiter la demande
		server.handleRequest(request);
	}
	
	public void stop()
	{
		
	}
	
	public ListenerStream(int port,Server server)
	{
		this.port = port;
		this.server = server;
	}
	
	// port d'écoute physique
	private int port;
	
	// serveur "logique" responsable de la gestion au niveau "métier" des requetes
	Server server;
	
	ServerSocket socketserver;
    Socket socketduserveur;
    
	}
