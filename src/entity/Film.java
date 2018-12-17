package entity;

import mylib.Constants;
import mylib.UtilitaCreazioneCampi;
/**
 *  Classe Film rappresenta un tipo di Risorsa. 
 * @author Marika
 *
 */
public class Film extends Risorsa {
	private String regista;
	private int durata;
	private int annoDiUscita;
	private String casaProduttrice;
	private String lingua;

	
//	VALORE CORRETTO	= 30 GG
public static final int DURATA_MAX_PRESTITO = 5; 
public static final int INTERVALLO_RICHIESTA_PROROGA = 3;
public static final int NUM_MAX_PRESTITI = 3;
	/**	
	 * Costruttore Film. Ciascun film � costituito da un titolo, regista, durata,
	 * anno di uscita, casa produttrice, lingua e genere.
	 * @param nome
	 * @param regista
	 * @param durata
	 * @param annoDiUscita
	 * @param casaProduttrice
	 * @param lingua
	 * @param genere
	 */
	public Film(String nome ,String regista,int durata, 
			int annoDiUscita, String casaProduttrice, String lingua,String genere){
		super(nome,genere);
		super.setTipo(Constants.FILM);
		this.regista = regista;
		this.durata = durata;
		this.annoDiUscita = annoDiUscita;
		this.casaProduttrice = casaProduttrice;
		this.lingua = lingua;
	
	
	}



	public String getRegista() {
		return regista;
	}

	public void setRegista(String regista) {
		this.regista = regista;
	}
	/**
	 * Metodo equals utile per sfruttare il metodo remove by Object e non by Index
	 * utilizzato con gli ArrayList.
	 */
	@Override
	public boolean equals(Object r) {//metodo equals per sfruttare il remove by object e non by index dell'arraylist(perch� indice bisogna salvarlo ogni volta)
		if(r instanceof Film){
			if( ( (Film) r ).getRegista().equalsIgnoreCase(this.getRegista()) && //CAST RISORSA IN LIBRO COSI POSSO APPLICARE I METODI DELLA CLASSE LIBRO
					( (Film) r).getNome().equalsIgnoreCase(this.getNome())){
				return true;
			};

		}
		return false;
	}

	
	

	public int getDurata() {
		return durata;
	}

	public int getAnnoDiUscita() {
		return annoDiUscita;
	}

	public String getCasaProduttrice() {
		return casaProduttrice;
	}

	public String getLingua() {
		return lingua;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	public void setAnnoDiUscita(int annoDiUscita) {
		this.annoDiUscita = annoDiUscita;
	}

	public void setCasaProduttrice(String casaProduttrice) {
		this.casaProduttrice = casaProduttrice;
	}

	public void setLingua(String lingua) {
		this.lingua = lingua;
	}

	
}
