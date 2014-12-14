import java.util.Random;


public class Escritor extends Thread {
	BD bd;
	
	public Escritor(BD bd)
	{
		super("Escritor");
		this.bd = bd;
	}
	
	public void run()
	{
		
		Random r = new Random();
		try {
			synchronized(bd){
				for(int i = 0; i < 100; i++)
				{
					bd.write("MODIFICADO" ,r.nextInt(bd.tamanho));
				}
				sleep(1);
			}
			//System.out.println("FimEscritor");
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
