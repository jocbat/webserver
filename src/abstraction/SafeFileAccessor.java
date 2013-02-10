package abstraction;

import java.io.File;
import java.util.ArrayList;

/**
 * Permet de faire des opérations sur des fichier de manière "safe" en multithread
 * @author JOCELYN
 *
 */
public class SafeFileAccessor 
{
	/**
	 * url est-il le chemin d'un fichier de l'arborescence ?
	 * @param url
	 * @return
	 */
	public boolean isPathPointedOnFile(String url)
	{
		return true;
	}
	
	public ArrayList<String> getLines(File file)
	{
		return null;
	}
	
	/**
	 * Ecrire une ligne dans le fichier File
	 */
	public void writeLine()
	{
		
	}
}
