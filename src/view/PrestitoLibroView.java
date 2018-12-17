package view;

import entity.Libro;
import entity.Prestito;
import mylib.Data;

public  class PrestitoLibroView extends PrestitoView{
	
	/* **
	 * Metodo toStringLibro stampa a video l'oggetto di tipo Libro con il seguente
	 * format: LIBRO, TITOLO, AUTORE, DATA INIZIO PRESTITO E DATA FINE PRESTITO
	 * @return
	 */
	@Override
	protected String toString(Prestito p ){
		StringBuilder sb = new StringBuilder(  );
		sb.append(String.format("%8s | %15s | %15s | %20s | %20s | " , "LIBRO",  p.getR().getNome(), ((Libro) (p.getR())).getAutore(),Data.convertoData(p.getInizio_prestito()),Data.convertoData(p.getFine_prestito())));
		return sb.toString(); 
	}
	
	@Override
	protected String stampaPrestito(Prestito p){ //STAMPA IL PRESTITO CON LA RISORSA
		StringBuilder sb = new StringBuilder();
		if(p.getR() instanceof Libro){//POTREI PRENDE IL TIPO DI RISORSA E DIRE SE E' UGUALE A CONSTANTE LIBRO		
			if(p.getNumero_rinnovo() > 0){
			//VUOL DIRE CHE E' STATO RINNOVATO ALTRIMENTI NO
			//CATEGORIA TITOLO AUTORE DATA PRESTITO FINE PRESTITO
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
