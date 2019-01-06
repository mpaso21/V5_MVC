package view.prestito;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Prestito;
import mylib.Constants;
import mylib.ConstantsPrestito;
import mylib.Data;
import mylib.InputDati;

/**
 * La classe PrestitoView rappresenta l'Input/Output.
 * Mantiene tutte le stampe relative agli oggetti di tipo
 * Prestito. 
 * Mantiene inoltre i metodi che contengono input presi dall'utente.
 * @author Marika
 *
 */
public abstract class PrestitoView {

	/**
	 * Metodo stampaPrestito Fornisce una rappresentazione sotto forma di stringa
	 * del prestito (contiene tutte le sue propriet�) e inoltre contiene il valore
	 * dell'attributo proroga.
	 * @param p
	 * @return
	 */
	
	//METODO MESSO ASTRATTO PERCHE' CAMBIA L'IMPLEMENTAZIONE A SECONDA DEL TIPO
	//DI RISORSA PRESTATO
	protected abstract String stampaPrestito(Prestito p);

	/**	
	 *  Metodo seleziona permette di selezionare un determinato prestito
	 *  dall'elenco di prestiti. (in questo caso prestiti del fruitore).
	 *  Utile per il metodo prorogaPrestito
	 * @return
	 */
	public Prestito seleziona(List<Prestito> prestitiFruitore) {

		if(prestitiFruitore.isEmpty()){
			return null;
		}
		this.stampaTutti(prestitiFruitore);
		int valore = InputDati.leggiIntero(ConstantsPrestito.NUM_PRESTITO, 1, prestitiFruitore.size());
		return prestitiFruitore.get(valore - 1);

	}


	/**
	 * Metodo stampaTutti fornisce a video una rappresentazione di tutti  i prestiti
	 * contenuti nella Lista passata come parametro.
	 * @param film
	 */
	public void stampaTutti(List<Prestito> prestiti) {
		StringBuilder sb = new StringBuilder();
		if(prestiti == null || prestiti.isEmpty()) {
			stampaErroreVuoto();
		}
		else{
			sb.append(stringaStoricoIntestazione());
			String stringa = this.stampaConNumeri(prestiti);
			sb.append(stringa);
			System.out.println(sb.toString());
		}
	}

	/**
	 * Metodo stampa fornisce una rappresentazione del prestito.
	 * Ciascun prestito � rappresentato  dal nome fruitore  e da una sequenza di 
	 * propriet� proprie del prestito come la categoria di appartenenza,
	 * titolo, autore/regista, data inizio prestito, data fine prestito, proroga (si/no)
	 */
	public String stampa(Prestito p) {
		StringBuilder sb = new StringBuilder();
		sb.append( String.format(" %13s | ", p.getFruitore().getNome()) + stampaPrestito(p));
		return sb.toString();	
	}
	/**
	 * Metodo stringaStoricoIntestazione fornisce l'intestazione per 
	 * elenco dei prestiti sotto forma di Stringa.
	 * @return
	 */
	private String stringaStoricoIntestazione(){
		StringBuilder sb = new StringBuilder();
		sb.append(String.format(" %15s | %15s | %15s | %15s | %20s | %20s | %3s |\n" ,"FRUITORE: ", "TIPO", "TITOLO", "AUTORE/REGISTA", "INIZIO PRESTITO", "FINE PRESTITO", "PROROGA"));
		return sb.toString(); 
	}


	/**	
	 * Metodo stampaConNumeri fornisce  sotto forma di elenco puntato 
	 *una rappresentazione dell'ArrayList passato come parametro.
	 * @param prestiti
	 * @return
	 */
	private String stampaConNumeri(List<Prestito> prestiti){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < prestiti.size(); i++){
			sb.append(i+1 + " ");
			sb.append(stampa(prestiti.get(i)));
			sb.append("\n");
		}
		return sb.toString();
	}

	public void stampaNoPrestito() {
		System.out.println(ConstantsPrestito.NO_PRESTITO);
	}

	
	public void stampaErroreVuoto() {
		System.out.println(Constants.ERRORE_ARCHIVIO);

	}

	public void stampaSiRinnovo() {
		System.out.println(ConstantsPrestito.PRESTITO_RINNOVATO);
	}

	public void stampaRinnovoTraPoco(LocalDateTime la ){
		System.out.println(ConstantsPrestito.ISCRIZIONE_RINNOVATA_TRA_POCO_PRESTITO + Data.convertoData(la));
	}

	public void stampaNumPrestitiPerAnno(int valore){
		System.out.println(ConstantsPrestito.NUM_PRESTITI + valore);
	}


	public void stampaNumProroghePerAnno(int valore){
		System.out.println(ConstantsPrestito.NUM_PROROGHE + valore);
	}
	
	public void stampaPerRisorsaPiuPrestata(Map<String,Integer> map) {
		if(map.size() == 0){
			stampaZeroPrestiti();
		}
		else{
			System.out.println(ConstantsPrestito.RISORSE_PIU_PRESTATE + LocalDateTime.now().getYear() +  "\n");
			System.out.println(Arrays.asList(map));
		}	
	}
	
	public void superatoLimitePrestiti(){
		System.out.println(ConstantsPrestito.LIMITE_PRESTITO);
	}

	public void stampaZeroPrestiti() {
		System.out.println(ConstantsPrestito.PRESTITI_VUOTO);
		
	}

}
