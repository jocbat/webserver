package main;

import java.io.IOException;

import abstraction.Client;
import abstraction.Request;
import abstraction.Server;


public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		//Server server = new Server(2010);
		//server.start();
		Client client = null;
		Server server = null;
		Request request = new Request(client);
		
		try {
			client.sendRequest(request);
			client.handleResponse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
