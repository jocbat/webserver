package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{
	int internalPort;
	
	public Server(int port)
	{
		//waitingRequests = new ArrayList<>();
		internalPort = port;
	}
	
	// Chemin d'accès des fichiers que gère le serveur
	private String filesPath;
	
	public String getFilesPath() {
		return filesPath;
	}

	public void setFilesPath(String filesPath) {
		this.filesPath = filesPath;
	}
	
	private ListenerStream listener;

	public void start() throws IOException, InterruptedException
	{
		// on demarre l'ecoute sur le port à l'aide d'un ecouteur de flux
		listener = new ListenerStream(internalPort, this);
		listener.start();	
	}
	
	public void stop() throws IOException
	{
		listener.stop();
	}
	
	public synchronized void handleRequest(Request request)
	{
		Response response = new Response(request);
		response.generate();
		
		//listener.send(response);
		response.send();
	}
	
	public void receiveRequest(Request request)
	{
		
	}
	
	public void sendResponse(Response response)
	{
		response.send();
	}
}
