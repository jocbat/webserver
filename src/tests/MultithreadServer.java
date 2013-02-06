package tests;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
 
public class MultithreadServer {
 
    public static void main(String[] zero){
         
        ServerSocket socket;
        try {
        socket = new ServerSocket(2009);
        Thread t = new Thread(new Accepter_clients(socket, 1));
        Thread t2 = new Thread(new Accepter_clients(socket, 2));
        Thread t3 = new Thread(new Accepter_clients(socket, 3));
        Thread t4 = new Thread(new Accepter_clients(socket, 4));
        t.start();
        t2.start();
        t3.start();
        t4.start();
        
        System.out.println("Mes employeurs sont prêts !");
         
        } catch (IOException e) {
             
            e.printStackTrace();
        }
    }
}
 
class Accepter_clients implements Runnable {
 
       private ServerSocket socketserver;
       private Socket socket;
       private int num;
       private int nbrclient = 1;
        public Accepter_clients(ServerSocket s, int num){
            socketserver = s;
            this.num = num;
        }
         
        public void run() {
 
            try {
                while(true){
              socket = socketserver.accept(); // Un client se connecte on l'accepte
                      System.out.println("client "+nbrclient+ " est connecté !" + " canal = " + num);
                      
                      
                      PrintWriter out = new PrintWriter(socket.getOutputStream());
          	        
          	        out.println("HTTP/1.1 200 OK");
          	        out.println("Content-Type : text/plain");
          	        out.println("");
          	        out.println("Hello !! " + "canal : " + num + " client : "+ nbrclient);
          	        
          	        out.flush();
          	      nbrclient++; 
                      
                      socket.close();
                      try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
             
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
 
    }
