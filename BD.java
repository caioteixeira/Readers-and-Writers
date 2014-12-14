import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class BD{
	ArrayList<String> palavras;
	public BD()
	{
		palavras = new ArrayList<String>();
		File bd = new File("bd.txt");
		try {
			//Lê arquivo
			Scanner sc = new Scanner(bd);
			while(sc.hasNext())
			{
				palavras.add(sc.nextLine());
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*for(String e : palavras)
		{
			System.out.println(e);
		}*/
	}
	
	synchronized public String read(int index)
	{
		return palavras.get(index);
	}
	
	synchronized public void write(int index, String newValue)
	{
		palavras.set(index, newValue);
	}
}