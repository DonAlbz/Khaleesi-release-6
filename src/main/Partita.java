package main;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import javax.swing.JButton;

import GUI.Finestra;
import GUI.Visualizzatore;
import inputDati.InputDati;
import inputDati.MyMenu;

public class Partita extends Observable {
	private Map<Point, AbstractObject> casella = new HashMap<>();
	private Guerriero guerriero;
	private Stato stato;
	private boolean continuare = true;
	private String RICHIESTA_SPOSTAMENTO = "Seleziona una direzione di movimento del guerriero";
	private String[] SPOSTAMENTI_POSSIBILI={"NORD", "SUD", "OVEST", "EST", "TORNA IN CITTA'"};
	private MyMenu menuSpostamento = new MyMenu(RICHIESTA_SPOSTAMENTO, SPOSTAMENTI_POSSIBILI);
	private String ERRORE_SPOSTAMENTO = "Errore: il guerriero non è stato spostato nella direzione indicata";
	
	
	public Partita() {
		creaCasella(new Point(0,0), null); //inizializza la casella iniziale vuota
		scegliCategoria();	
		stato= new Disarmato(this);
	}
	
	public Partita(String nomeGuerriero, int categoria ) {
		creaCasella(new Point(0,0), null); //inizializza la casella iniziale vuota
		scegliCategoria(categoria, nomeGuerriero);	
		stato= new Disarmato(this);
	}
	
	
	private void scegliCategoria() {
		switch(Visualizzatore.scegliCategoria()){
			case 0:
				scegliCategoria();
				break;
			case 1:
				guerriero= new Paladino();
				break;
			case 2:
				guerriero= new Ladro();
				break;
			case 3:
				guerriero = new Barbaro();
				break;
			default:
				assert false;
				break;
		}		
	}

	private void scegliCategoria(int categoria, String nomeGuerriero) {
		switch(categoria){
			case 0:
				scegliCategoria();
				break;
			case 1:
				guerriero= new Paladino(nomeGuerriero);
				break;
			case 2:
				guerriero= new Ladro(nomeGuerriero);
				break;
			case 3:
				guerriero = new Barbaro(nomeGuerriero);
				break;
			default:
				assert false;
				break;
		}		
	}
	
	public void start() {
		richiediSpostamento();		
		/*while (continuare){	
			stato.eseguiAzione();
			this.setChanged();
			this.notifyObservers(this);
			this.clearChanged();
			if(continuare)
				richiediSpostamento();
			this.setChanged();
			this.notifyObservers(this);
			this.clearChanged();
		}*/
		//Visualizzatore.getFinestra().arrivederci();
	}

	public void eseguiPasso(int i){
		this.clearChanged();
		spostamentoRichiesto(i);
		this.setChanged();
		this.notifyObservers(this);		
		stato.eseguiAzione();
		this.setChanged();	
		//this.notifyObservers(this);	
		//richiediSpostamento();
	}
	/**Permette all'utente di decidere la direzione di spostamento del {@link #guerriero}
	 * 
	 */
	private void richiediSpostamento() {
		Visualizzatore.richiediSpostamento();
	}
	
	public void spostamentoRichiesto(int spostamento){
		assert guerriero.getPosizione()!=null;
		Point posizioneVecchia= new Point(guerriero.getPosizione());
		switch(spostamento){
		case Visualizzatore.ESCI_INT: 
			continuare=false;
			break;
		case Visualizzatore.NORD_INT:
			eseguiSpostamento(0, 1);
			assert posizioneVecchia.getY()+1==guerriero.getPosizione().getY() : ERRORE_SPOSTAMENTO ;
			break;
		case Visualizzatore.SUD_INT:
			eseguiSpostamento(0, -1);
			assert posizioneVecchia.getY()-1==guerriero.getPosizione().getY() : ERRORE_SPOSTAMENTO ;
			break;
		case Visualizzatore.OVEST_INT:
			eseguiSpostamento(-1, 0);
			assert posizioneVecchia.getX()-1==guerriero.getPosizione().getX() : ERRORE_SPOSTAMENTO ;
			break;
		case Visualizzatore.EST_INT:
			eseguiSpostamento(1, 0);
			assert posizioneVecchia.getX()+1==guerriero.getPosizione().getX() : ERRORE_SPOSTAMENTO ;
			break;
		case Visualizzatore.CITTA_INT:
			guerriero.getPosizione().setLocation(0, 0);
			assert (guerriero.getPosizione().getX() == 0 && guerriero.getPosizione().getY()==0) : ERRORE_SPOSTAMENTO ;
			break;
		default:
			assert false;
		}
	}
	
	/**Permette di effettuare uno spostamento differenziale dalla posizione in cui si trova il guerriero
	 * @param x spostamento intero dx
	 * @param y spostamento intero dy
	 */
	private void eseguiSpostamento(int x, int y) {
		guerriero.getPosizione().translate(x, y);
		if(!esisteCasella(guerriero.getPosizione())){
			Point newPosizione = new Point();
			newPosizione.setLocation(guerriero.getPosizione());
			creaCasella(newPosizione,ObjectFactory.creaContenutoCasella());
		}		
	}

	/**Serve sia a creare una casella, sia a modificare il contenuto di una casella gia' presente
	 * @param coordinate
	 * @param abstractObject
	 */
	private void creaCasella(Point coordinate, AbstractObject abstractObject){
		casella.put(coordinate, abstractObject);	
	}
	
	/**Ridondante, ma il metodo ha un nome piu' comprensibile dall'esterno
	 * @param coordinate
	 * @param abstractObject
	 */
	public void modificaCasella(Point coordinate, AbstractObject abstractObject){
		creaCasella(coordinate, abstractObject);
	}

	private Boolean esisteCasella(Point coordinate){
		return casella.containsKey(coordinate);
	}

	public Boolean isVuota(Point coordinate){
		return casella.get(coordinate)==null;
	}
	
	public AbstractObject contenutoCasella(Point coordinate){
		return casella.get(coordinate);
	}
	
	public String contenutoCasellaToString(Point coordinate){
		if(contenutoCasella(coordinate)!=null)
			return contenutoCasella(coordinate).getNomeOggetto();
		else
			return Visualizzatore.VUOTO;		
	}

	public void setStato(Stato stato){
		this.stato=stato;
	}
	/**
	 * @return the guerriero
	 */
	public Guerriero getGuerriero() {
		return guerriero;
	}

	/**
	 * @param continuare the continuare to set
	 */
	public void setContinuare(boolean continuare) {
		this.continuare = continuare;
	}


	/**
	 * @return the stato
	 */
	public Stato getStato() {
		return stato;
	}
	
	
	
}