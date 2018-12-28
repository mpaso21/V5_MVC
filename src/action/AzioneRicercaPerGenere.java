package action;

import java.util.ArrayList;
import java.util.List;

import entity.Risorsa;

public class AzioneRicercaPerGenere implements RicercaInArchivio{

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
