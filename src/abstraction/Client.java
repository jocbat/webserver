package abstraction;

public abstract class Client 
{
	public Client(Server server)
	{
		this.server = server; 
	}
	
	// serveur � qui on fait des requetes
	private Server server;
	
	/**
	 * serveur � qui on pose des questions
	 * @return
	 */
	public Server getServer() 
	{
		return server;
	}

	/**
	 * Poser une question au serveur
	 * @param request
	 * @throws Exception 
	 */
	public void sendRequest(Request request) throws Exception
	{
		if(request.getClient() != this)
		{
			// TODO : il faudrait faire une exception personalis�e
			throw new Exception("La requ�te n'a pas �t� initi� par ce client");
		}
		if(server == null)
		{
			throw new Exception("Aucun serveur n'est d�fini");
		}
		currentRequest = request;
		//server.receiveRequest(request);
		request.send();
	}
	
	/**
	 * Recevoir une r�ponse du serveur
	 * @param response
	 * @throws Exception
	 */
	public void receiveResponse(Response response)
	{
		currentResponse = response;
	}
	/**
	 * Requete courante pos�e au serveur
	 */
	protected Request currentRequest;
	
	/**
	 * R�ponse � la requete courante
	 */
	protected Response currentResponse;
	
	public Response getCurrentResponse() throws Exception
	{
		if (currentRequest == null)
		{
			throw new Exception("Le client actuel n'a effectu� aucune requete au serveur");
		}
		return currentResponse;
	}
	
	/**
	 * Le comportement � adopter lorsqu'une r�ponse est re�ue
	 */
	public abstract void handleResponse() throws Exception ;
	
}
