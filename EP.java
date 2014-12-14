import java.lang.Thread;
import java.util.Random;
import java.lang.System;
import java.util.ArrayList;

public class EP {
	
	static int activeThreads = 0; //Conta Threads ativas
	static int nTestes = 5; //nTestes para calcular media
	
	public static void main(String[] args)
	{
		long[][] mediasDeTempo = new long[101][2]; //Guarda as medias
		
		for(int nLeitores = 0; nLeitores <= 100; nLeitores++)
		{
			//Roda e calcula media para execução simples
			long soma = 0; 
			for(int i = 0; i < nTestes; i++)
			{
				soma += comum(nLeitores);
			}
			mediasDeTempo[nLeitores][0] = soma/nTestes; //Calcula e guarda media
			System.out.println("Terminou " + nLeitores);
		}
		
		//Imprime medias de Tempo
		for(int nLeitores = 0; nLeitores <= 100; nLeitores++)
		{
			System.out.println("nLeitores: "+nLeitores +"\t "+mediasDeTempo[nLeitores][0]);
		}
		
	}
	
	//Implementação simples, retorna o tempo em ms para execução
	//Argumento nLeitores define quantos leitores
	public static long comum(int nLeitores)
	{
		BD bd = new BD();
		Random r = new Random();
		
		//Inicia arranjo de posicoes vazias
		ArrayList<Integer> posicoesVazias = new ArrayList<Integer>();
		for(int i = 0; i < 100; i++)
		{
			posicoesVazias.add(i);
		}
		
		//Popula arranjo de Threads
		Thread[] threads = new Thread[100];
		for(int i = 0; i < 100; i++)
		{
			//Leitor ou escritor?
			Thread t;
			if(nLeitores > 0)
			{
				t = new Leitor(bd);
				nLeitores--;
			}
			else
			{
				t = new Escritor(bd);
			}
			
			//Escolhe posição da nova thread
			//Pega aleatoriamente uma posicao vazia
			int pos = r.nextInt(posicoesVazias.size());
			int index = posicoesVazias.get(pos);
			posicoesVazias.remove(pos);
			
			//Adiciona thread no arranjo
			threads[index] = t;
			activeThreads++;
		}
		//Inicia contagem de tempo
		long tInicial = System.currentTimeMillis();
		
		
		//Roda as threds
		for(Thread t : threads)
		{
			t.run();
		}
		
		
		while(activeThreads > 0) {}; //Espera todas as Threads terminarem
		//bd.imprimeTudo();
		
		return System.currentTimeMillis() - tInicial;
	}
	
	//Implementação com solução de Readers and Writers
	
	public static float readersAndWriters()
	{
		
		return 0.0f;
	}
	
	//Conta Thread sendo desativada
	public static void desativaThread()
	{
		activeThreads--;
	}
}


