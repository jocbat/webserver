package abstraction;

import implementation.OutPutStreamClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class StreamHandler implements Runnable {

	private int numero;
	private int waitingTime;
	
	public StreamHandler(int numero, ServerSocket socketserver, int waitingTime)
	{
		this.numero = numero;
		this.socketserver = socketserver;
		this.waitingTime = waitingTime;
	}
	
	private ServerSocket socketserver;
	
	@Override
	public void run() 
	{
		String url = "";
		String version= "";
		String method = "";
				
		while(true)
		{			
			// On attend qu'un client se connecte
			Socket socketduserveur = null;
			try 
			{
				socketduserveur = socketserver.accept();
				System.out.println("Debut prise en compte de la requete -> prise en charge par le thread num�ro : " + numero);
				
				// Le client est connect� on effectue les traitements
				InputStream input = socketduserveur.getInputStream();
				
				// R�cup�ration du flux de sortie pour afficher dans le navigateur
				OutputStream out = socketduserveur.getOutputStream();
				BufferedReader buff;			
				
				buff = new BufferedReader (new InputStreamReader(input));
				String chainePremierLigne = "";
				chainePremierLigne = buff.readLine();
				
				System.out.println("Premi�re ligne de la requ�te " + chainePremierLigne);
				
				// Si l'on a rien en premi�re ligne, la requete est mal faite donc inutile de continuer
				if((chainePremierLigne == null) || (chainePremierLigne == "")) continue;
				
				int positionPremierBlanc = chainePremierLigne.indexOf(" ");
				int positionDeuxiemeBlanc = chainePremierLigne.lastIndexOf(" ");
				
				method = chainePremierLigne.substring(0,positionPremierBlanc);
				url = chainePremierLigne.substring(positionPremierBlanc + 1 , positionDeuxiemeBlanc);
				version = chainePremierLigne.substring(positionDeuxiemeBlanc + 1);
				SafeFileAccessor safeFileAccessor = new SafeFileAccessor();
				
				// On renseigne ici le chemin physique du disque sur lequel le serveur ira lire et �crire
				Server server = new Server("D:", safeFileAccessor);
				
				Client client = new OutPutStreamClient(server, out);
				
				// Cr�er une requete � partir de ces donn�es
				Request request = new Request(client);
				request.setMethod(method);
				request.setURL(url);
				request.setVersion(version);
				
				
				client.sendRequest(request);
				// On affiche les donn�es dans le navigateur
				client.handleResponse();
			} 
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			finally
			{
				System.out.println("Fin prise en compte de la requ�te -> prise en charge par le thread num�ro : " + numero);	
				System.out.println(" ");
				try {
					socketduserveur.close();
					System.out.println("D�but attente de " + waitingTime + " secondes");
					Thread.sleep(waitingTime);
					System.out.println("Fin attente de " + waitingTime + " secondes");
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			
		}
		
		
	}

}
