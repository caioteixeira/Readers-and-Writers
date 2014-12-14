import java.util.Random;


public class Leitor extends Thread {
	BD bd;
	boolean LE;
	
	public Leitor(BD bd)
	{
		this(bd, false);
	}
	
	public Leitor(BD bd, boolean LE)
	{
		super("Leitor");
		this.LE = LE;
		this.bd = bd;
	}
	
	public void run()
	{
		Random r = new Random();
		if(LE)
		{
			try {
				bd.comecaLeitura();
				
				for(int i = 0; i < 100; i++)
				{
					bd.read(r.nextInt(bd.tamanho));
				}
				sleep(1);
				
				bd.terminaLeitura();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
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
}
