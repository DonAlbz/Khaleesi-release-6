/**
 * 
 */
package main;

/**
 * @author Mikele
 *
 */
public class Ladro extends Guerriero {

	/**
	 * @param forza
	 * @param velocita
	 * @param categoria
	 */
	public Ladro() {
		super(Parametri.LADRO_F, Parametri.LADRO_V, Parametri.LADRO);
		// TODO Auto-generated constructor stub
	}
	
	public Ladro (String nome){
		super(nome,Parametri.LADRO_F, Parametri.LADRO_V, Parametri.LADRO);
	}

}
