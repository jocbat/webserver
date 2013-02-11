package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import implementation.OutPutStreamClient;

import org.junit.Test;

import abstraction.Client;
import abstraction.Request;
import abstraction.SafeFileAccessor;
import abstraction.Server;

public class ServerTest 
{

	@Test
	public void whenSendsRequestToServerTheReponseHaveSameRequest() 
	{
		Server server = new Server("D:",new SafeFileAccessor());
		Client client = new OutPutStreamClient(server, null);
		
		try 
		{
			Request r = new Request(client);
			client.sendRequest(r);
			assertTrue(r.equals(client.getCurrentResponse().getInitialRequest()));
		} 
		catch (Exception e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	@Test
	public void isFileURLReturnsTrueWhenItIsURLofFile() 
	{
		Server server = new Server("D:", new SafeFileAccessor());
		boolean isURLGood = server.isFileURL("src/tests/fichierDeTest.txt");
		
		
		assertTrue(isURLGood);
		
	}
	
	@Test
	public void isFileURLReturnsFalseWhenItIsURLofFile() 
	{
		Server server = new Server("D:", new SafeFileAccessor());
		boolean isURLGood = server.isFileURL("src/tests/MauvaisFichier.txt");
		
		
		assertFalse(isURLGood);
		
	}
	
	@Test
	public void getFileLinesReturnsGoodLines() 
	{
		Server server = new Server("D:", new SafeFileAccessor());
		ArrayList<String> returnedList = new ArrayList<>();
		try {
			returnedList = server.getFileLines("src/tests/fichierDeTest.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		assertTrue("A".equals(returnedList.get(0)));
		assertTrue("B".equals(returnedList.get(1)));
		assertTrue("C".equals(returnedList.get(2)));
		assertTrue("D".equals(returnedList.get(3)));
		assertTrue(returnedList.size() == 4);
		
	}
	

}
