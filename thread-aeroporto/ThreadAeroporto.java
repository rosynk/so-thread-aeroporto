package controller;

import java.util.concurrent.Semaphore;

public class ThreadAeroporto extends Thread {
	
	private Semaphore areaDecolagem;
	private int idAviao;
	private Semaphore pistaDecolagemNorte;
	private Semaphore pistaDecolagemSul;
	
	
	public ThreadAeroporto(Semaphore areaDecolagem, Semaphore pistaDecolagemNorte, Semaphore pistaDecolagemSul, int idAviao) {
		// TODO Auto-generated constructor stub
		
		this.areaDecolagem = areaDecolagem;
		this.idAviao = idAviao;
		this.pistaDecolagemNorte = pistaDecolagemNorte;
		this.pistaDecolagemSul = pistaDecolagemSul;
	}
	
	
	public void run () {
		try {
			areaDecolagem.acquire();
			int random = (int) ((Math.random() * 2));
			if (random == 0) {
				decolagemNorte();
			} else {
				decolagemSul();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			areaDecolagem.release();
		}
	}
	
	private void decolagemSul () throws InterruptedException {
		pistaDecolagemNorte.acquire();
		
		System.out.println("#" + idAviao + " entrou na pista NORTE");
		
		decolar();
		
		pistaDecolagemNorte.release();
		
		System.out.println("#" + idAviao + " saiu da pista NORTE");
	}
	
	private void decolagemNorte() throws InterruptedException {
		pistaDecolagemSul.acquire();
		
		System.out.println("#" + idAviao + " entrou na pista SUL");
		
		decolar();
		
		pistaDecolagemSul.release();
		
		System.out.println("#" + idAviao + " saiu na pista SUL");
	}
	
	private void manobrar() throws InterruptedException {
		System.out.println("#" + idAviao + " entrou na fase de manobra!");
		
		int tempo = (int)((Math.random() * 401) + 300);
		sleep(tempo);
		
		System.out.println("#" + idAviao + " saiu da fase de manobra! >> levou " + tempo + " ms");
	}
	
	private void taxiar() throws InterruptedException {
		System.out.println("#" + idAviao + " entrou na fase de taxiação!");
		
		int tempo = (int)((Math.random() * 501) + 500);
		sleep(tempo);
		
		System.out.println("#" + idAviao + " saiu da fase de taxiação! >> levou " + tempo + " ms");
	}
	
	private  void decolagem() throws InterruptedException {
		System.out.println("#" + idAviao + " entrou na fase de decolagem!");
		
		int tempo = (int)((Math.random() * 201) + 600);
		sleep(tempo);
		
		System.out.println("#" + idAviao + " saiu da fase de decolagem! >> levou " + tempo + " ms");

	}

	private  void afastamento() throws InterruptedException {
		System.out.println("#" + idAviao + " entrou na fase de afastamento!");
		
		int tempo = (int)((Math.random() * 501) + 300);
		sleep(tempo);
		
		System.out.println("#" + idAviao + " saiu da fase de afastamento! >> levou " + tempo + " ms");

	}
	
	private  void decolar () throws InterruptedException {
		manobrar();
		taxiar();
		decolagem();
		afastamento();
	}
}
