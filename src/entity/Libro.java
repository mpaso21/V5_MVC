package entity;


import mylib.ConstantsRisorsa;

/**
 *  Classe Libro rappresenta un tipo di Risorsa. 
 * @author Marika
 *
 */
public class Libro extends Risorsa {
	private String autore, casaEditrice;
	private int anno, numeroPagine;
	//COSTANTI DI LIBRO SPOSTATE IN UN APPOSITA CLASSE CONSTANTS LIBRO -> REFACTOR MOVE FIELD
	/**	
	 * Costruttore Libro. Ciascun libro è costituito da un titolo, autore, numero di pagine,
	 * anno di pubblicazione, casa editrice, lingua e genere.
	 * @param nom
	 * @param autore
	 * @param numeroDiPagine
	 * @param annoDiPubblicazione
	 * @param casaEditrice
	 * @param lingua
	 * @param genere
	 */
	public Libro(String nome, String autore, String lingua, String genere, String casaEditrice, int anno, int numeroPagine) {
		super(nome, genere, lingua); //COSTRUTTORE CAMBIATO PERCHE' HO CAMBIATO QUELLO DI RISORSA
		super.setTipo(ConstantsRisorsa.LIBRO); //SETTO IL TIPO A LIBRO
		this.autore = autore;
		this.casaEditrice = casaEditrice;
		this.anno = anno;
		this.numeroPagine = numeroPagine;

	}
//METODO STATIC CREA LIBRO CREA IL LIBRO TRAMITE INPUT. SPOSTATO IN LIBROVIEW
//REFACTOR ->MOVE METHOD. APPARTIENE ALLA VIEW.ANCHE METODO TO STRING, STRINGA STORICO
// E STRINGA STORICO INTESTAZIONE
	
	
	//METODO EQUALS APPARTIENE A LIBRO.
	/**		
	 *  Metodo equals utile per sfruttare il metodo remove by Object e non by Index
	 *  utilizzato con gli ArrayList.
	 */
	@Override
	public boolean equals(Object r) {
		if(r instanceof Libro){
			if( ( (Libro) r ).getAutore().equalsIgnoreCase(this.getAutore()) && //CASTO RISORSA IN LIBRO COSI POSSO APPLICARE I METODI DELLA CLASSE LIBRO
					( (Libro) r).getNome().equalsIgnoreCase(this.getNome())){
				return true;
			};

		}
		return false;
	}
	
	public String getAutore() {
		return autore;
	}

	public String getCasaEditrice() {
		return casaEditrice;
	}

	public int getAnno() {
		return anno;
	}

	public int getNumeroPagine() {
		return numeroPagine;
	}


	public void setAutore(String autore) {
		this.autore = autore;
	}

	public void setCasa(String casa) {
		this.casaEditrice = casa;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public void setNumeroPagine(int numeroPagine) {
		this.numeroPagine = numeroPagine;
	}

}
