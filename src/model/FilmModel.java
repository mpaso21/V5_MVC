package model;

import java.util.ArrayList;
import java.util.List;

import entity.Film;
import entity.Libro;
import entity.Risorsa;



public class FilmModel extends RisorsaModel{
	
		/**
		 * Metodo ricercaRisorsaAutore permette di ricercare all'interno dell'archivio
		 * un libro inserendo come input l'autore.
		 * @param inputAutore
		 */
		
				public List<Risorsa> ricercaFilmRegista(String inputRegista){
					
					List<Risorsa> risorseTrovate = new ArrayList<>();
					for(int i=0; i<getArchivio().size(); i++){
						  if(inputRegista.equalsIgnoreCase(((Film) getArchivio().get(i)).getRegista())){
								risorseTrovate.add((Film) getArchivio().get(i));
							
					}
				}
				return risorseTrovate;
				
				}
				
	

}
