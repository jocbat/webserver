package abstraction;

public abstract class Client 
{
	public Client(Server server)
	{
		this.server = server; 
	}
	
	// serveur à qui on fait des requetes
	private Server server;
	
	/**
	 * serveur à qui on pose des questions
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
			// TODO : il faudrait faire une exception personalisée
			throw new Exception("La requête n'a pas été initié par ce client");
		}
		if(server == null)
		{
			throw new Exception("Aucun serveur n'est défini");
		}
		currentRequest = request;
		request.send();
		
		if(currentResponse == null)
		{
			throw new Exception("Aucune réponse n'a été retournée par le serveur");
		}
	}
	
	/**
	 * Recevoir une réponse du serveur
	 * @param response
	 * @throws Exception
	 */
	public void receiveResponse(Response response) throws Exception
	{
		if(response.getInitialRequest() != currentRequest)
		{
			throw new Exception("La réponse reçue n'est pas issue de la requête courante");
		}
		currentResponse = response;
	}
	/**
	 * Requete courante posée au serveur
	 */
	protected Request currentRequest;
	
	/**
	 * Réponse à la requete courante
	 */
	protected Response currentResponse;
	
	public Response getCurrentResponse() throws Exception
	{
		if (currentRequest == null)
		{
			throw new Exception("Le serveur n'a retourné aucune réponse");
		}
		return currentResponse;
	}
	
	/**
	 * Le comportement à adopter lorsqu'une réponse est reçue
	 */
	public abstract void handleResponse() throws Exception;
	
}
