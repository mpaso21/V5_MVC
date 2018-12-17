package entity;



/**
 * La classe Cittadino rappresenta una persona appartenente a una comunit� che pu� effettuare 
 * una richiesta per diventare fruitore dei servizi di prestito.
 * @author Marika
 *
 */
public class Cittadino {
	protected String nome; 
	protected int eta;

	/**
	 * Costruttore classe Cittadino. Ciascun cittadino � costituito da un nome e da un'et�.
	 * @param nome
	 * @param eta
	 */
	public Cittadino(String nome, int eta){
		this.nome = nome; //HO SPOSTATO CREA NOME, CREA ETA' IN MY LIB -> MOVE METHOD
		this.eta = eta;
	}

	public String getNome() {
		return nome;
	}

	public int getEta() {
		return eta;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public void setEta(int eta) {
		this.eta = eta;
	}


}
