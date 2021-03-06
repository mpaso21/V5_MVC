package action;

import java.util.ArrayList;
import java.util.List;

import entity.Film;
import entity.Risorsa;

public class AzioneRicercaPerRegista implements RicercaInArchivio {
	/**			
	 * Metodo ricercaRisorsaRegista permette di ricercare all'interno dell'archivio
	 * un film inserendo come input il regista.
	 * @param inputRegista
	 */
	@Override
	public List<Risorsa> ricerca(String input, List<Risorsa> archivio) {
		List<Risorsa> risorseTrovate = new ArrayList<>();
		for(int i=0; i< archivio.size(); i++){
			if(input.equalsIgnoreCase(((Film) (archivio.get(i))).getRegista())){
				risorseTrovate.add((Film) archivio.get(i));			
			}
		}
		return risorseTrovate;
	}

}
