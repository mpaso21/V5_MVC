package entity;


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
	//COSTANTI DI FILM SPOSTATE IN UN APPOSITA CLASSE CONSTANTS LIBRO -> REFACTOR MOVE FIELD

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
			int annoDiUscita, String casaProduttrice, String lingua,String genere){
		super(nome,genere, lingua); //COSTRUTTORE CAMBIATO PERCHE' HO CAMBIATO QUELLO DI RISORSA
		super.setTipo(ConstantsRisorsa.FILM);//SETTO TIPO A FILM
		this.regista = regista;
		this.durata = durata;
		this.annoDiUscita = annoDiUscita;
		this.casaProduttrice = casaProduttrice;
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
			if( ( (Film) r ).getRegista().equalsIgnoreCase(this.getRegista()) && //CAST RISORSA IN LIBRO COSI POSSO APPLICARE I METODI DELLA CLASSE LIBRO
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
