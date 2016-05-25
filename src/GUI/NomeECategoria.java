package GUI;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Parametri;

public class NomeECategoria extends JPanel {
	private JPanel nomePanel;
	private JPanel categoriaPanel;
	private JPanel avantiPanel;
	private JTextField nome;
	private JComboBox categoria;
	private JButton avanti;
	

	public NomeECategoria() {
		inizializza();
	}

	private void inizializza() {
		this.setOpaque(false);
		setLayout(new GridLayout(3,1));
		inizializzaNomePanel();
		inizializzaCategoriaPanel();
		inizializzaAvantiPanel();
		add(nomePanel);
		add(categoriaPanel);
		add(avantiPanel);
		setVisible(true);
	}


	private void inizializzaAvantiPanel() {
		avantiPanel=new JPanel();
		avantiPanel.setOpaque(false);
		avantiPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,75));
		avanti = new JButton(Visualizzatore.BUTTON_NOME_CATEGORIA_FINITO);
		avantiPanel.add(avanti);
		avantiPanel.setVisible(true);
	}

	private void inizializzaCategoriaPanel() {
		categoriaPanel=new JPanel();
		categoriaPanel.setOpaque(false);
		categoriaPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,75));
		categoria = new JComboBox(Parametri.CATEGORIE);
		categoriaPanel.add(categoria);
		
		categoriaPanel.setVisible(true);		
	}

	private void inizializzaNomePanel() {
		nomePanel=new JPanel();
		nomePanel.setOpaque(false);
		nomePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,75));
		nome = new JTextField(Parametri.NOME_GUERRIERO_LUNGHEZZA_MAX);
		nomePanel.add(nome);
		nomePanel.setVisible(true);
	}

	/**
	 * @return the nome
	 */
	public JTextField getNome() {
		return nome;
	}

	/**
	 * @return the categoria
	 */
	public JComboBox getCategoria() {
		return categoria;
	}

	/**
	 * @return the avanti
	 */
	public JButton getAvanti() {
		return avanti;
	}

	
	
	
}
