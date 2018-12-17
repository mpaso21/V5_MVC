package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import entity.Film;
import entity.Fruitore;
import entity.Libro;
import entity.Prestito;
import entity.Risorsa;
import mylib.Constants;
import mylib.Data;
import mylib.InputDati;

public class PrestitoModel {

private List<Prestito> prestitiStorico;
	
	public PrestitoModel() {
		this.prestitiStorico= new ArrayList<>();
	}
	
	/**
	 * Metodo aggiungiPrestito permette di aggiungere un prestito all'elenco dei prestiti
	 * @param p
	 */
	public void aggiungiPrestito(Prestito p, List<Prestito> prestitiFruitore){
		prestitiStorico.add(p);
		prestitiFruitore.add(p);
	}
	/**
	 * Metodo controlloScadenzaPrestiti controlla per ogni prestito, contenuto
	 * nell'elenco di prestiti, la scadenza.Se il prestito è scaduto, viene
	 * rimosso dall'elenco.
	 * @param d
	 */
	public void controlloScadenzaPrestitiFruitore(LocalDateTime d, List<Prestito> prestitiFruitore) {
		if (!prestitiFruitore.isEmpty()) {

			List<Prestito> toRemove = new ArrayList<Prestito>();

			for (Prestito p : prestitiFruitore) {
				if (p.getFine_prestito().isBefore(d)) {
						toRemove.add(p);
						p.getR().setNumeroLicenze(p.getR().getNumeroLicenze()+1);
					//PRESTITO_SCADUTO (SE IL PRESTITO E' AFTER D VUOL DIRE CHE NON E' SCADUTO E NON FACCIO NIENTE)
				} 
			}
			prestitiFruitore.removeAll(toRemove);
		} 
	}
	
	public void controlloScadenzaPrestitiListaFruitori(LocalDateTime d, List<Fruitore> fruitori){
		for(Fruitore f: fruitori){
			controlloScadenzaPrestitiFruitore(d, f.getPrestiti());
		}
	}

	public List<Prestito> getArchivioStoricoPrestiti() {
		return prestitiStorico;
	}
	
	

	
	
}



