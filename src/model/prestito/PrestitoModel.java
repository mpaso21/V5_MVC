package model.prestito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import entity.Prestito;
import entity.Risorsa;

/**
 * Classe PrestitoModel rappresenta il modello nonchè la logica applicativa
 * relative agli oggetti di tipo Prestito.
 * @author Marika
 *
 */
public class PrestitoModel {

	private List<Prestito> prestitiStorico; //ARCHIVIO STORICO DEI PRESTITI
/**
 * Un oggetto di tipo PrestitoModel è costituito da una lista di prestiti che
 * rappresentano i prestiti storici.
 */
	public PrestitoModel() {
		this.prestitiStorico= new ArrayList<>();
	}

	/**
	 * Metodo aggiungiPrestito permette di aggiungere un prestito all'elenco dei prestiti
	 * storici, ai prestiti dei fruitori e ai prestitiStoriciFruitori.
	 * @param p
	 */
	
	//UTILIZZATO SUI PRESTITI STORICI, COME SUI PRESTITI ATTUALI
	public void aggiungiPrestito(Prestito p, List<Prestito> prestitiFruitore, List <Prestito> prestitiStoriciFruitore){
		prestitiStorico.add(p);
		prestitiFruitore.add(p);
		prestitiStoriciFruitore.add(p);
	}
	
	/**
	 * Metodo prestitiPerAnno restituisce il numero di prestiti
	 * effettuati nell'anno solare.
	 * @return
	 */
	public int prestitiPerAnno(){
		int conto = 0;
		for(Prestito p: prestitiStorico){
			if(p.getInizio_prestito().getYear() == LocalDateTime.now().getYear()){
				conto = conto +1;
			}
		}
		return conto;
	}

	/**	
	 * Metodo proroghePerAnno restituisce il numero di proroghe
	 * effettuate nell'anno solare.
	 * @return
	 */
	public int proroghePerAnno(){ 
		int a = 0;
		for(Prestito p: prestitiStorico){
			if(p.getNumero_rinnovo()>0  && (p.getInizio_prestito().getYear() == LocalDateTime.now().getYear())){
				a = a+1;
			}
		}
		return a;
	}

	
	/**	
	 * Metodo risorsaPiuPrestataPerAnno restituisce la risorsa che è stata oggetto
	 * del maggior numero di prestiti per anno solare
	 * @return
	 */
		public Map <String,Integer> risorsaPiuPrestataPerAnno(){
			int conteggioTemp = 0;
			Risorsa risTemp;
			Map<String, Integer> ris = new HashMap<>();
			int max = 0;
	
			for( int j=0; j<prestitiStorico.size(); j++){
				conteggioTemp =0;
				risTemp = null; 								//DATA CORRENTE
				if(prestitiStorico.get(j).getInizio_prestito().getYear() == LocalDateTime.now().getYear() ){
					for(int i=0; i<prestitiStorico.size(); i++){
						if(prestitiStorico.get(i).getInizio_prestito().getYear() == LocalDateTime.now().getYear() ){
							if(prestitiStorico.get(j).getR().equals(prestitiStorico.get(i).getR())){
							conteggioTemp++;
							risTemp =prestitiStorico.get(j).getR() ;
							}
						}
					}
				}
				trovaMaxRisorsa(conteggioTemp, max, ris, risTemp);
			}
			return (HashMap<String, Integer>) ris; //RITORNO UN HASHMAP SE CE NE E' PIU' DI UNA
		}
		
		
	private void trovaMaxRisorsa(int conteggioTemp, int max, Map<String,Integer> ris, Risorsa risTemp){

		if(conteggioTemp > max) {
			max = conteggioTemp;
			ris.clear();
			String s =  risTemp.getTipo() + "->" + risTemp.getNome();
			ris.put(s, max);
		}//PUO'SUCCEDERE CHE SONO DUE LE RISORSE PIU PRESTATE NELL'ANNO
		else if (conteggioTemp == max){//NON RIPULISCO L'ARRAY E AGGIUNGO SOLO
			String s =  risTemp.getTipo() + "->" + risTemp.getNome();
			ris.put(s,conteggioTemp);
		}
	}
	
//	/**
//	 * Metodo controlloScadenzaPrestitiFruitore controlla per ogni prestito, contenuto
//	 * nell'elenco di prestiti dei fruitori, la scadenza.Se il prestito è scaduto, viene
//	 * rimosso dall'elenco.
//	 * @param d
//	 */
	//SPOSTATO IN FRUITORE
//	public void controlloScadenzaPrestitiFruitore(LocalDateTime d, List<Prestito> prestitiFruitore) {
//		if (!prestitiFruitore.isEmpty()) {
//				List<Prestito> toRemove = new ArrayList<Prestito>();
//				for (Prestito p : prestitiFruitore) {
//					if (p.getFine_prestito().isBefore(d)) {
//						toRemove.add(p);
//						p.getR().setNumeroLicenze(p.getR().getNumeroLicenze()+1);
//					} 
//				}
//				prestitiFruitore.removeAll(toRemove);
//			} 
//		}
//	/**
//	 * Metodo controlloScadenzaPrestitiListaFruitore controlla per ogni prestito, contenuto
//	 * nell'elenco di prestiti del fruitore, la scadenza.Se il prestito è scaduto, viene
//	 * rimosso dall'elenco. A differenza del metodo sopra, questo itera su tutti i fruitori.
//	 * @param d
//	 */
//	public void controlloScadenzaPrestitiListaFruitori(LocalDateTime d, List<Fruitore> fruitori){
//		for(Fruitore f: fruitori){
//			controlloScadenzaPrestitiFruitore(d, f.getPrestiti());
//		}
//	}
//	

	public List<Prestito> getArchivioStoricoPrestiti() {
		return prestitiStorico;
	}

}



