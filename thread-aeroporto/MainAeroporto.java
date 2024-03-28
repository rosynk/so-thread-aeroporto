package view;

import java.util.concurrent.Semaphore;

import controller.ThreadAeroporto;

public class MainAeroporto {
	
	public static void main(String[] args) {
		
		Semaphore pistaDecolagemSul = new Semaphore(1);
		Semaphore pistaDecolagemNorte = new Semaphore(1);
		Semaphore areaDecolagem = new Semaphore(2);
		
		for (int idAviao = 0; idAviao < 12; idAviao++) {	
			Thread threadAeroporto = new ThreadAeroporto(areaDecolagem, pistaDecolagemNorte, pistaDecolagemSul, idAviao);
			threadAeroporto.start();
		}
	}	
}
