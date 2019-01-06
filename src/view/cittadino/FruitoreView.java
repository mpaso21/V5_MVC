package view.cittadino;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import entity.Fruitore;
import mylib.Constants;
import mylib.ConstantsCittadino;
import mylib.Data;
import mylib.InputDati;

/**
 * La classe FruitoreView rappresenta l'Input/Output.
 * Mantiene tutte le stampe relative agli oggetti di tipo
 * Fruitore. 
 * Mantiene inoltre i metodi che contengono input presi dall'utente.
 * @author Marika
 *
 */
public class FruitoreView {

	/**
	 * Metodo creaFruitoreInput permette di creare un fruitore, prendendo in input i valori 
	 * come nome e et� necessari per creare il fruitore.
	 * @return
	 */
	public Fruitore creaFruitoreInput(){
		String nome = mylib.InputDati.creaNome(); //ho spostato i metodi di cittadino in mylib ->MOVE METHOD
		int eta =  mylib.InputDati.creaEta(); 
		if(eta>=Constants.MAGGIORENNE){
			return new Fruitore(nome,eta);
		}else{
			return null;
		}
	}

	/**
	 * Metodo stampaFruitore fornisce a video una rappresentazione delle anagrafiche del fruitore.
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
/**
 * Metodo stampaIntestazioneFruitore stampa a video l'intestazione per un oggetto di tipo Fruitore.
 */
	private void stampaIntestazioneFruitore(){
		StringBuilder sb = new StringBuilder();
		sb.append(Constants.CORNICE + "\n")
		.append(String.format("%10s | %15s | %5s | %20s | %20s\n" , "ID", "NOME", "ETA", "ISCRIZIONE", "SCADENZA"))
		.append(Constants.CORNICE + "\n");
		System.out.println(sb.toString());
	}
	/**
	 * Metodo inserisciIdInput permette all'utente di inserire il proprio id, previa
	 * richiesta.
	 * @return
	 */
	public String inserisciIdInput(){ //POSSO CREARE UN CITTADINO VIEW E SPOSTARE METODO INSERISCI ID INPUT LI COSI CE L'HANNO ANHE FRUITORE E OPERATORE
		String id = InputDati.leggiStringaNonVuota(mylib.Constants.RICHIESTA_ID);
		return id;
	}


	public void stampaFruitoreNonTrovato() {
		System.out.println(ConstantsCittadino.ID_NON_PRESENTE);
		System.out.println(ConstantsCittadino.ISCRIZIONE);
	}

	public void stampaFruitoreScaduto(){
		System.out.println(ConstantsCittadino.ISCRIZIONE_TERMINI_SCADUTI);
	}

	public void stampaRinnovoFruitoreTraPoco(String d){
		System.out.println(ConstantsCittadino.ISCRIZIONE_RINNOVATA_TRA_POCO + d);
	}

	public void stampaNoFruitore() {
		System.out.println(ConstantsCittadino.NON_ISCRITTO);

	}

	public void stampaZeroFruitori(){
		System.out.println(ConstantsCittadino.ZERO_FRUITORI);
	}

	public void stampaIdFruitore(String id){
		System.out.println(ConstantsCittadino.ASSEGNO_ID + id);
	}
//
//	public void stampaPrestitiPerFruitore(Map<String,Integer> map){
//		System.out.println(Arrays.asList(map)); 
//	}

	public void stampaIscrizioneRinnovata() {
		System.out.println(ConstantsCittadino.ISCRIZIONE_RINNOVATA);
	}

	public boolean messaggioIdGiaPresente() {
		System.out.println("L'ID CHE HAI SCELTO � GIA PRESENTE!");
		return true;
	}



}


