package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.AbstractObject;
import main.AbstractStato;
import main.Arma;
import main.Forziere;
import main.Orco;
import main.Pozione;
import main.Stato;

public class Ascoltatore implements ActionListener{
	AbstractStato stato;
	AbstractObject abstractObject;
	
	
	public Ascoltatore(AbstractStato stato, AbstractObject abstractObject) {
		this.stato = stato;
		this.abstractObject = abstractObject;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		JButton premuto = (JButton)e.getSource();
		Boolean scelta;
		if (premuto.getActionCommand().equals(Visualizzatore.YES))
			scelta=true;
		else
			scelta=false;
		
		stato.oggettoYN(abstractObject, scelta);
	}
	
	
}

