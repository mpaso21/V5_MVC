package entity;


import mylib.ConstantsPrestito;
import mylib.ConstantsRisorsa;

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
	//COSTANTI DI FILM SPOSTATE IN UN APPOSITA CLASSE CONSTANTS PRESTITO -> REFACTOR MOVE FIELD

	/**	
	 * Costruttore Film. Ciascun film è costituito da un titolo, regista, durata,
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
			int annoDiUscita, String casaProduttrice, String lingua,String genere, int numLicenze){
		super(nome,genere, lingua, numLicenze); //COSTRUTTORE CAMBIATO PERCHE' HO CAMBIATO QUELLO DI RISORSA
		super.setTipo(ConstantsRisorsa.FILM);//IMPOSTO IL  TIPO DI RISORSA A FILM
		this.regista = regista;
		this.durata = durata;
		this.annoDiUscita = annoDiUscita;
		this.casaProduttrice = casaProduttrice;
		this.costantiCalcoloRisorsa.put(ConstantsPrestito.DURATA_MAX_PRESTITO_KEY, ConstantsPrestito.DURATA_MAX_PRESTITO_FILM);
		this.costantiCalcoloRisorsa.put(ConstantsPrestito.INTERVALLO_RICHIESTA_PROROGA_KEY, ConstantsPrestito.INTERVALLO_RICHIESTA_PROROGA_FILM);
		this.costantiCalcoloRisorsa.put(ConstantsPrestito.NUM_MAX_PRESTITI_KEY, ConstantsPrestito.NUM_MAX_PRESTITI_FILM);

		
	}

	//METODO STATIC CREA FILM CREA IL LIBRO TRAMITE INPUT. SPOSTATO IN FILMVIEW
	//REFACTOR ->MOVE METHOD. APPARTIENE ALLA VIEW.ANCHE METODO TO STRING, STRINGA STORICO
	// E STRINGA STORICO INTESTAZIONE
		
		
	//METODO EQUALS APPARTIENE A FILM.
	/**
	 * Metodo equals utile per sfruttare il metodo remove by Object e non by Index
	 * utilizzato con gli ArrayList.
	 */
	@Override
	public boolean equals(Object r) {//metodo equals per sfruttare il remove by object e non by index dell'arraylist(perchè indice bisogna salvarlo ogni volta)
		if(r instanceof Film){
			if( ( (Film) r ).getRegista().equalsIgnoreCase(this.getRegista()) && //CAST RISORSA IN FILM PER POTER RICHIAMARE I METODI DELLA CLASSE FILM
					( (Film) r).getNome().equalsIgnoreCase(this.getNome())){
				return true;
			};

		}
		return false;
	}
	
	public String getRegista() {
		return regista;
	}

	public void setRegista(String regista) {
		this.regista = regista;
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


	public void setDurata(int durata) {
		this.durata = durata;
	}

	public void setAnnoDiUscita(int annoDiUscita) {
		this.annoDiUscita = annoDiUscita;
	}

	public void setCasaProduttrice(String casaProduttrice) {
		this.casaProduttrice = casaProduttrice;
	}

	

}
