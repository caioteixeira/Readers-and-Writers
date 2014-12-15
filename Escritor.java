import java.util.Random;


public class Escritor extends Thread {
	BD bd;
	boolean LE;
	
	public Escritor(BD bd)
	{
		this(bd, false);
	}
	
	public Escritor(BD bd, boolean LE)
	{
		super("Escritor");
		this.LE = LE;
		this.bd = bd;
	}
	
	public void run()
	{
		Random r = new Random();
		if(this.LE) //Solução com Leitor Escritor
		{
			try{
				bd.comecaEscrita();
				for(int i = 0; i < 100; i++)
				{
					bd.write("MODIFICADO" ,r.nextInt(bd.tamanho));
				}
				sleep(1);
				bd.terminaEscrita();
				
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else
		{
			try {
				//Garante que apenas um leitor tenha acesso ao banco
				synchronized(bd){
					for(int i = 0; i < 100; i++)
					{
						bd.write("MODIFICADO" ,r.nextInt(bd.tamanho));
					}
					sleep(1);
				}
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
