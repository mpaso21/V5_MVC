package view.risorsa;


import java.util.List;
import entity.Risorsa;
import mylib.Constants;
import mylib.ConstantsRisorsa;
import mylib.InputDati;

/**
 * La classe RisorsaView rappresenta l'Input/Output.
 * Mantiene tutte le stampe relative agli oggetti di tipo
 * Risorsa. 
 * Mantiene inoltre i metodi che contengono input presi dall'utente.
 * @author Marika
 *
 */
public abstract class RisorsaView {

	/**
	 * Metodo crea permette la creazione di una determinata risorsa prendendo
	 * input dall'utente.
	 * @return
	 */
	
	//METODI CREATI ASTRATTI PERCHE HANNO UNA DIVERSA IMPLEMENTAZIONE A SECONDA DEL TIPO 
	//DI RISORSA UTLIZZATO
	public abstract Risorsa crea(); 
	/**
	 * Metodo stampa fornisce una rappresentazione dell'oggetto
	 * di tipo Risorsa sottoforma di Stringa.
	 * @param r
	 * @return
	 */
	protected abstract String stampa(Risorsa r);
	/**
	 * Metodo stampaCategoria mostra la categoria specifica della risorsa in
	 * questione.
	 */
	public abstract void stampaCategoria();

	/**	
	 * Metodo selezionaRisorsa permette di selezionare una determinata risorsa presente
	 * in archivio.
	 * @param c
	 * @return
	 */	
	//MOVE METHOD DA METODO RIMUOVI PRECEDENTE
	//PARTE DEL METODO RIMUOVI. LA SELEZIONE MESSA NELLA VIEW COME ANCHE LA STAMPA.
	public Risorsa selezionaRisorsa(List<Risorsa> archivio){
		if(archivio.isEmpty()){
			return null;
		}
		this.stampaTutti(archivio);
		int valore = InputDati.leggiIntero(ConstantsRisorsa.NUM_RISORSA, ConstantsRisorsa.INIZIO,archivio.size());
		return archivio.get(valore -1);							
	}



	/**
	 * Metodo stampaTutti � la mia vera stampa.
	 * Metodo stampaTutti fornisce a video una rappresentazione della lista passata
	 * per parametro. Se � vuota, stampa errore.
	 * @param film
	 */
	public void stampaTutti(List<Risorsa> archivio) {
		StringBuilder sb = new StringBuilder();
		if(archivio == null || archivio.isEmpty()) {
			stampaErroreArchivio();
		}
		else{
			sb.append(intestazione());
			String stringa = this.stampaConNumeri(archivio);
			sb.append(stringa);
			System.out.println(sb.toString());
		}
	}
	
	private String intestazione(){
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%16s | %15s | %15s | %10s | %20s | %10s | %10s | %5s |\n" ,"TITOLO", "AUTORE/REGISTA", "PAGINE/DURATA", "ANNO", "CASA", "LINGUA", "GENERE", "LICENZE"));
		return sb.toString(); 
	}
	
	/**	
	 * Metodo stampaConNumeri fornisce una rappresentazione sotto forma
	 * di elenco puntato della Lista passata come parametro.
	 * Utile per il metodo stampaTutti.
	 * passato come parametro.
	 * @param arr
	 * @return
	 */
	private String stampaConNumeri(List<Risorsa> arr){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < arr.size(); i++){
			sb.append(i+1 + " ");
			sb.append(stampa(arr.get(i)));
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * Metodo stampaRisorseTrovate fornisce a video una rappresentazione della 
	 * lista passata per parametro. Se � vuota, stampa nessuna risorsa trovate.
	 * @param film
	 */
	public void stampaRisorseTrovate(List<Risorsa> risorseTrovate){
		StringBuilder sb = new StringBuilder();
		if(risorseTrovate == null || risorseTrovate.isEmpty()) {
			System.out.println(ConstantsRisorsa.RISORSA_NON_TROVATA);
		}
		else{
			System.out.println(ConstantsRisorsa.ELENCO_RISORSE_TROVATE);
			String stringa = stampaConNumeri(risorseTrovate);
			sb.append(stringa);
			System.out.println(sb.toString());
		}
	}
	

	public void stampaErroreArchivio(){
		System.out.println(Constants.ERRORE_ARCHIVIO);
	}

	public void stampaRisorsaCopieEsaurite(Risorsa r){
		System.out.println(r.getNome() + " " +ConstantsRisorsa.COPIE_ESAURITE);
	}

}
