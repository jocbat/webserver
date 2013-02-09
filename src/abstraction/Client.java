package abstraction;

public abstract class Client 
{
	public Client(Server server)
	{
		this.server = server;
	}
	
	// serveur � qui on pose des questions
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
			throw new Exception("La requ�te n'est pas destin�e � ce client");
		}
		server.receiveRequest(request);
	}
	
	/**
	 * Recevoir une r�ponse du serveur
	 * @param response
	 * @throws Exception
	 */
	public abstract void receiveResponse(Response response) throws Exception;

	
}
