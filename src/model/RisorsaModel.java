package model;

import java.util.ArrayList;
import java.util.List;

import entity.Libro;
import entity.Risorsa;


public  class RisorsaModel {
	
	private List<Risorsa> archivio;
	

	public RisorsaModel() {
		this.archivio = new ArrayList<>();
		
	}
	
	public int controlloSizeArchivio(){
		return archivio.size();
	}
	
	public List<Risorsa> getArchivio() {
		return archivio;
	}

	public void aggiungiRisorsa(Risorsa r){
		boolean aggiungo = false;

		if(archivio.isEmpty()){
			aggiungo = true;
		} else if(!esisteGia(r)){ 
				aggiungo = true;
		}
		
		if(aggiungo) {
			archivio.add(r);
			
		}
	}


	/**
	 * Metodo esisteGia restituisce un valore di verità se la risorsa è
	 *  già presente nell'archivio.
	 * @param r
	 * @return
	 */
	public boolean esisteGia (Risorsa r) {
		for (Risorsa c: archivio)
		{
			if(c.equals(r)) {
				return true;			
			}
		}
		return false;  
	}
	
	public void eliminaRisorsa (Risorsa f) {
		archivio.remove(f);
	}
	
	public List<Risorsa> ricercaPerTitolo(String inputTitolo){
		List<Risorsa> risorseTrovate = new ArrayList<>();
		for(int i=0; i<archivio.size(); i++){
			if(inputTitolo.equalsIgnoreCase(archivio.get(i).getNome())){ 
					risorseTrovate.add(archivio.get(i));									
			}
		}
		return risorseTrovate;
	}
	
	/**
	 * Metodo ricercaLibroGenere permette di ricercare all'interno dell'archivio
	 * un libro inserendo come input il genere.		
	 * @param genere
	 */

	public List<Risorsa> ricercaPerGenere(String genere){   
	
		List<Risorsa> risorseTrovate = new ArrayList<>();
		for(int i=0; i<getArchivio().size(); i++){
			  if(genere.equalsIgnoreCase(archivio.get(i).getGenere())){
				  risorseTrovate.add(archivio.get(i));
			
			  }
		}
		return risorseTrovate;
	
	}
	
	
		
}



	

