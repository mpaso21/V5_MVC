package action;

import java.util.List;

import entity.Risorsa;

public interface RicercaInArchivio {
	
	public List<Risorsa> ricerca(String input, List<Risorsa> archivio);

}
