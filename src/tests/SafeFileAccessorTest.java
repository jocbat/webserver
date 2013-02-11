package tests;

import static org.junit.Assert.*;

import java.io.File;
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
		boolean isAFile = fileAccessor.isPathPointedOnFile("src/tests/fichierDeTest.txt");
		
		assertTrue(isAFile);
	}
	
	
	@Test
	public void isPathPointedOnFileReturnsFalseWhenFileDoesNotExist() 
	{
		SafeFileAccessor fileAccessor = new SafeFileAccessor();
		
		// TODO : faire pointer via une url en relatif pour que les tests passent chez tous les développeurs
		boolean isAFile = fileAccessor.isPathPointedOnFile("src/tests/PasDeFichier.txt");
		
		assertFalse(isAFile);
	}
	
	
	@Test
	public void getLinesReturnsGoodLines() 
	{
		SafeFileAccessor fileAccessor = new SafeFileAccessor();
		
		// TODO : faire pointer via une url en relatif pour que les tests passent chez tous les développeurs
		ArrayList<String> lines = fileAccessor.getLines("src/tests/fichierDeTest.txt");
		
		assertTrue("A".equals(lines.get(0)));
		assertTrue("B".equals(lines.get(1)));
		assertTrue("C".equals(lines.get(2)));
		assertTrue("D".equals(lines.get(3)));
		assertTrue(lines.size() == 4);
	}

}
