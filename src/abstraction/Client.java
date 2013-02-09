package abstraction;

public abstract class Client 
{
	public Client(Server server)
	{
		this.server = server;
	}
	
	// serveur à qui on pose des questions
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
			throw new Exception("La requête n'est pas destinée à ce client");
		}
		server.receiveRequest(request);
	}
	
	/**
	 * Recevoir une réponse du serveur
	 * @param response
	 * @throws Exception
	 */
	public abstract void receiveResponse(Response response) throws Exception;

	
}
