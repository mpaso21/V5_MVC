package entity;

import java.time.LocalDateTime;
import action.CalcoloFinePrestito;
import action.Calcolo;
import mylib.Data;
/**
 *  La classe Prestito rappresenta un prestito di una determinata
 *  risorsa.
 * @author Marika
 *
 */
public class Prestito {

	//private String tipoRisorsa;
	private Risorsa r;
	private Fruitore fruitore;
	private LocalDateTime inizio_prestito ;
	private LocalDateTime fine_prestito ;
	private int numero_rinnovo;
	/**
	 *  Costruttore Prestito. Un prestito è costituito da una risorsa, da una data
	 *  di inizio prestito, da una data di fine prestito e da un numero di rinnovo
	 *  associato alla risorsa, e dal nome del fruitore.
	 * @param r
	 */
	public Prestito(Risorsa r, Fruitore fruitore){ 
		this.r = r;
		this.fruitore = fruitore;
		inizio_prestito = Data.creaData(); 
		fine_prestito = calcolo_fine_prestito();
		numero_rinnovo = 0;
	//	this.setTipoRisorsa(r.getTipo());
	}
	
	/**
	 * Metodo calcolo_fine_prestito richiama il metodo calcolo della classe
	 * CalcoloFinePrestito che ha la responsabilià di calcolare, dato un prestito,
	 * la fine del prestito.
	 * @return
	 */
	//AGGIUNTA METODO CHE RICHIAMA UTILITA' CALCOLO FINE PRESTITO
	//CALCOLO METODO PRESCRITTI PROROGA SPOSTATO IN AZIONE PROROGA -> REFACTOR MOVE METHOD
	
	//METODO STAMPA PRESTITO -> IN VIEW
	//METODO TO STRING LIBRO /FILM -> IN VIEW
	public LocalDateTime calcolo_fine_prestito(){
		Calcolo calcoloFine = new CalcoloFinePrestito(this);
		return calcoloFine.calcolo();
	}

	//METODO RINNOVO RIMASTO UGUALE APPARTENENTE A PRESTITO
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



	public Fruitore getFruitore() {
		return fruitore;
	}

	public String getTipoRisorsa() {
		return r.getTipo();
	}

	
//	public void setTipoRisorsa(String tipoRisorsa) {
//		this.tipoRisorsa = tipoRisorsa;
//	}
	
}
