package view;

import java.time.LocalDateTime;
import java.util.List;

import entity.Film;
import entity.Libro;
import entity.Prestito;
import entity.Risorsa;
import mylib.Constants;
import mylib.Data;
import mylib.InputDati;

public abstract class PrestitoView {

	
	protected abstract String toString(Prestito p);
	
	protected abstract String stampaPrestito(Prestito p);	
	/**
	 * Metodo stampa fornisce a video l'intero elenco di prestiti.
	 * Ciascun prestito è rappresentato mediante una categoria di appartenenza,
	 * titolo, autore/regista, data inizio prestito, data fine prestito, proroga (si/no)
	 */
		public String stampa(Prestito p) {
			StringBuilder sb = new StringBuilder();
				sb.append( String.format(" %10s | ", p.getNomeFruitore()) + stampaPrestito(p));
			return sb.toString();	
		}
	/**
	 * Metodo stringaStoricoIntestazione stampa a video l'intestazione per 
	 * elenco dei prestiti	
	 * @return
	 */
		public String stringaStoricoIntestazione(){
		StringBuilder sb = new StringBuilder();
		sb.append(String.format(" %10s | %7s | %14s | %15s | %20s | %20s | %3s |\n" ,"FRUITORE: ", "CATEGORIA", "TITOLO", "AUTORE/REGISTA", "INIZIO PRESTITO", "FINE PRESTITO", "PROROGA"));
		return sb.toString(); 
	}
		
	public void stampaNoPrestito() {
		System.out.println(Constants.NO_PRESTITO);
	}
	
	public void stampaNoRinnovo(){
		System.out.println(Constants.RINNOVO_NON_POSSIBILE);
	}
	public void stampaErroreVuoto() {
		System.out.println(Constants.ERRORE_ARCHIVIO);
		
	}
	
	/**
	 * Metodo stampaTuttiFilm fornisce a video una rappresentazione di tutti i film
	 * presenti in archivio.
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
		 * Metodo stampaConNumeri stampa a video un'elenco puntato dell'ArrayList 
		 * passato come parametro.
		 * @param prestiti
		 * @return
		 */
		public String stampaConNumeri(List<Prestito> prestiti){
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < prestiti.size(); i++){
				sb.append(i+1 + " ");
				sb.append(stampa(prestiti.get(i)));//TO STRING IN BASE AL TIPO DI RISORSA
				sb.append("\n");
			}
			return sb.toString();
		}
	
	/**	
	 *  Metodo selezionaPrestito permette di selezionare un determinato prestito
	 *  dall'elenco di prestiti del fruitore.
	 * @return
	 */
	public Prestito seleziona(List<Prestito> prestitiFruitore) {

		if(prestitiFruitore.isEmpty()){
			return null;
		}
		this.stampaTutti(prestitiFruitore);
		int valore = InputDati.leggiIntero(Constants.NUM_PRESTITO, 1, prestitiFruitore.size());
		return prestitiFruitore.get(valore - 1);

	}

	public void stampaSiRinnovo() {
		System.out.println(Constants.PRESTITO_RINNOVATO);
	}
	
	public void stampaRinnovoTraPoco(LocalDateTime la ){
		System.out.println(Constants.ISCRIZIONE_RINNOVATA_TRA_POCO_PRESTITO + Data.convertoData(la));
	}

}
