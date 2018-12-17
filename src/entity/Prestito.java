package entity;

import java.time.LocalDateTime;

import action.CalcoliPerPrestito;
import mylib.Data;
/**
 *  La classe Prestito rappresenta un prestito di una determinata
 *  risorsa.
 * @author Marika
 *
 */
public class Prestito {

	private String tipoRisorsa;
	private Risorsa r;
	private String nomeFruitore;
	private LocalDateTime inizio_prestito ;
	private LocalDateTime fine_prestito ;
	private int numero_rinnovo;
	/**
	 *  Costruttore Prestito. Un prestito è costituito da una risorsa, da una data
	 *  di inizio prestito, da una data di fine prestito e da un numero di rinnovo
	 *  associato alla risorsa.
	 * @param r
	 */
	public Prestito(Risorsa r, String nomeFruitore){ 
		this.r = r;
		this.nomeFruitore = nomeFruitore;
		inizio_prestito = Data.creaData(); 
		fine_prestito = calcolo_fine_prestito();
		numero_rinnovo = 0;
		this.setTipoRisorsa(r.getTipo());//SBAGLIA
	}
	
	public LocalDateTime calcolo_fine_prestito(){
		CalcoliPerPrestito calcoli = new CalcoliPerPrestito(this);
		return calcoli.calcolo_fine_prestito();
	}
	
	
//	/**
//	 * Metodo calcolo_fine_prestito permette di calcolare la data di 
//	 *  scadenza del prestito che è esattamente 30 giorni dopo la
//	 *   data di inizio prestito.
//	 * @return
//	 */
//	public LocalDateTime calcolo_fine_prestito(){
//		//METODO CORRETTO: scadenza 30 giorni dalla data di inizio prestito
//		//fine_prestito = Data.cambiaGiorno(30, inizio_prestito);
//		//DIMOSTRAZIONE METODO: scadenza 5 minuti dopo quella di iscrizione
//		if(r instanceof Libro){
//			fine_prestito = Data.cambiaMinuto(Libro.DURATA_MAX_PRESTITO, inizio_prestito);
//		}		
//		else{
//			fine_prestito = Data.cambiaMinuto(Film.DURATA_MAX_PRESTITO, inizio_prestito);
//		}
//		return fine_prestito;
//	}
//	
//	/**
//	 * Metodo calcoloTerminiPrescrittiProroga calcola, data la data di scadenza del prestito,
//	 *  l'intervallo di tempo entro cui è richiedibile la proroga del prestito.
//	 *  In questo caso è possibile richiedere la proroga non prima dei 3 giorni precedenti
//	 *  alla scadenza.
//	 * @return
//	 */
//	public LocalDateTime calcoloTerminiPrescrittiProroga(){
//		LocalDateTime d;
//		//METODO CORRETTO: intervallo proroga non prima dei 3 giorni precedenti la scadenza
//		//d = Data.cambiaGiorno(3, fine_prestito);
//		//DIMOSTRAZIONE METODO: intervallo proroga non prima dei 3 minuti precedenti la scadenza
//		if(this.getR() instanceof Libro){
//			d  = Data.menoMinuto(Libro.INTERVALLO_RICHIESTA_PROROGA, this.getFine_prestito());
//			return d;
//		}
//		else{
//			d  = Data.menoMinuto(Film.INTERVALLO_RICHIESTA_PROROGA,this.getFine_prestito());
//			return d;
//		}
//	}
	/**
	 * Metodo rinnovo controlla la possibilità di effettuare un rinnovo
	 * di tale prestito. Il rinnovo può essere richiesto una sola volta.
	 * @return
	 */
	public boolean rinnovo(){
		if(this.getNumero_rinnovo()==0){
			return true;
		}
		return false;
	}


	public LocalDateTime getInizio_prestito() {
		return inizio_prestito;
	}

	public LocalDateTime getFine_prestito() {
		return fine_prestito;
	}

	public void setInizio_prestito(LocalDateTime inizio_prestito) {
		this.inizio_prestito = inizio_prestito;
	}

	public void setFine_prestito(LocalDateTime fine_prestito) {
		this.fine_prestito = fine_prestito;
	}

	public Risorsa getR() {
		return r;
	}

	public void setR(Risorsa r) {
		this.r = r;
	}


	public int getNumero_rinnovo() {
		return numero_rinnovo;
	}

	public void setNumero_rinnovo(int numero_rinnovo) {
		this.numero_rinnovo = numero_rinnovo;
	}

	public String getNomeFruitore() {
		return nomeFruitore;
	}
	public void setNomeFruitore(String nomeFruitore) {
		this.nomeFruitore = nomeFruitore;
	}

	public String getTipoRisorsa() {
		return tipoRisorsa;
	}

	public void setTipoRisorsa(String tipoRisorsa) {
		this.tipoRisorsa = tipoRisorsa;
	}
	
}
