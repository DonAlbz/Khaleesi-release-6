package GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Partita;

public class SchedaGioco extends JPanel implements Observer {
	private JLabel messaggioIniziale;
	private JLabel nomeLabel;
	private JLabel nomeInseritoLabel;
	private JLabel categoriaDesLabel;
	private JLabel categoriaLabel;
	private JLabel expDesLabel;
	private JLabel expLabel;
	private JLabel hpDesLabel;
	private JLabel hpLabel;
	private JLabel armaDesLabel;
	private JLabel armaLabel;
	private JLabel moneteDesLabel;
	private JLabel moneteLabel;
	private JLabel contenutoCasellaDesLabel;
	private JLabel contenutoCasellaLabel;
	private JLabel livelloDesLabel;
	private JLabel livelloLabel;
	private JLabel grazieLabel;
	private JLabel theShandonAdventureLabel;

	public SchedaGioco(){
		inizializzaGUI();
	}



	public SchedaGioco(Partita partita) {
		inizializzaGUI();
		aggiungiLabel();
		inserisciDatiIniziali(partita);
		update(partita,partita);		
	}


	private void inizializzaGUI(){
		setBackground(new Color(209,245,255));		
		this.setVisible(true);  
	}

	private void aggiungiLabel() {
		this.setLayout(new GridLayout(8, 2));			
		nomeLabel = new JLabel();
		nomeLabel.setAlignmentX(JLabel.CENTER);
		nomeLabel.setHorizontalAlignment(JLabel.CENTER);
		nomeInseritoLabel = new JLabel();        
		nomeInseritoLabel.setHorizontalAlignment(JLabel.CENTER);

		livelloDesLabel = new JLabel();        
		livelloDesLabel.setHorizontalAlignment(JLabel.CENTER);
		livelloLabel = new JLabel();        
		livelloLabel.setHorizontalAlignment(JLabel.CENTER);

		categoriaDesLabel = new JLabel();        
		categoriaDesLabel.setHorizontalAlignment(JLabel.CENTER);
		categoriaLabel = new JLabel();        
		categoriaLabel.setHorizontalAlignment(JLabel.CENTER);

		expDesLabel = new JLabel();        
		expDesLabel.setHorizontalAlignment(JLabel.CENTER);
		expLabel = new JLabel();        
		expLabel.setHorizontalAlignment(JLabel.CENTER);


		hpDesLabel = new JLabel();
		hpDesLabel.setHorizontalAlignment(JLabel.CENTER);
		hpLabel = new JLabel();        
		hpLabel.setHorizontalAlignment(JLabel.CENTER);//

		armaDesLabel = new JLabel();        
		armaDesLabel.setHorizontalAlignment(JLabel.CENTER);
		armaLabel = new JLabel();
		armaLabel.setHorizontalAlignment(JLabel.CENTER);
		moneteDesLabel = new JLabel();        
		moneteDesLabel.setHorizontalAlignment(JLabel.CENTER);
		moneteLabel = new JLabel();
		moneteLabel.setHorizontalAlignment(JLabel.CENTER);
		contenutoCasellaDesLabel = new JLabel();        
		contenutoCasellaDesLabel.setHorizontalAlignment(JLabel.CENTER);
		contenutoCasellaLabel = new JLabel();        
		contenutoCasellaLabel.setHorizontalAlignment(JLabel.CENTER);

		grazieLabel = new JLabel();
		grazieLabel.setHorizontalAlignment(JLabel.CENTER);
		theShandonAdventureLabel = new JLabel();
		theShandonAdventureLabel.setHorizontalAlignment(JLabel.CENTER);		
		theShandonAdventureLabel.setVerticalAlignment(JLabel.TOP);		

		this.add(nomeLabel);
		this.add(nomeInseritoLabel);

		this.add(categoriaDesLabel);
		this.add(categoriaLabel);

		this.add(livelloDesLabel);
		this.add(livelloLabel);

		this.add(expDesLabel);
		this.add(expLabel);

		this.add(hpDesLabel);
		this.add(hpLabel);

		this.add(armaDesLabel);
		this.add(armaLabel);

		this.add(moneteDesLabel);
		this.add(moneteLabel);

		this.add(contenutoCasellaDesLabel);
		this.add(contenutoCasellaLabel);

	}



	private void inserisciDatiIniziali(Partita partita){
		nomeLabel.setText("Nome guerriero:"); 
		nomeInseritoLabel.setText(partita.getGuerriero().getNome());
		categoriaDesLabel.setText("Categoria:"); 
		categoriaLabel.setText(partita.getGuerriero().getCategoria());		
		livelloDesLabel.setText("Livello:"); 
		expDesLabel.setText("Esperienza:"); 
		hpDesLabel.setText("Punti vita:"); 
		armaDesLabel.setText("Arma impugnata:"); 
		moneteDesLabel.setText(Visualizzatore.MONETE);
		contenutoCasellaDesLabel.setText("Contenuto casella corrente:");
		grazieLabel.setFont(new Font("Serif",Font.ROMAN_BASELINE,20));
		grazieLabel.setText("Grazie per aver giocato a");
		theShandonAdventureLabel.setFont(new Font("Serif",Font.BOLD,35));
		theShandonAdventureLabel.setText("The Shandon Adventure");
		this.setVisible(true);  
	} 


	private void setContenutoCasella(String s){
		contenutoCasellaLabel.setText(s);
	}

	private void setPuntiVita(Double d){
		hpLabel.setText(Double.toString(d));
	}

	private void setArma(String a){
		armaLabel.setText(a);
	}

	private void setLivello(int l){
		livelloLabel.setText(Integer.toString(l));
	}

	private void setMonete(int m){
		moneteLabel.setText(Integer.toString(m));
	}

	private void setExp(int e){
		expLabel.setText(Integer.toString(e));
	}

	@Override
	public void update(Observable o, Object arg) {
		Partita partita=(Partita)arg;
		setContenutoCasella(partita.contenutoCasellaToString(partita.getGuerriero().getPosizione()));
		setPuntiVita(partita.getGuerriero().getPuntiVita());
		setMonete(partita.getGuerriero().getMonete());
		try{
			setArma(partita.getGuerriero().getArma().getNomeOggetto());
		}
		catch(NullPointerException e){
			setArma(Visualizzatore.NESSUNA);			
		}
		setLivello(partita.getGuerriero().getLivello());
		setExp(partita.getGuerriero().getPuntiEsperienza());
	}
}