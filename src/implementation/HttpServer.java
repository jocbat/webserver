package implementation;

import abstraction.Response;
import abstraction.Server;

public class HttpServer extends Server
{

	@Override
	protected void generateResponse() 
	{
		// TODO Auto-generated method stub
		Response response = new OutPutStreamWrapperResponse(currentRequest);
		currentReponse = response;
		currentReponse.generate();
	}
	
}
