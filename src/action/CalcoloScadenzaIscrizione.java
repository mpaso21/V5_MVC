package action;

import java.time.LocalDateTime;

import entity.Fruitore;

import mylib.ConstantsCittadino;
import mylib.Data;



public class CalcoloScadenzaIscrizione implements Calcolo{
	
private Fruitore p;
	
	public CalcoloScadenzaIscrizione(Fruitore p){
		this.p = p;
	}

	/**
	 * Metodo calcolo_fine_prestito permette di calcolare la data di 
	 *  scadenza del prestito che è esattamente 30 giorni dopo la
	 *   data di inizio prestito.
	 * @return
	 */
	public LocalDateTime calcolo(){
		//METODO CORRETTO: scadenza 5 anni dopo quella di iscrizione
				// scadenza_iscrizione = Data.cambiaAnno(5, inizio_iscrizione);
				//DIMOSTRAZIONE METODO: scadenza 5 minuti dopo quella di iscrizione
		//LocalDateTime valore = Data.cambiaAnno(ConstantsCittadino.NUM_SCADENZA, p.getInizioIscrizione());
		LocalDateTime valore = Data.cambiaMinuto(ConstantsCittadino.NUM_SCADENZA, p.getInizioIscrizione());
		return valore;
	}
	
}
