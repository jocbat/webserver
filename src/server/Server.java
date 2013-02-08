package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{

    public ServerSocket getSocketserver() {
		return socketserver;
	}

	public Socket getSocketduserveur() {
		return socketduserveur;
	}

	ServerSocket socketserver;
    Socket socketduserveur;
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

	public void start() throws IOException, InterruptedException
	{
		socketserver = new ServerSocket(internalPort);
		while(true)
		{
			/*System.out.println("Début attente 10s");
			
			Thread.sleep(10000);
			
			System.out.println("Fin attente 10s");*/
			//System.out.println("hello1");
			
			socketduserveur = socketserver.accept(); 

			//System.out.println("hello2");
			
			//InputStream input = socketduserveur.getInputStream();
			
//			int read = input.read();
//			StringBuilder st = new StringBuilder();
//			while (read != -1) {
//				st.append((char)read);
//				System.out.print(read + " " + (char)read);
//				read = input.read();
//			}
//			System.out.println(st.toString());
			Request request = new Request(this);
			request.fill();
			
			
			Response response = new Response(request);
			response.generate();
			response.send();
			//PrintWriter out = new PrintWriter(socketduserveur.getOutputStream());
	        
			// TODO : mieux gérer cela car ici rustine...
			//if (!"/favicon.ico".equals(request.getURL()))
			//{
				//out.println("HTTP/1.1 200 OK");
		        //out.println("Content-Type : text/plain");
		        //out.println("");
		        //out.println("Hello !!");
			//}
			
	        
	        
	        //out.flush();
	        socketduserveur.close();
	        
	        //socketserver.close();
		}
		
	}
	
	public void stop() throws IOException
	{
		//socketduserveur.close();
        socketserver.close();
	}
	
	// requêtes en attente à traiter par le serveur
	//private volatile  ArrayList<Request> waitingRequests;
	
	public synchronized void handleRequest(Request request)
	{
		
	}
	
	public Response returnResponseFromRequest(Request request)
	{
		return null;
	}
}
