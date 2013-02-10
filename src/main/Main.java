package main;

import implementation.HttpClient;
import implementation.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
		int internalPort = 2010;
		ServerSocket socketserver = new ServerSocket(internalPort);
		
		while(true)
		{			
			// On attend qu'un client se connecte
			Socket socketduserveur = socketserver.accept();
			
			// Le client est connecté on effectue les traitements
			InputStream input = socketduserveur.getInputStream();
			
			String url = "";
			String version= "";
			String method = "";
			
			try 
			{
				
				BufferedReader buff;			
				
				buff = new BufferedReader (new InputStreamReader(input));	
				String chainePremierLigne = "";
				chainePremierLigne = buff.readLine();
				
				// Si l'on a rien en première ligne, la requete est mal faite donc inutile de continuer
				if((chainePremierLigne == null) || (chainePremierLigne == "")) return;
				
				
				System.out.println(chainePremierLigne);
				
				int positionPremierBlanc = chainePremierLigne.indexOf(" ");
				int positionDeuxiemeBlanc = chainePremierLigne.lastIndexOf(" ");
				
				method = chainePremierLigne.substring(0,positionPremierBlanc);
				url = chainePremierLigne.substring(positionPremierBlanc + 1 , positionDeuxiemeBlanc);
				version = chainePremierLigne.substring(positionDeuxiemeBlanc + 1);
				
			} catch (IOException e) {
				// une erreur est survenue, la requete est invalide
				//this.internalIsValid = false;
			}
			
			
			Server server = new Server();
			
			// Récupération du flux de sortie pour afficher dans le navigateur
			OutputStream out = null;
			
			Client client = new HttpClient(server, out);
			
			
			
			
			// Créer une requete à partir de ces données
			Request request = new Request(client);
			request.setMethod(method);
			request.setURL(url);
			request.setMethod(method);
			
			
			
			try 
			{
				client.sendRequest(request);
				client.handleResponse();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			socketduserveur.close();
		}
		
		
		
		
		
	}

}
