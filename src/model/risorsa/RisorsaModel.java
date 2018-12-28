package model.risorsa;

import java.util.ArrayList;
import java.util.List;
import action.AzioneRicercaPerGenere;
import action.AzioneRicercaPerTitolo;
import action.RicercaInArchivio;
import entity.Risorsa;

/**
 * Classe RisorsaModel rappresenta il modello nonchè la logica applicativa
 * relative agli oggetti di tipo Risorsa.
 * 
 * @author Marika
 *
 */
public  class RisorsaModel {

	private List<Risorsa> archivio;
	private List<Risorsa> archivioStoricoRisorse;
/**
 * Un oggetto di tipo RisorsaModel è costituito da una lista di risorse
 * rappresentanti l'archivio attuale, inoltre è costituito da una lista
 * di risorse rappresentanti l'archivio storico.
 */
	public RisorsaModel() {
		this.archivio = new ArrayList<>();
		this.archivioStoricoRisorse = new ArrayList<>();
	}


	/**
	 * Metodo aggiungiRisorsa mi permette di aggiungere una determinata risorsa all'archivio.
	 * @param r
	 */
	public void aggiungiRisorsa(Risorsa r){
		boolean aggiungo = false;

		if(archivio.isEmpty()){
			aggiungo = true;
		} else if(!esisteGia(r)){ 
			aggiungo = true;
		}

		if(aggiungo) {
			archivio.add(r);
			archivioStoricoRisorse.add(r);
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
	/**
	 * Metodo eliminaRisorsa permette di eliminare una risorsa dall'archivio.
	 */
	public void eliminaRisorsa (Risorsa f) {
		archivio.remove(f);
	}

	/**
	 * Metodo ricercaPerTitolo permette di ricercare all'interno dell'archivio
	 * una determinata risorsa inserendo come input il titolo.
	 * @param inputTitolo
	 * @return
	 */
	public List<Risorsa> ricercaPerTitolo(String inputTitolo){
		RicercaInArchivio a = new AzioneRicercaPerTitolo();
		return a.ricerca(inputTitolo, archivio);
	}

	/**
	 * Metodo ricercaPerGenere permette di ricercare all'interno dell'archivio
	 * una determinata risorsa inserendo come input il genere.
	 * @param inputTitolo
	 * @return
	 */
	public List<Risorsa> ricercaPerGenere(String inputGenere){   
		RicercaInArchivio a = new AzioneRicercaPerGenere();
		return a.ricerca(inputGenere, archivio);
	}

	public List<Risorsa> getArchivio() {
		return archivio;
	}

	public List<Risorsa> getArchivioStoricoRisorse(){
		return archivioStoricoRisorse;
	}

}



	

