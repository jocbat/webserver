package main;

import java.io.IOException;

import server.Server;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		Server server = new Server(2010);
		server.start();
	}

}
