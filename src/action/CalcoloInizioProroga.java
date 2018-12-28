package action;

import java.time.LocalDateTime;

import entity.Prestito;

import mylib.ConstantsPrestito;
import mylib.ConstantsRisorsa;
import mylib.Data;

public class CalcoloInizioProroga implements Calcolo {

private Prestito p;
	
	public CalcoloInizioProroga(Prestito p){
		this.p = p;
	}
	
	public LocalDateTime calcolo(){
		LocalDateTime d;
		//METODO CORRETTO: intervallo proroga non prima dei 3 giorni precedenti la scadenza
		//d = Data.cambiaGiorno(3, fine_prestito);
		//DIMOSTRAZIONE METODO: intervallo proroga non prima dei 3 minuti precedenti la scadenza
		if(p.getR().getTipo().equalsIgnoreCase(ConstantsRisorsa.LIBRO)){
			d  = Data.menoMinuto(ConstantsPrestito.INTERVALLO_RICHIESTA_PROROGA_LIBRO, p.getFine_prestito());
			return d;
		}
		else{
			d  = Data.menoMinuto(ConstantsPrestito.INTERVALLO_RICHIESTA_PROROGA_FILM, p.getFine_prestito());
			return d;
		}
	}
	
}
