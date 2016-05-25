/**
 * 
 */
package main;

import GUI.Visualizzatore;

/**
 * @author alber
 *
 */
public class Morto extends AbstractStato {

	/**
	 * @param nomeOggetto
	 */
	public Morto(Partita partita) {
		super(partita);
		eseguiAzione();
	}

	@Override
	public void eseguiAzione() {
		Visualizzatore.morto();
		partita.setContinuare(false);		
	}

	@Override
	public void trovataArma(Arma arma) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void trovatoVuoto() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void armaYN(Arma arma, Boolean scelta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vuotoYN(Boolean scelta) {
		// TODO Auto-generated method stub
		
	}

}
