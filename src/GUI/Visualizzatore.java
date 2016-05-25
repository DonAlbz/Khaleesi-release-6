package GUI;

import javax.management.NotificationEmitter;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import inputDati.InputDati;
import inputDati.MyMenu;
import main.Arma;
import main.Partita;

public final class Visualizzatore {
	public static Finestra finestra;

	public static final int ESCI_INT = 0;
	public static final int NORD_INT = 1;
	public static final int SUD_INT = 2;
	public static final int OVEST_INT = 3;
	public static final int EST_INT = 4;
	public static final int CITTA_INT = 5;

	private static final String INSERISCI_NOME_CATEGORIA = "Inserisci il nome e la categoria del tuo guerriero";
	private final static String RICHIEDI_SPOSTAMENTO="Selezione la direzione di movimento del guerriero";
	private static final String ARRIVEDERCI = "Grazie per aver giocato a The Shandon Adventure!";
	public static final String BENVENUTO = "Benvenuto in The Shandon Adventure!";
	public final static String VUOTO="Vuoto";
	public final static String SPADA="Spada";
	public final static String ARCO="Arco";
	public final static String PUGNALE="Pugnale";
	public final static String POZIONE="Pozione";
	public final static String FORZIERE="Forziere";
	public static final String ORCO = "Orco";
	public static final String MONETE = "Monete";
	public static final String NESSUNA = "Nessuna";

	public static final int VUOTO_INT=0;
	public static final int ARMA_INT=1;
	public static final int POZIONE_INT=2;
	public static final int FORZIERE_INT=3;
	public static final int ORCO_INT=4;
	final static String BUTTON_NOME_CATEGORIA_FINITO="Avanti";
	private final static String OGGETTO_TROVATO="Hai trovato l'oggetto:\n";
	private static final String ORCO_TROVATO="Hai trovato un Orco!";
	private final static String RACCOGLIERE="Lo vuoi raccogliere?";
	private final static String APRIRE="Lo vuoi aprire?";
	private static final String USARE_POZIONE = "Vuoi usarla?";
	private final static String RICHIESTA_NOME_GUERRIERO = "Inserisci il nome del tuo guerriero: ";
	public final static String FORZIERE_VUOTO = "Il forziere e' vuoto.";
	public static final String FORZIERE_TROVATE_MONETE1 = "Nel forziere hai trovato ";
	public static final String FORZIERE_TROVATE_MONETE2 = " monete e te ne sei impossessato.";
	public static final String FORZIERE_TROVATO_VELENO = "Nel forziere hai trovato del veleno.";
	public static final String DANNI_SUBITI1 = "Hai subito ";
	public static final String DANNI_SUBITI2 = " danni.";
	private static final String CASELLA_VUOTA_TROVATA = "Hai raggiunto una casella vuota.";
	private static final String ALTRA_ARMA_TROVATA = "Hai trovato un'altra arma.";
	private static final String DROPPARE = "Vuoi lasciare la tua arma su questa casella?";
	private static final String GUERRIERO_CURATO=" e' stato curato di ";	
	private static final String REPORT_HP="Ora la sua salute ammonta a ";	
	private static final String PUNTI_VITA = " punti vita.";
	private static final String COMBATTI_ORCO = "Vuoi combattere? ";
	private static final String REPORT_COMBATTIMENTO = " nel combattimento ha subito ";
	private static final String ARMA_DEPOSITATA = "Arma depositata.";
	private static final String MORTO = "Sfortunatamente il tuo guerriero e' morto.";
	private static final String FUGA_RIUSCITA = "La fuga e' riuscita con successo";
	private static final String FUGA_NON_RIUSCITA = "La fuga non e' riuscita";
	private static final String SCEGLI_CATEGORIA = "Scegli la categoria del tuo guerriero.";
	private static final String[] CATEGORIE = {"Paladino", "Ladro", "Barbaro"};
	private static final String ARMA_IMPUGNATA = "Arma impugnata.";	
	private static final String HTML_INIZIO="<html><body style= 'text-align: center'>";
	private static final String HTML_FINE="</html>";
	private static final String A_CAPO="<p>";
	public static final String NORD = "Nord";
	public static final String SUD = "Sud";
	public static final String OVEST = "Ovest";
	public static final String EST = "Est";
	public static final String YES = "SI";
	public static final String NO = "NO";
	private static final String PUNTO = ".";

	

	
	





	/**
	 * @param finestra the finestra to set
	 */
	public static void setFinestra(Finestra _finestra) {
		finestra=_finestra;
	}

	private static String oggettoTrovato(String nomeOggetto){
		StringBuffer s= new StringBuffer();
		s.append(OGGETTO_TROVATO);
		s.append(nomeOggetto);
		s.append(PUNTO);
		s.append( A_CAPO);
		return s.toString();
	}

	public static void scegliRaccoltaArma(String nomeOggetto){
		StringBuffer s= new StringBuffer();
		s.append(oggettoTrovato(nomeOggetto));
		s.append(RACCOGLIERE);		
		finestra.getConsole().stampa(incapsulaHtml(s.toString()));				
	}

	public static void scegliDropArma() {
		StringBuffer s = new StringBuffer();
		s.append(CASELLA_VUOTA_TROVATA);
		s.append(A_CAPO);
		s.append(DROPPARE);
		finestra.getConsole().stampa(incapsulaHtml(s.toString()));		
	}

	public static void scegliAperturaForziere(String nomeOggetto){
		StringBuffer s= new StringBuffer();
		s.append(oggettoTrovato(nomeOggetto));
		s.append(APRIRE);		
		finestra.getConsole().stampa(incapsulaHtml(s.toString()));						
	}

	public static void scegliUtilizzoPozione(String nomeOggetto) {
		StringBuffer s= new StringBuffer();
		s.append(oggettoTrovato(nomeOggetto));
		s.append(USARE_POZIONE);		
		finestra.getConsole().stampa(incapsulaHtml(s.toString()));	
	}


	public static String richiestaNomeGuerriero() {
		return InputDati.leggiStringaNonVuota(RICHIESTA_NOME_GUERRIERO);
	}

	public static void notificaCura(String nome, int totaleCura, double puntiVita) {
		StringBuffer s = new StringBuffer();
		s.append(nome);
		s.append(GUERRIERO_CURATO);
		s.append(totaleCura);
		s.append(PUNTI_VITA);
		s.append(A_CAPO);
		s.append(REPORT_HP);
		s.append(puntiVita);
		s.append(PUNTI_VITA);
		
		finestra.getConsole().stampa(incapsulaHtml(s.toString()));
	}

	public static void scegliCombattiOrco(String nomeOggetto) {
		StringBuffer s= new StringBuffer();
		s.append(oggettoTrovato(nomeOggetto));
		s.append(COMBATTI_ORCO);		
		finestra.getConsole().stampa(incapsulaHtml(s.toString()));			
	}

	public static void reportCombattimento(String nomeGuerriero, double danni){
		StringBuffer s = new StringBuffer();
		s.append(nomeGuerriero);
		s.append(REPORT_COMBATTIMENTO);
		s.append(String.format("%.2f", danni));
		s.append(DANNI_SUBITI2);
		finestra.getConsole().stampa(incapsulaHtml(s.toString()));	
	}

	public static void reportPuntiVita(double danni) {
		System.out.printf(PUNTI_VITA, danni);
	}

	public static void morto() {
		System.out.println(MORTO);			
	}

	public static void benvenuto() {
		StringBuffer s = new StringBuffer();
		s.append(BENVENUTO);
		s.append(A_CAPO);
		s.append(A_CAPO);
		s.append(A_CAPO);
		s.append(A_CAPO);
		s.append(INSERISCI_NOME_CATEGORIA);
		finestra.getConsole().stampa(incapsulaHtml(s.toString()));		
	}

	public static void printArrivederci() {
		System.out.println(ARRIVEDERCI);

	}


	public static void fugaRiuscita(boolean b) {
		if (b)
			finestra.getConsole().stampa(incapsulaHtml(FUGA_RIUSCITA));
		else
			finestra.getConsole().stampa(incapsulaHtml(FUGA_NON_RIUSCITA));
	}

	public static int scegliCategoria() {
		MyMenu menuCategoria= new MyMenu(SCEGLI_CATEGORIA, CATEGORIE);
		return menuCategoria.scegli();

	}



	public static void richiediSpostamento() {
		finestra.getConsole().stampa(incapsulaHtml(RICHIEDI_SPOSTAMENTO));		
	}

	public static String incapsulaHtml(String s){
		return HTML_INIZIO + s + HTML_FINE;
	}

	public static void enableFreccie(Boolean b){
		finestra.getConsole().enableFreccie(b);
	}

	public static void enableYN(Boolean b){
		finestra.getConsole().enableYN(b);
	}

	public static JButton getYesButton(){
		return finestra.getConsole().getYesButton();
	}

	public static JButton getNoButton(){
		return finestra.getConsole().getNoButton();
	}

	public static void forziereVuoto() {
		finestra.getConsole().stampa(incapsulaHtml(FORZIERE_VUOTO));		
	}

	public static void trovateMonete(int monete) {
		StringBuffer s= new StringBuffer();
		s.append(FORZIERE_TROVATE_MONETE1);
		s.append(monete);
		s.append(FORZIERE_TROVATE_MONETE2);
		finestra.getConsole().stampa(incapsulaHtml(s.toString()));

	}

	private static String danniSubiti(double danni){
		StringBuffer s = new StringBuffer();
		s.append(DANNI_SUBITI1);
		s.append(danni);
		s.append(DANNI_SUBITI2);
		return s.toString();
	}

	public static void trovatoVeleno(double veleno) {
		StringBuffer s= new StringBuffer();
		s.append(FORZIERE_TROVATO_VELENO);
		s.append(A_CAPO);
		s.append(danniSubiti(veleno));
		finestra.getConsole().stampa(incapsulaHtml(s.toString()));
	}

	public static void armaImpugnata(Arma arma) {
		finestra.getConsole().stampa(incapsulaHtml(ARMA_IMPUGNATA));		
	}

	public static String append(String text, String stringa) {
		StringBuffer s = new StringBuffer();
		s.append(text.replaceAll(HTML_FINE, ""));
		s.append(A_CAPO);
		s.append(A_CAPO);
		s.append(A_CAPO);
		s.append(stringa);
		s.append(HTML_FINE);
		return s.toString();		
	}

	public static void appendRichiediSpostamento() {
		finestra.getConsole().stampaAppend(RICHIEDI_SPOSTAMENTO);	
	}

	public static void armaDepositata() {
		finestra.getConsole().stampa(incapsulaHtml(ARMA_DEPOSITATA));		
		
	}

	public static void casellaVuotaDisarmato() {
		finestra.getConsole().stampa(incapsulaHtml(CASELLA_VUOTA_TROVATA));		
		appendRichiediSpostamento();
	}

	public static void trovataAltraArma() {
		finestra.getConsole().stampa(incapsulaHtml(ALTRA_ARMA_TROVATA));		
		appendRichiediSpostamento();
	}


}
