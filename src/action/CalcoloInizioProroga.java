package action;

import java.time.LocalDateTime;

import entity.Prestito;

import mylib.ConstantsPrestito;

import mylib.Data;

public class CalcoloInizioProroga implements Calcolo {

private Prestito p;
	
	public CalcoloInizioProroga(Prestito p){
		this.p = p;
	}
	/**
	 * Metodo calcolo, calcola, data la data di scadenza del prestito,
	 *  l'intervallo di tempo entro cui è richiedibile la proroga del prestito.
	 *  In questo caso è possibile richiedere la proroga non prima dei 3 giorni precedenti
	 *  alla scadenza.
	 * @return
	 */
	public LocalDateTime calcolo(){
		//METODO CORRETTO: intervallo proroga non prima dei 3 giorni precedenti la scadenza
		//d = Data.cambiaGiorno(3, fine_prestito);
		//DIMOSTRAZIONE METODO: intervallo proroga non prima dei 3 minuti precedenti la scadenza
		int intervallo_proroga = p.getR().getCostantiCalcoloRisorsaByKey(ConstantsPrestito.INTERVALLO_RICHIESTA_PROROGA_KEY);
		LocalDateTime inizio_proroga = Data.cambiaMinuto(intervallo_proroga, p.getInizio_prestito());
		return inizio_proroga;
		
//		if(p.getR().getTipo().equalsIgnoreCase(ConstantsRisorsa.LIBRO)){
//			d  = Data.menoMinuto(ConstantsPrestito.INTERVALLO_RICHIESTA_PROROGA_LIBRO, p.getFine_prestito());
//			return d;
//		}
//		else{
//			d  = Data.menoMinuto(ConstantsPrestito.INTERVALLO_RICHIESTA_PROROGA_FILM, p.getFine_prestito());
//			return d;
//		}
	}
	
}
