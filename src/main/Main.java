package main;

import GUI.Finestra;
import GUI.Visualizzatore;

public class Main{
	private static Partita partita;
	private static Finestra finestra;


	public static void main(String[] args) {		
		finestra= new Finestra();
		benvenuto();
		//finestra.start();
		//arrivederci();
	}

	private static void benvenuto() {
		Visualizzatore.benvenuto();
		
	}
	
	private static void arrivederci() {
		Visualizzatore.printArrivederci();
		
	}
	
	public static Partita getPartita(){
		return partita;
	}
			
		
}

