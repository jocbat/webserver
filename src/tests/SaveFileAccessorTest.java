package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import abstraction.SafeFileAccessor;

public class SaveFileAccessorTest 
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
}
