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
	//RISORSA MODEL DOVREBBE "RISPECCHIARE" LA CLASSE ARCHIVIO DEL PROG.PRECEDENTE
	
	//HO TOLTO ATTRIBUTO NOME ARCHIVIO E HO CREATO L'OGGETTO ARCHIVIOSTORICORISORSE COME
	//ARRAYLIST DI RISORSE E NON COME OGGETTO.(QUESTO PERCHE' IL MODEL DI RISORSA E' UNICO,
	//NEL MVC NON POSSO AVERE PIU MODEL).
	//ARRAY TIPOLOGIE RISORSE SPOSTATO IN CONSTANTS RISORSA
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
	
	//METODO ELIMINA RISORSA CLASSE PRECEDENTE CONTENEVA LA SELEZIONE DA UTENTE + STAMPA
	//QUINDI SPEZZATO POICHE' LA STAMPA APPARTIENE ALLA VIEW COME ANCHE LA SELEZIONE, LA
	//RIMOZIONE COME LOGICA AL MODEL.
	//REFACTOR EXTRACT METHOD
	public void eliminaRisorsa (Risorsa f) {
		archivio.remove(f);
	}

	//METODO STAMPA ARCHIVIO SPOSTATO IN VIEW, ANCHE STAMPA CON NUMERI, ANCHE STAMPA RISORSE TROVATE
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
	//RICERCA LIBRO GENERE E FILM GENERE SONO DIVENTATE UN METODO UNICO A CAUSA DELLO
	//SPOSTAMENTO DELL'ATTRIBUTO GENERE NELLA SUPERCLASSE. 
	public List<Risorsa> ricercaPerGenere(String inputGenere){   
		RicercaInArchivio a = new AzioneRicercaPerGenere();
		return a.ricerca(inputGenere, archivio);
	}

	//RICERCA REGISTA E RICERCA AUTORE SPOSTATI IN FILM MODEL E LIBRO MODEL. REFACTOR -> MOVE METHOD
	public List<Risorsa> getArchivio() {
		return archivio;
	}

	public List<Risorsa> getArchivioStoricoRisorse(){
		return archivioStoricoRisorse;
	}

}



	

