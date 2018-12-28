package action;

import java.util.ArrayList;
import java.util.List;

import entity.Libro;
import entity.Risorsa;

public class AzioneRicercaPerAutore implements RicercaInArchivio{

	@Override
	public List<Risorsa> ricerca(String input, List<Risorsa> archivio) {
		List<Risorsa> risorseTrovate = new ArrayList<>();
		for(int i=0; i<archivio.size(); i++){
			if(input.equalsIgnoreCase(((Libro) (archivio.get(i))).getAutore())){
				risorseTrovate.add(archivio.get(i));

			}
		}
		return risorseTrovate;
	}

}
