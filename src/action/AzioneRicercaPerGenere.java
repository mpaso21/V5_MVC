package action;

import java.util.ArrayList;
import java.util.List;

import entity.Risorsa;

public class AzioneRicercaPerGenere implements RicercaInArchivio{
	/**
	 * Metodo ricerca permette di ricercare all'interno dell'archivio
	 * una risorsa inserendo come input il genere.		
	 * @param genere
	 */
	@Override
	public List<Risorsa> ricerca(String input, List<Risorsa> archivio) {
		List<Risorsa> risorseTrovate = new ArrayList<>();
		for(int i=0; i<archivio.size(); i++){
			if(input.equalsIgnoreCase(archivio.get(i).getGenere())){
				risorseTrovate.add(archivio.get(i));

			}
		}
		return risorseTrovate;
	}

}
