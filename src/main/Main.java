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
		
		// Récupération du flux de sortie pour afficher dans le navigateur
		OutputStream out = null;
		
		Client client = new HttpClient(server, out);
		
		// Lire à partir d'un flux d'entree les données demandées
		String url = "";
		String version= "";
		String method = "";
		
		// Créer une requete à partir de ces données
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
