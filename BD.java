import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class BD{
	ArrayList<String> palavras;
	public boolean temEscritor;
	public int leitoresAtivos;
	public int escritoresAtivos;
	
	
	public int tamanho;
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
				tamanho++;
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public String read(int index)
	{
		return palavras.get(index);
	}
	
	public void write(String newValue, int index)
	{
		palavras.set(index, newValue);
	}
	
	public void imprimeTudo()
	{
		for(String e : palavras)
		{
			System.out.println(e);
		}
	}
	
	//Métodos auxiliares para técnica de leitor/escritor
	public synchronized void comecaLeitura() throws InterruptedException
	{
		while(this.temEscritor) //Se tiver escritor espera
		{
			this.wait();
		}
		leitoresAtivos++;
	}
	public synchronized void terminaLeitura()
	{
		leitoresAtivos--;
		notify(); //Notifica que terminou leitura
	}
	
	public synchronized void comecaEscrita() throws InterruptedException
	{
		//Espera até poder escrever
		while(leitoresAtivos > 0 || temEscritor) 
		{
			wait();
		}
		temEscritor = true;
	}
	public synchronized void terminaEscrita()
	{
		temEscritor = false;
		notify();
	}
}