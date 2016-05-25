/**
 * 
 */
package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.Visualizzatore;

/**
 * @author alberto
 *
 */
public class Disarmato extends AbstractStato{


	public Disarmato(Partita partita) {
		super(partita);
	}
	
	/* (non-Javadoc)
	 * @see main.AbstractStato#eseguiAzione()
	 */
	public void eseguiAzione() {
		AbstractObject oggetto = partita.contenutoCasella(guerriero.getPosizione()); //l'oggetto contenuto nella casella è Null quando la casella è vuota
		if (!partita.isVuota(guerriero.getPosizione())){			
			if (oggetto.getClass()==Arma.class){
				trovataArma(((Arma)oggetto));				
			}
			else if(oggetto.getClass()==Forziere.class){
				trovatoForziere((Forziere)oggetto);
			}
			else if(oggetto.getClass()==Pozione.class){
				trovataPozione((Pozione)oggetto);
			}
			else if (oggetto.getClass()==Orco.class) {
				trovatoOrco((Orco)oggetto);
			}
			else 
				assert false;
		}else
			trovatoVuoto();

	}	

	/**Fa scegliere se impugnare l'arma trovata, in caso affermativo la impugna
	 * @param arma impugnabile
	 */
	private void scegliRaccolta(Arma arma) {
	Visualizzatore.scegliRaccoltaArma(arma.getNomeOggetto());
	/*
		if(raccolta){
			guerriero.impugna(arma);
			partita.modificaCasella(guerriero.getPosizione(), null);
			partita.setStato(new Armato(partita));
		}*/
	}

	@Override
	public void trovataArma(Arma arma) {
		Visualizzatore.enableFreccie(false);
		Visualizzatore.enableYN(true);		
		scegliRaccolta(arma);
		settaPulsanti(arma);
	}

	@Override
	public void trovatoVuoto() {
	Visualizzatore.casellaVuotaDisarmato();
	}

	@Override
	public void armaYN(Arma arma, Boolean scelta) {
		if(scelta){
			guerriero.impugna(arma);
			partita.modificaCasella(guerriero.getPosizione(), null);
			Visualizzatore.armaImpugnata(arma);
			rimuoviAscoltatori();
			partita.setStato(new Armato(partita));
		}
	}

	@Override
	public void vuotoYN(Boolean scelta) {		
	}

}
