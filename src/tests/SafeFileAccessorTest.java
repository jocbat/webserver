package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import abstraction.SafeFileAccessor;

public class SafeFileAccessorTest 
{

	@Test
	public void isPathPointedOnFileReturnsTrueWhenFileExists() 
	{
		SafeFileAccessor fileAccessor = new SafeFileAccessor();
		
		// TODO : faire pointer via une url en relatif pour que les tests passent chez tous les développeurs
		boolean isAFile = fileAccessor.isPathPointedOnFile("D:/Travail_Java/simple-5.0.4/WebServer/src/tests/fichierDeTest.txt");
		
		assertTrue(isAFile);
	}
	
	
	@Test
	public void isPathPointedOnFileReturnsFalseWhenFileDoesNotExist() 
	{
		SafeFileAccessor fileAccessor = new SafeFileAccessor();
		
		// TODO : faire pointer via une url en relatif pour que les tests passent chez tous les développeurs
		boolean isAFile = fileAccessor.isPathPointedOnFile("D:/Travail_Java/simple-5.0.4/WebServer/src/tests/PasDeFichier.txt");
		
		assertFalse(isAFile);
	}
	
	
	@Test
	public void getLinesReturnsGoodLines() 
	{
		SafeFileAccessor fileAccessor = new SafeFileAccessor();
		
		// TODO : faire pointer via une url en relatif pour que les tests passent chez tous les développeurs
		ArrayList<String> lines = fileAccessor.getLines("D:/Travail_Java/simple-5.0.4/WebServer/src/tests/fichierDeTest.txt");
		
		assertTrue("A".equals(lines.get(0)));
		assertTrue("B".equals(lines.get(1)));
		assertTrue("C".equals(lines.get(2)));
		assertTrue("D".equals(lines.get(3)));
		assertTrue(lines.size() == 4);
	}
	
	@Test
	public void isPathPointedOnFileIsThreadSafe() 
	{
		SafeFileAccessor fileAccessor = new SafeFileAccessor();
		
		// TODO : faire pointer via une url en relatif pour que les tests passent chez tous les développeurs
//		ArrayList<String> lines = fileAccessor.getLines("D:/Travail_Java/simple-5.0.4/WebServer/src/tests/fichierDeTest.txt");
//		Thread t1 = new Thread(new FileAccessorThread(fileAccessor, "D:/Travail_Java/simple-5.0.4/WebServer/src/tests/fichierDeTest.txt", 1));
//		Thread t2 = new Thread(new FileAccessorThread(fileAccessor, "D:/Travail_Java/simple-5.0.4/WebServer/src/tests/fichierDeTest.txt", 2));
//		Thread t3 = new Thread(new FileAccessorThread(fileAccessor, "D:/Travail_Java/simple-5.0.4/WebServer/src/tests/fichierDeTest.txt", 3));
//		Thread t4 = new Thread(new FileAccessorThread(fileAccessor, "D:/Travail_Java/simple-5.0.4/WebServer/src/tests/fichierDeTest.txt", 4));
//		Thread t5 = new Thread(new FileAccessorThread(fileAccessor, "D:/Travail_Java/simple-5.0.4/WebServer/src/tests/fichierDeTest.txt", 5));
//		Thread t6 = new Thread(new FileAccessorThread(fileAccessor, "D:/Travail_Java/simple-5.0.4/WebServer/src/tests/fichierDeTest.txt", 6));
//		
//		t1.start();
//		t2.start();
//		t3.start();
//		t4.start();
//		t5.start();
//		t6.start();
	}
}
