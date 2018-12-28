package model.risorsa;


import java.util.List;
import action.AzioneRicercaPerAutore;
import action.RicercaInArchivio;
import entity.Risorsa;


/**
 * Classe LibroModel rappresenta il modello nonchè la logica applicativa
 * relative agli oggetti di tipo Risorsa, più in specifico libri.
 * 
 * @author Marika
 *
 */
public class LibroModel extends RisorsaModel {


	/**		
	 * Metodo ricercaLibroAutore permette di ricercare all'interno dell'archivio
	 * un libro inserendo come input l'autore.
	 * @param inputAutore
	 */

	public List<Risorsa> ricercaLibroAutore(String inputAutore){
		RicercaInArchivio a = new AzioneRicercaPerAutore();
		return a.ricerca(inputAutore, getArchivio());	

	}

}
