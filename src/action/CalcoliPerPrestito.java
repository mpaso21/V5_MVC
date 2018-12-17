package action;

import java.time.LocalDateTime;

import entity.Film;
import entity.Libro;
import entity.Prestito;
import mylib.Constants;
import mylib.Data;

public class CalcoliPerPrestito {

	private Prestito p;
	public CalcoliPerPrestito(Prestito p){
		this.p = p;
	}
	
	/**
	 * Metodo calcolo_fine_prestito permette di calcolare la data di 
	 *  scadenza del prestito che è esattamente 30 giorni dopo la
	 *   data di inizio prestito.
	 * @return
	 */
	public LocalDateTime calcolo_fine_prestito(){
		LocalDateTime fine_prestito;
		//METODO CORRETTO: scadenza 30 giorni dalla data di inizio prestito
		//fine_prestito = Data.cambiaGiorno(30, inizio_prestito);
		//DIMOSTRAZIONE METODO: scadenza 5 minuti dopo quella di iscrizione
		if(p.getR().getTipo().equalsIgnoreCase(Constants.LIBRO)){ //HO SOSTITUITO INSTANCEOF
			fine_prestito = Data.cambiaMinuto(Libro.DURATA_MAX_PRESTITO, p.getInizio_prestito());
		}		
		else{//ELSE IF FILM
			fine_prestito = Data.cambiaMinuto(Film.DURATA_MAX_PRESTITO, p.getInizio_prestito());
		}
		return fine_prestito;
	}
	
	/**
	 * Metodo calcoloTerminiPrescrittiProroga calcola, data la data di scadenza del prestito,
	 *  l'intervallo di tempo entro cui è richiedibile la proroga del prestito.
	 *  In questo caso è possibile richiedere la proroga non prima dei 3 giorni precedenti
	 *  alla scadenza.
	 * @return
	 */
	public LocalDateTime calcoloTerminiPrescrittiProroga(){
		LocalDateTime d;
		//METODO CORRETTO: intervallo proroga non prima dei 3 giorni precedenti la scadenza
		//d = Data.cambiaGiorno(3, fine_prestito);
		//DIMOSTRAZIONE METODO: intervallo proroga non prima dei 3 minuti precedenti la scadenza
		if(p.getR().getTipo().equalsIgnoreCase(Constants.LIBRO)){
			d  = Data.menoMinuto(Libro.INTERVALLO_RICHIESTA_PROROGA, p.getFine_prestito());
			return d;
		}
		else{
			d  = Data.menoMinuto(Film.INTERVALLO_RICHIESTA_PROROGA, p.getFine_prestito());
			return d;
		}
	}
}
