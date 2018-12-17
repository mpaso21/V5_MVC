package view;

import entity.Film;
import entity.Libro;
import entity.Prestito;
import mylib.Data;

public class PrestitoFilmView extends PrestitoView {

	/**
	 * Metodo toStringFilm stampa a video l'oggetto di tipo Film con il seguente
	 * format: FILM, TITOLO, REGISTA, DATA INIZIO PRESTITO E DATA FINE PRESTITO
	 * @return
	 */
	@Override
	protected String toString(Prestito p ){
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%8s | %15s | %15s | %20s | %20s | " , "FILM",  p.getR().getNome(), ((Film) (p.getR())).getRegista(),Data.convertoData(p.getInizio_prestito()),Data.convertoData(p.getFine_prestito())));
		return sb.toString(); 
	}
	
	@Override
	protected String stampaPrestito(Prestito p){ //STAMPA IL PRESTITO CON LA RISORSA
		StringBuilder sb = new StringBuilder();
		if(p.getR() instanceof Film){		
			
				if(p.getNumero_rinnovo() > 0){
				sb.append(String.format("%s", toString(p)));
				sb.append("SI");
				}
				else{
				sb.append(String.format("%s", toString(p)));
				sb.append("NO");
				}
			
		}
		return sb.toString();
	}
	
}
