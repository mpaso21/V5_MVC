package model.risorsa;

import java.util.List;
import action.AzioneRicercaPerRegista;
import action.RicercaInArchivio;
import entity.Risorsa;


/**
 * Classe FilmoModel rappresenta il modello nonchè la logica applicativa
 * relative agli oggetti di tipo Risorsa, più in specifico film.
 * 
 * @author Marika
 *
 */
public class FilmModel extends RisorsaModel{

	/**
	 * Metodo ricercaFilmRegista permette di ricercare all'interno dell'archivio
	 * un film inserendo come input il regista.
	 * @param inputAutore
	 */

	public List<Risorsa> ricercaFilmRegista(String inputRegista){
		RicercaInArchivio a = new AzioneRicercaPerRegista();
		return a.ricerca(inputRegista, getArchivio());		
	}

}
