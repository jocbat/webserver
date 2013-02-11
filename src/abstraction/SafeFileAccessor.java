package abstraction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Permet de faire des op�rations sur des fichier de mani�re "safe" en multithread
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
	public synchronized boolean isPathPointedOnFile(String url)
	{
		File file = new File(url);
		return file.isFile();
	}
	
	public synchronized ArrayList<String> getLines(String url)
	{
		ArrayList<String> returnedList = new ArrayList<>();
		BufferedReader br = null;
		boolean isExist = false;
		try {
			
			String sCurrentLine;
 
			br = new BufferedReader(new FileReader(url));
			
			while ((sCurrentLine = br.readLine()) != null) 
			{
				returnedList.add(sCurrentLine);
				//System.out.println(sCurrentLine);
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
		return returnedList;
	}
	
	/**
	 * Ecrire une ligne dans le fichier File
	 */
	public synchronized void writeLine()
	{
		// TODO : �crire m�thode writeLine
	}
}
