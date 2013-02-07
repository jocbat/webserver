package tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class SomeTries {

	@Test
	public void separerEnTroisLaPremiereLigneRequete() 
	{
		String premierLigne = "GET /toto.html HTTP/1.1";
		
		ArrayList<String> resultatsAttendu = new ArrayList<String>();
		resultatsAttendu.add("GET");
		resultatsAttendu.add("/toto.html");
		resultatsAttendu.add("HTTP/1.1");
		
		System.out.println(separerPremiereChaine(premierLigne).indexOf(0));
		
		ArrayList<String> listeSeparee = separerPremiereChaine(premierLigne);
		
		assertEquals(listeSeparee.get(0), resultatsAttendu.get(0));
		assertEquals(listeSeparee.get(1), resultatsAttendu.get(1));
		assertEquals(listeSeparee.get(2), resultatsAttendu.get(2));
	}
	
	@Test
	public void recupererFluxFichier() 
	{
		BufferedReader br = null;
		boolean isExist = false;
		try {
			
			String sCurrentLine;
 
			br = new BufferedReader(new FileReader("D:/toto.txt"));
			
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
				// on a bien une ligne, l'adresse pointe bien vers le fichier mockrequest
				isExist = true;
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		assertTrue(isExist);
	}
	
	
	@Test
	public void isExistingFile() 
	{
		File file = new File("C:/hiberfil.sys");
		System.out.println(file.isFile());
	}
	
	
	private ArrayList<String> separerPremiereChaine(String chaine)
	{
		ArrayList<String> retour = new ArrayList<>();
		int positionPremierBlanc = chaine.indexOf(" ");
		int positionDeuxiemeBlanc = chaine.lastIndexOf(" ");
		
		String methode = chaine.substring(0,positionPremierBlanc);
		System.out.println(methode);
		
		String url = chaine.substring(positionPremierBlanc + 1 , positionDeuxiemeBlanc);
		System.out.println(url);
		
		String version = chaine.substring(positionDeuxiemeBlanc + 1);
		System.out.println(version);
		
		
		retour.add(methode);
		retour.add(url);
		retour.add(version);
		return retour;
	}
	
	
	
	

}
