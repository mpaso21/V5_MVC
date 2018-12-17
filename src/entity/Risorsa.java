package entity;

import mylib.Constants;
import mylib.UtilitaControllo;

/**
 * Classe Risorsa rappresenta una risorsa, di qualunque tipo, presente nell'archivio,
 * che può essere acquisita da un fruitore.
 * @author Marika
 *
 */
/**
 * @author Marika
 *
 */
public class Risorsa {

	protected String nome;
	protected String genere;
	protected int numeroLicenze;
	protected String tipo;
/**
 *  Costruttore Risorsa. Una risorsa è costituita da un nome e da un numero di licenze.
 * @param nome
 */
	public Risorsa(String nome, String genere){
		this.nome = nome;
		this.genere = genere; //GENERE SPOSTATO QUI PERCHE' COMUNE A LIBRO/FILM MOVE FIELD REFACTOR
		this.numeroLicenze = UtilitaControllo.randomcompreso(Constants.MIN_LICENZA,Constants.MAX_LICENZA);
		tipo = Constants.RISORSA;
	}
	
public String getTipo() {
	return tipo;
}

public void setTipo(String tipo) {
	this.tipo = tipo;
}

/**
	 * Metodo risorsaUguale controlla se due risorse sono uguali.
	 * Per uguaglianza di risorsa si intende nome (titolo) uguale.
	 * @param r
	 * @return
	 */
	 public boolean equals(Risorsa r) {
	 if(r.getNome().equalsIgnoreCase(this.getNome())) {
		 return true;
	 }
	 	return false;
	 }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumeroLicenze() {
		return numeroLicenze;
	}

	public void setNumeroLicenze(int numeroLicenze) {
		this.numeroLicenze = numeroLicenze;
	}

	

	public String getGenere() {
		return genere;
	}



	
}
