package main;

import implementation.HttpClient;
import implementation.HttpServer;

import java.io.IOException;
import java.io.OutputStream;

import abstraction.Client;
import abstraction.Request;
import abstraction.Server;


public class Main 
{

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		Server server = new HttpServer();
		
		// R�cup�ration du flux de sortie pour afficher dans le navigateur
		OutputStream out = null;
		
		Client client = new HttpClient(server, out);
		
		// Lire � partir d'un flux d'entree les donn�es demand�es
		String url = "";
		String version= "";
		String method = "";
		
		// Cr�er une requete � partir de ces donn�es
		Request request = new Request(client);
		request.setMethod(method);
		request.setURL(url);
		request.setMethod(method);
		
		
		
		try 
		{
			client.sendRequest(request);
			server.sendResponse();
			client.handleResponse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
