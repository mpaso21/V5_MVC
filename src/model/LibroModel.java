package model;


import java.util.ArrayList;
import java.util.List;

import entity.Libro;
import entity.Risorsa;



public class LibroModel extends RisorsaModel {
	
	


	
		/**		
		 * Metodo ricercaRisorsaAutore permette di ricercare all'interno dell'archivio
		 * un libro inserendo come input l'autore.
		 * @param inputAutore
		 */
		
				public List<Risorsa> ricercaLibroAutore(String inputAutore){
				
					List<Risorsa> risorseTrovate = new ArrayList<>();
					for(int i=0; i<getArchivio().size(); i++){
						  if(inputAutore.equalsIgnoreCase(((Libro) (getArchivio().get(i))).getAutore())){
								risorseTrovate.add(getArchivio().get(i));
							
					}
				}
				return risorseTrovate;
				
				}
			
}
