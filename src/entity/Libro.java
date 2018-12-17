package entity;

import mylib.Constants;

/**
 *  Classe Film rappresenta un tipo di Risorsa. 
 * @author Marika
 *
 */
public class Libro extends Risorsa {
	private String titolo, autore, lingua, casaEditrice;
	private int anno, numeroPagine;
	
	
//	 VALORE CORRETTO	= 30 GG
public static final int DURATA_MAX_PRESTITO = 5; 
public static final int INTERVALLO_RICHIESTA_PROROGA = 3;
public static final int NUM_MAX_PRESTITI = 3;
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
		super(nome, genere);
		super.setTipo(Constants.LIBRO);
		this.autore = autore;
		this.lingua = lingua;
		this.casaEditrice = casaEditrice;
		this.anno = anno;
		this.numeroPagine = numeroPagine;
		
	}

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
	public String getTitolo() {
		return titolo;
	}

	public String getAutore() {
		return autore;
	}

	public String getLingua() {
		return lingua;
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

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public void setLingua(String lingua) {
		this.lingua = lingua;
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
