package main;

import GUI.Ascoltatore;
import GUI.Visualizzatore;
import utility.EstrazioniCasuali;

public abstract class AbstractStato implements Stato {
	Partita partita;
	Guerriero guerriero;
	Ascoltatore ascoltatore;


	public AbstractStato (Partita partita) {
		this.partita=partita;
		this.guerriero=partita.getGuerriero();
	}



	@Override
	public abstract void eseguiAzione();

	public abstract void trovataArma(Arma arma);

	public abstract void trovatoVuoto();

	public abstract void armaYN(Arma arma, Boolean scelta);

	public abstract void vuotoYN(Boolean scelta);
	

	/**Chiede se utilizzare la pozione trovata, in caso affermativo la utilizza
	 * @param pozione
	 */
	public void trovataPozione(Pozione pozione){
		assert pozione!=null;
		Visualizzatore.enableFreccie(false);
		Visualizzatore.enableYN(true);
		scegliUtilizzoPozione(pozione);
		settaPulsanti(pozione);
	}

	private void scegliUtilizzoPozione(Pozione pozione) {
		Visualizzatore.scegliUtilizzoPozione(pozione.getNomeOggetto());		
	}



	public void pozioneYN(Pozione pozione,Boolean scelta){
		if(scelta)
			usaPozione(pozione);
	}

	public void trovatoOrco(Orco orco) {
		assert orco!=null;
		Visualizzatore.enableFreccie(false);
		Visualizzatore.enableYN(true);
		scegliCombattiOrco(orco);
		settaPulsanti(orco);/*
		Boolean scelta= Visualizzatore.scegliCombattiOrco();
		if (scelta)
			combattiOrco(orco);
		else
			fugaOrco(orco);*/
	}

	private void scegliCombattiOrco(Orco orco) {
		Visualizzatore.scegliCombattiOrco(orco.getNomeOggetto());		
	}



	public void orcoYN(Orco orco,Boolean scelta){
		if (scelta)
			combattiOrco(orco);
		else
			fugaOrco(orco);
	}



	public void combattiOrco(Orco orco) {
		guerriero.combatti();
		Visualizzatore.reportCombattimento(guerriero.getNome(), guerriero.danniSubiti());
		if(guerriero.isVivo()){
			guerriero.aumentaExp(orco.getExp());
			Visualizzatore.reportPuntiVita(guerriero.getPuntiVita());
			partita.modificaCasella(guerriero.getPosizione(), null);			
		}
		else
			partita.setStato(new Morto(partita));			
	}


	private void fugaOrco (Orco orco) {
		double casuale=estraiPobalitaFuga();
		if(casuale>=Parametri.probFuga)
			Visualizzatore.fugaRiuscita(true);
		else{
			Visualizzatore.fugaRiuscita(false);
			combattiOrco(orco);
		}

	}

	private double estraiPobalitaFuga() {
		Double prob = EstrazioniCasuali.estraiDouble(0, 1);
		prob*=guerriero.getVelocita();
		if (prob > 1)
			prob=1.0;
		return prob;
	}

	/**Applica la cura a {@link #guerriero}
	 * @param pozione
	 */
	private void usaPozione(Pozione pozione) {
		guerriero.riceviCura(pozione.getTotaleCura());
		partita.modificaCasella(guerriero.getPosizione(), null); // Elimina la pozione dalla casella
		Visualizzatore.notificaCura(guerriero.getNome(),pozione.getTotaleCura(), guerriero.getPuntiVita());
	}

	/**Chiede se aprire il forziere trovato, in caso affermativo lo apre
	 * @param forziere
	 */
	public void trovatoForziere(Forziere forziere){
		assert forziere!=null;
		Visualizzatore.enableFreccie(false);
		Visualizzatore.enableYN(true);
		scegliAperturaForziere(forziere);
		settaPulsanti(forziere);
		//Boolean scelta= Visualizzatore.scegliAperturaForziere(forziere.getNomeOggetto());
		/*
		if(scelta){
			apriForziere(forziere);
			assert forziere.getMonete()==0 && forziere.getVeleno()==0; //una volta aperto il forziere, esso deve essere vuoto
		}
		 */
	}

	private void scegliAperturaForziere(Forziere forziere) {
		Visualizzatore.scegliAperturaForziere(forziere.getNomeOggetto());		
	}



	public void forziereYN(Forziere forziere, Boolean scelta){
		if(scelta){
			apriForziere(forziere);
			assert forziere.getMonete()==0 && forziere.getVeleno()==0; //una volta aperto il forziere, esso deve essere vuoto
		}
	}


	/**Funzione che apre il forziere, ne scopre il contenuto e applica le conseguenze dell'apertura a {@link #guerriero}
	 * @param forziere aperto
	 */
	private void apriForziere(Forziere forziere) {
		assert forziere!=null;
		if(forziere.isVuoto()){
			Visualizzatore.forziereVuoto();
		}
		if(forziere.getMonete()!=0){
			int monete=forziere.getMonete();
			guerriero.aumentaMonete(monete);
			forziere.setMonete(0);
			Visualizzatore.trovateMonete(monete);
		}
		if(forziere.getVeleno()!=0){
			int veleno=forziere.getVeleno();
			guerriero.subisciDanni((double)veleno);
			forziere.setVeleno(0);
			Visualizzatore.trovatoVeleno((double)veleno);
			if(!guerriero.isVivo()){
				rimuoviAscoltatori();
				partita.setStato(new Morto(partita));
			}
				
		}
	}

	protected void rimuoviAscoltatori() {
			Visualizzatore.getYesButton().removeActionListener(ascoltatore);
			Visualizzatore.getNoButton().removeActionListener(ascoltatore);
		}
	
	
	protected void settaPulsanti(AbstractObject abstractObject) {
		if(ascoltatore!=null){
			Visualizzatore.getYesButton().removeActionListener(ascoltatore);
			Visualizzatore.getNoButton().removeActionListener(ascoltatore);
		}
		ascoltatore=new Ascoltatore(this, abstractObject);
		Visualizzatore.getYesButton().addActionListener(ascoltatore);
		Visualizzatore.getNoButton().addActionListener(ascoltatore);
	}

	public void oggettoYN(AbstractObject abstractObject, Boolean scelta){
		if(abstractObject!=null){
		if (abstractObject.getClass()==Arma.class)
			armaYN((Arma)abstractObject, scelta);
		else if (abstractObject.getClass()==Pozione.class)
			pozioneYN((Pozione)abstractObject, scelta);
		else if (abstractObject.getClass()==Forziere.class)
			forziereYN((Forziere)abstractObject, scelta);
		else if (abstractObject.getClass()==Orco.class)
			orcoYN((Orco)abstractObject, scelta);
		}
		else
			vuotoYN(scelta);
		Visualizzatore.appendRichiediSpostamento();
		notifica();		
		Visualizzatore.enableYN(false);
		Visualizzatore.enableFreccie(true);
	}
	
	public void notifica() {
		partita.notifyObservers(partita);
	}
}
