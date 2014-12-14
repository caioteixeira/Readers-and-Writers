import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Thread;
import java.util.Random;
import java.lang.System;
import java.util.ArrayList;

public class EP {
	
	static int activeThreads = 0; //Conta Threads ativas
	static int nTestes = 50; //nTestes para calcular media
	
	static String arqSaida = "saida.csv"; //Endereco do arquivo de saida
	
	public static void main(String[] args)
	{
		long[][] mediasDeTempo = new long[101][2]; //Guarda as medias
		
		for(int nLeitores = 0; nLeitores <= 100; nLeitores++)
		{
			//Roda e calcula media para execução simples
			long soma = 0; 
			for(int i = 0; i < nTestes; i++)
			{
				soma += teste(nLeitores, false);
			}
			mediasDeTempo[nLeitores][0] = soma/nTestes; //Calcula e guarda media
			
			//Roda e calcula media para execucao com leitor e escritor
			soma = 0;
			for(int i = 0; i < nTestes; i++)
			{
				soma += teste(nLeitores, true);
			}
			mediasDeTempo[nLeitores][1] = soma/nTestes; //Calcula e guarda media
			
			System.out.println("Terminou: " + nLeitores + "%");
		}
		
		//Imprime medias de Tempo em arquivo
		try {
			FileWriter writer = new FileWriter(arqSaida);
			writer.append("Num de Leitores;Media de Tempo(implementação comum);Media de Tempo(com Leitores e Escritores)\n");
			for(int nLeitores = 0; nLeitores <= 100; nLeitores++)
			{
				//System.out.println("nLeitores: "+nLeitores +"\t "+mediasDeTempo[nLeitores][0] + "\t " + mediasDeTempo[nLeitores][1]);
				writer.append(nLeitores + ";" + mediasDeTempo[nLeitores][0] + ";" + mediasDeTempo[nLeitores][1] + "\n");
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	//Implementação dos testes, retorna tempo de execucao em ms
	//Argumento nLeitores define quantos leitores
	//Argumento LE define se usa método de Leitores e Escritores
	public static long teste(int nLeitores, boolean LE)
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
				t = new Leitor(bd, LE);
				nLeitores--;
			}
			else
			{
				t = new Escritor(bd, LE);
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
			t.start();
		}		
		
		for(Thread t : threads) //Espera todas as threads terminarem
		{
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
		//bd.imprimeTudo();
		
		return System.currentTimeMillis() - tInicial;
	}
	
	//Conta Thread sendo desativada
	public static void desativaThread()
	{
		//System.out.println("OK");
		activeThreads--;
	}
}


