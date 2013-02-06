package tests;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


import org.junit.Test;

import server.Request;

public class RequestTest 
{

	@Test
	public void fillRequestWithNotNullInputStreamRetrieveMethodURLAndVersionWell() throws FileNotFoundException 
	{
		
		FileInputStream inputStream;
		File file = new File("D:/Travail_Java/simple-5.0.4/WebServer/src/tests/mockrequest.txt");
		inputStream = new FileInputStream(file);
		Request request = new Request(inputStream);
		
		request.fill();
		
		assertEquals(request.getMethod(), "GET");
		assertEquals(request.getURL(), "/toto.html");
		assertEquals(request.getVersion(), "HTTP/1.1");

	}

}
