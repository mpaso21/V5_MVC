package action;

import java.time.LocalDateTime;

import entity.Prestito;

import mylib.ConstantsPrestito;
import mylib.ConstantsRisorsa;
import mylib.Data;

public class CalcoloFinePrestito  implements Calcolo{

	private Prestito p;
	
	public CalcoloFinePrestito(Prestito p){
		this.p = p;
	}

	/**
	 * Metodo calcolo_fine_prestito permette di calcolare la data di 
	 *  scadenza del prestito che è esattamente 30 giorni dopo la
	 *   data di inizio prestito.
	 * @return
	 */
	public LocalDateTime calcolo(){
		LocalDateTime fine_prestito;
		//METODO CORRETTO: scadenza 30 giorni dalla data di inizio prestito
		//fine_prestito = Data.cambiaGiorno(30, inizio_prestito);
		//DIMOSTRAZIONE METODO: scadenza 5 minuti dopo quella di iscrizione
		if(p.getR().getTipo().equalsIgnoreCase(ConstantsRisorsa.LIBRO)){ //HO SOSTITUITO INSTANCEOF
			fine_prestito = Data.cambiaMinuto(ConstantsPrestito.DURATA_MAX_PRESTITO_LIBRO, p.getInizio_prestito());
		}		
		else{//ELSE IF FILM
			fine_prestito = Data.cambiaMinuto(ConstantsPrestito.DURATA_MAX_PRESTITO_FILM, p.getInizio_prestito());
		}
		return fine_prestito;
	}

}
