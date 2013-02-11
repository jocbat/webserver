package main;

import java.io.IOException;
import java.net.ServerSocket;
import abstraction.StreamHandler;


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
		Thread t1 = new Thread(new StreamHandler(1,socketserver,10000));
		Thread t2 = new Thread(new StreamHandler(2,socketserver,10000));
		Thread t3 = new Thread(new StreamHandler(3,socketserver,10000));
		//Thread t4 = new Thread(new StreamHandler(4,socketserver,1000));
		
		t1.start();
		t2.start();
		
		t3.start();
//		t4.start();		
	}
}
