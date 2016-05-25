package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import main.Partita;

public class Finestra extends JFrame implements ActionListener {	
	Partita partita;
	private SchedaGioco schedaGioco;
	private Console console;
	private JLabel grazieLabel;
	private JLabel theShandonAdventureLabel;

	public Finestra(){		
		inizializzaGUI();
		console = new Console();		
		/*this.partita=new Partita();
		schedaGioco= new SchedaGioco(partita);				
		partita.addObserver(schedaGioco);
		inizializzaListeners();
		aggiungiLabel();
		Visualizzatore.setFinestra(this);*/
		schedaGioco=new SchedaGioco();
		add(schedaGioco);
		add(console);
		Visualizzatore.setFinestra(this);
		setVisible(true);
		ascoltaNomeECategoria();
	}

	
	private void ascoltaNomeECategoria() {
		console.getAvantiButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!console.getNomeButton().getText().isEmpty()){
					setGioco(console.getNomeButton().getText(), console.getCategoriaButton().getSelectedIndex()+1);
				}
				
			}
		});
	}


	public void setGioco(String nomeGuerriero,int categoria){
		this.partita=new Partita(nomeGuerriero, categoria);
		schedaGioco= new SchedaGioco(partita);
		console.setFreccieEYN();
		partita.addObserver(schedaGioco);
		inizializzaListeners();
		aggiungiLabel();
		Visualizzatore.setFinestra(this);
		start();
	}
	
	public void start(){
		partita.start();
	}

	private void inizializzaListeners() {/*
		console.getYesButton().addActionListener(this);
		console.getNoButton().addActionListener(this);*/
		console.getNordButton().addActionListener(this);
		console.getSudButton().addActionListener(this);
		console.getOvestButton().addActionListener(this);
		console.getEstButton().addActionListener(this);
	}

	private void aggiungiLabel() {
		getContentPane().removeAll();
		add(schedaGioco);
		add(console);
	}


	private void inizializzaGUI() {
		setTitle("Finestra di Gioco");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(1000,1000);
		this.setResizable(false);
		setLayout(new GridLayout(1, 2));
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});    
		setAlwaysOnTop(true);

		setLocation(0, 0);
		grazieLabel = new JLabel();
		grazieLabel.setHorizontalAlignment(JLabel.CENTER);
		theShandonAdventureLabel = new JLabel();
		theShandonAdventureLabel.setHorizontalAlignment(JLabel.CENTER);		
		theShandonAdventureLabel.setVerticalAlignment(JLabel.TOP);		
		grazieLabel.setFont(new Font("Serif",Font.ROMAN_BASELINE,20));
		grazieLabel.setText("Grazie per aver giocato a");
		theShandonAdventureLabel.setFont(new Font("Serif",Font.BOLD,35));
		theShandonAdventureLabel.setText("The Shandon Adventure");
		setVisible(true);
	}

	public void arrivederci(){
		getContentPane().removeAll();
		setLayout(new GridLayout(2, 1));
		add(grazieLabel);
		add(theShandonAdventureLabel);	
		revalidate();
		repaint();
		try {
			Thread.sleep(4000);
			dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public SchedaGioco getSchedaGioco() {
		return schedaGioco;
	}

	/**
	 * @return the console
	 */
	public Console getConsole() {
		return console;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton premuto = (JButton)e.getSource();
		if(premuto==console.getNordButton()||
				premuto==console.getSudButton()||
				premuto==console.getOvestButton()||
				premuto==console.getEstButton()){
			partita.eseguiPasso(Integer.parseInt(premuto.getName()));
		}
	}
	
}

