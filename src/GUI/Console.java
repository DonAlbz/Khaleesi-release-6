package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main.Partita;

public class Console extends JPanel {
	private JLabel top;
	private FreccieYN bottomFreccie;
	private NomeECategoria nomeECategoria;

	public Console() {
		inizializzaGUI();
	}

	private void inizializzaGUI() {
		setBackground(new Color(219,255,255));
		this.setLayout(new GridLayout(2, 1));
		top = new JLabel();
		top.setHorizontalAlignment(JLabel.CENTER);		
		top.setVerticalAlignment(JLabel.CENTER);		
		/*testo.setBackground(UIManager.getColor("Label.background"));
		testo.setFont(UIManager.getFont("Label.font"));
		testo.setBorder(UIManager.getBorder("Label.border"));
		 */
		top.setFont(new Font("Serif",Font.BOLD,35));
		top.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		nomeECategoria=new NomeECategoria();
		bottomFreccie=new FreccieYN();
		add(top);
		add(nomeECategoria);
	}

	public void stampa(String stringa){
		top.setText(stringa);
	}
	
	public void setFreccieEYN(){
		remove(nomeECategoria);
		add(bottomFreccie);
	}
	
	public void stampaAppend(String stringa){
		stampa(Visualizzatore.append(top.getText(), stringa));
	}

	public void richiediSpostamento() {
		Visualizzatore.richiediSpostamento();
		enableFreccie(true);
	}

	public void enableFreccie(Boolean b){
		 bottomFreccie.enableFreccie(b);		
	}
	
	public void enableYN(Boolean b){
		 bottomFreccie.enableYN(b);		
	}
	
	public JButton getYesButton(){
		return bottomFreccie.getYesButton();		
	}

	public JButton getNoButton() {
		return bottomFreccie.getNoButton();
	}
	public JButton getNordButton(){
		return bottomFreccie.getNordButton();
	}

	public JButton getSudButton() {
		return bottomFreccie.getSudButton();
	}
	public JButton getOvestButton(){
		return bottomFreccie.getOvestButton();		
		
	}

	public JButton getEstButton() {
		return bottomFreccie.getEstButton();
	}
	
	public JTextField getNomeButton(){
		return nomeECategoria.getNome();
	}

	public JComboBox getCategoriaButton() {
		return nomeECategoria.getCategoria();
	}
	public JButton getAvantiButton(){
		return nomeECategoria.getAvanti();		
		
	}
	
	
}
