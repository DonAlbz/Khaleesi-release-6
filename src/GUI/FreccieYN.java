package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Main;

public class FreccieYN extends JPanel{
	private final Dimension dimensioneBottone = new Dimension(75,50);
	private final Dimension dimensioneBloccoFreccie = new Dimension(200,200);
	private JPanel centerPanel;
	private JPanel frecciePanel;
	private JPanel yNPanel;

	private JButton nord;
	private JButton sud;
	private JButton ovest;
	private JButton est;
	private JButton yes;
	private JButton no;

	public FreccieYN() {
		inizializza();
	}

	private void inizializza() {
		this.setOpaque(false);
		inizializzaCentro();
		setVisible(true);
	}


	private void inizializzaCentro() {
		centerPanel=new JPanel();
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 75,100));
		centerPanel.setOpaque(false);
		inizializzaFreccie();
		inizializzaYN();
		this.add(centerPanel);
		centerPanel.setVisible(true);
	}

	private void inizializzaFreccie() {
		frecciePanel = new JPanel();
		frecciePanel.setOpaque(false);
		nord=new JButton(Visualizzatore.NORD);
		sud=new JButton(Visualizzatore.SUD);
		ovest=new JButton(Visualizzatore.OVEST);
		est=new JButton(Visualizzatore.EST);
		nord.setPreferredSize(new Dimension(dimensioneBottone));
		sud.setPreferredSize(new Dimension(dimensioneBottone));
		ovest.setPreferredSize(new Dimension(dimensioneBottone));
		est.setPreferredSize(new Dimension(dimensioneBottone));
		nord.setName(Integer.toString(Visualizzatore.NORD_INT));
		sud.setName(Integer.toString(Visualizzatore.SUD_INT));
		ovest.setName(Integer.toString(Visualizzatore.OVEST_INT));
		est.setName(Integer.toString(Visualizzatore.EST_INT));
		frecciePanel.setLayout(new BorderLayout());
		frecciePanel.add(nord, BorderLayout.NORTH);
		frecciePanel.add(sud, BorderLayout.SOUTH);
		frecciePanel.add(ovest, BorderLayout.WEST);
		frecciePanel.add(est, BorderLayout.EAST);
		frecciePanel.setPreferredSize(dimensioneBloccoFreccie);
		frecciePanel.setMinimumSize(dimensioneBloccoFreccie);		
		frecciePanel.setVisible(true);
		enableFreccie(true);
		centerPanel.add(frecciePanel);

	}

	private void inizializzaYN() {		
		yNPanel= new JPanel();
		yNPanel.setOpaque(false);
		yNPanel.setLayout(new GridLayout(1,2));
		yes=new JButton(Visualizzatore.YES);
		no=new JButton(Visualizzatore.NO);
		yes.setActionCommand(Visualizzatore.YES);
		no.setActionCommand(Visualizzatore.NO);
		yNPanel.add(yes);
		yNPanel.add(no);
		yNPanel.setVisible(true);
		enableYN(true);
		centerPanel.add(yNPanel);
	}

	public void enableYN(Boolean b){
		yes.setEnabled(b);
		no.setEnabled(b);
		yNPanel.setVisible(b);
	}

	public void enableFreccie(Boolean b){
		nord.setEnabled(b);
		sud.setEnabled(b);
		ovest.setEnabled(b);
		est.setEnabled(b);
		frecciePanel.setVisible(b);
	}

	public JButton getYesButton() {
		return yes;
	}

	public JButton getNoButton() {
		return no;
	}
	
	public JButton getNordButton() {
		return nord;
	}

	public JButton getSudButton() {
		return sud;
	}
	public JButton getOvestButton() {
		return ovest;
	}

	public JButton getEstButton() {
		return est;
	}
}
