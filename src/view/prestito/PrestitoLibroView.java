package view.prestito;

import entity.Libro;
import entity.Prestito;

import mylib.ConstantsRisorsa;
import mylib.Data;


/**
 * La classe PrestitoLibroView rappresenta l'Input/Output.
 * Mantiene tutte le stampe relative ai prestiti degli oggetti di tipo
 * Risorsa, più in specifico libri. 
 * Mantiene inoltre i metodi che contengono input presi dall'utente.
 * @author Marika
 *
 */
public  class PrestitoLibroView extends PrestitoView{

	/**
	 * Metodo toString Fornisce una rappresentazione sotto forma di stringa
	 * del prestitoLibro (contiene tutte le sue proprietà).
	 * Utile per il metodo stampaPrestito.
	 * @param p
	 * @return
	 */

	protected String toString(Prestito p ){
		StringBuilder sb = new StringBuilder(  );
		sb.append(String.format("%8s | %15s | %15s | %20s | %20s | " , "LIBRO",  p.getR().getNome(), ((Libro) (p.getR())).getAutore(),Data.convertoData(p.getInizio_prestito()),Data.convertoData(p.getFine_prestito())));
		return sb.toString(); 
	}

	/**
	 * Metodo stampaPrestito fornisce una rappresentazione sotto forma di stringa
	 * del prestitoLibro (contiene tutte le sue proprietà)e inoltre contiene anche il valore
	 * della proroga.
	 * @param p
	 * @return
	 */
	@Override
	protected String stampaPrestito(Prestito p){ 
		StringBuilder sb = new StringBuilder();
		if(p.getR().getTipo().equalsIgnoreCase(ConstantsRisorsa.LIBRO)){//TOLTO INSTANCE OF 	
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
