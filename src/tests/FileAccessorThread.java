package tests;

import java.util.ArrayList;

import abstraction.SafeFileAccessor;

public class FileAccessorThread implements Runnable 
{

	private int number;
	private int waitingTime;

	public FileAccessorThread(SafeFileAccessor fileAccessor, String path, int number, int waitingTime)
	{
		this.fileAccessor = fileAccessor;
		this.path = path;
		this.number = number;
		this.waitingTime = waitingTime;
	}
	
	private SafeFileAccessor fileAccessor;
	private String path;
	
	@Override
	public void run() 
	{
		try {
			Thread.sleep(waitingTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		ArrayList<String> lines = fileAccessor.getLines(path);
		lines.add(number + "");
		lines.add("---------------------------------------------------------------------------------");
		int i;
		for (i = 0; i < lines.size(); i++) 
		{
		   System.out.println(lines.get(i));
		}
		
	}

}
