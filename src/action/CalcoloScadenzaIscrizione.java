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
	 * Metodo calcoloScadenza permette, avendo una data di iscrizione, di calcolare 
	 * La scadenza di iscrizione che è esattamente 5 anni dopo la data di iscrizione.
	 * @return
	 */
	public LocalDateTime calcolo(){
		//METODO CORRETTO: scadenza 5 anni dopo quella di iscrizione
				// scadenza_iscrizione = Data.cambiaAnno(5, inizio_iscrizione);
				//DIMOSTRAZIONE METODO: scadenza 10 minuti dopo quella di iscrizione
		//LocalDateTime valore = Data.cambiaAnno(ConstantsCittadino.NUM_SCADENZA, p.getInizioIscrizione());
		LocalDateTime valore = Data.cambiaMinuto(ConstantsCittadino.NUM_SCADENZA, p.getInizioIscrizione());
		return valore;
	}
	
}
