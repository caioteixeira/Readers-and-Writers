import java.util.Random;


public class Leitor extends Thread {
	BD bd;
	
	public Leitor(BD bd)
	{
		super("Leitor");
		this.bd = bd;
	}
	
	public void run()
	{
		Random r = new Random();
		try{
			synchronized(bd)
			{
				for(int i = 0; i < 100; i++)
				{
					bd.read(r.nextInt(bd.tamanho));
				}
				
				sleep(1);
			}
			EP.desativaThread();
			//System.out.println("FimLeitor");
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
