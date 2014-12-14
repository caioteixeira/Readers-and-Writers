import java.lang.Thread;
import java.util.Random;

public class EP {
	
	public static void main(String[] args)
	{
		BD bd = new BD();
		Thread[] threads = new Thread[100];
		Random r = new Random();
		
		//Popula arranjo
		for(int i = 0; i < 100; i++)
		{
			//Leitor ou escritor?
			Thread t;
			if(r.nextBoolean())
			{
				t = new Escritor(bd);
			}
			else
			{
				t = new Leitor(bd);
			}
			
			//Escolhe posição da nova thread
			int index;
			do{
				index = r.nextInt(100);
			}while(threads[index] != null);
			
			//Adiciona thread no arranjo
			threads[index] = t;
		}
		
		//Roda as threds
		for(Thread t : threads)
		{
			t.run();
		}
	}
}


