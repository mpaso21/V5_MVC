package view;


import java.util.List;

import entity.Fruitore;
import mylib.Constants;
import mylib.Data;
import mylib.InputDati;

public class FruitoreView {
	
	/**
	 * Metodo creaFruioreInput permette di creare un fruitore, prendendo in input i valori 
	 * come nome e età necessari per creare il fruitore.
	 * @return
	 */
	public Fruitore creaFruitoreInput(){
		String nome = mylib.InputDati.creaNome(); //ho spostato i metodi di cittadino in mylib ->MOVE METHOD
		int eta =  mylib.InputDati.leggiIntero(Constants.ETA_CITTADINO, Constants.LIMITE_MIN_ETA, Constants.LIMITE_MAX_ETA);  //public visibilita' a creaEta per farli vedere
		if(eta>=Constants.MAGGIORENNE){
			return new Fruitore(nome,eta);
		}else{
			return null;
		}
	}
	
	/**
	 * Metodo stampaFruitore fornisce una rappresentazione delle anagrafiche del fruitore.
	 */
	public  void stampaFruitore(Fruitore f){
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%10s | %15s | %5d | %20s | %20s\n", f.getId(),  f.getNome(), f.getEta(),Data.convertoData(f.getInizioIscrizione()),Data.convertoData(f.getScadenzaIscrizione() )));
		System.out.println( sb.toString()); 
	}
	
	/**
	 * Metodo stampaTuttiFruitori stampa a video tutti i fruitori presenti nell'elenco.
	 * @param fruitori
	 */
	public void stampaTuttiFruitori(List<Fruitore> fruitori){
		if(fruitori == null || fruitori.isEmpty()) {
			System.out.println(Constants.ERRORE_ARCHIVIO);
			return;
		}
		stampaIntestazioneFruitore();
		for(Fruitore l : fruitori) {
			this.stampaFruitore(l);;
		}
	}
	
	public void stampaIntestazioneFruitore(){
		StringBuilder sb = new StringBuilder();
		sb.append("-------------------------------------------------------------------------------------------\n")
		.append(String.format("%10s | %15s | %5s | %20s | %20s\n" , "ID", "NOME", "ETA", "ISCRIZIONE", "SCADENZA"))
		.append("-------------------------------------------------------------------------------------------\n");
		System.out.println(sb.toString());
	}
	/**
	 * Metodo inserisciIdInput permette all'utente di inserire il proprio id, previa
	 * richiesta.
	 * @return
	 */
	public String inserisciIdInput(){
		String id = InputDati.leggiStringaNonVuota(mylib.Constants.RICHIESTA_ID);
		return id;
	}
	
	public void stampaFruitoreNonTrovato() {
		System.out.println(Constants.ID_NON_PRESENTE);
		System.out.println(Constants.ISCRIZIONE);
	}
	
	public void stampaFruitoreScaduto(){
		System.out.println(Constants.ISCRIZIONE_TERMINI_SCADUTI);
	}
	
	public void stampaRinnovoFruitoreTraPoco(String d){
		System.out.println(Constants.ISCRIZIONE_RINNOVATA_TRA_POCO + d);
	}

	public void stampaNoFruitore() {
		System.out.println(Constants.NON_ISCRITTO);
	}
	
	public void stampaIdFruitore(String id){
		System.out.println(Constants.ASSEGNO_ID + id);
	}
}


