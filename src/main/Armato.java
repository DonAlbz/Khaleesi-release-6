/**
 * 
 */
package main;

import GUI.Visualizzatore;

/**
 * @author alberto
 *
 */
public class Armato  extends AbstractStato{

	public Armato(Partita partita) {
		super(partita);
	}



	/* (non-Javadoc)
	 * @see main.AbstractStato#eseguiAzione()
	 */
	@Override
	public void eseguiAzione() {

		if (!partita.isVuota(guerriero.getPosizione())){
			AbstractObject oggetto = partita.contenutoCasella(guerriero.getPosizione());
			//l'oggetto contenuto nella casella è Null quando la casella è vuota
			if(oggetto.getClass()==Forziere.class){
				trovatoForziere((Forziere)oggetto);
			}
			else if(oggetto.getClass()==Pozione.class){
				trovataPozione((Pozione)oggetto);
			}
			else if (oggetto.getClass()==Orco.class) {
				trovatoOrco((Orco) oggetto);
			}
			else if (oggetto.getClass()==Arma.class){
				trovataArma((Arma) oggetto);
			}
			else
				assert false;
		}
		else 
			trovatoVuoto();				

	}

	/**Permette di scegliere se lasciare l'arma sulla casella vuota trovata, in caso affermativo la lascia.
	 * @param arma abbandonabile
	 */
	private void scegliDrop(Arma arma) {
		//TODO da cambiare Visualizzatore.scegliDropArma();
		Visualizzatore.scegliDropArma();
	}



	@Override
	public void trovataArma(Arma arma) {
		Visualizzatore.trovataAltraArma();
	}



	@Override
	public void trovatoVuoto() {
		Visualizzatore.enableFreccie(false);
		Visualizzatore.enableYN(true);
		scegliDrop((guerriero.getArma()));
		settaPulsanti(null);				
	}



	@Override
	public void armaYN(Arma arma, Boolean scelta) {		
	}

	@Override
	public void vuotoYN(Boolean scelta) {
		if(scelta){
			partita.modificaCasella(guerriero.getPosizione(), guerriero.getArma());
			guerriero.dropArma();
			Visualizzatore.armaDepositata();
			rimuoviAscoltatori();
			partita.setStato(new Disarmato(partita));
		}				
	}

}
