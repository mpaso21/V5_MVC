package model.cittadino;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import entity.Fruitore;

import entity.Prestito;

import mylib.Constants;
import mylib.ConstantsCittadino;
import mylib.Data;

/**
 * Metodo FruitoreModel rappresenta il modello nonchè la logica applicativa
 * relative agli oggetti di tipo Fruitore.
 * @author Marika
 *
 */
public class FruitoreModel {
	private List<Fruitore> fruitori;
	private List<Fruitore> storicoFruitori;


/**
 * Metodo FruitoreModel è costituito da una lista attuali di fruitori, da un archivio
 * storico di fruitori e da una lista di prestiti.
 */
	public FruitoreModel() {
		fruitori = new ArrayList<>();
		storicoFruitori = new ArrayList<>();
	
	}

	/**
	 * Il metodo aggiungiFruitore permette l'aggiunta di un fruitore all'elenco dei fruitori, 
	 * assegnando al fruitore l'id.
	 * 
	 * @param f
	 */
	public void aggiungiFruitore(Fruitore f){ //QUI ASSOCIO AL FRUITORE IL SUO ID (NON ALLA CREAZIONE).
		String id;
		boolean flag = false;
		do{
			id = Integer.toString(((int)(Math.random()*Constants.RANDOM)));
			flag = false;
			for(Fruitore fr : fruitori){
				if(fr.getId().equals(id)) {//controllo che l'id creato non sia già assegnato a un fruitore
					flag = true;
					break;
				}
			}
		}while(flag);
		f.setId(id);//ID UNIVOCO PER IDENTIFICARE IL FRUITORE
		fruitori.add(f);
		storicoFruitori.add(f);
	}

	/**
	 * Il metodo rimozioneFruitore permette la rimozione di un fruitore dall'elenco dei fruitori.
	 * @param f
	 */
	public void rimozioneFruitore(Fruitore f){

		if(fruitori!=null && !fruitori.isEmpty()){//IS EMPTY RITORNA TRUE SE L'ELENCO E' VUOTO
			fruitori.remove(f);  				//NULL-> SE ELENCO NON è STATO INIZIALIZZATO
		}
	}

	/**
	 *Il metodo controlloScadenza permette di controllare, per ogni fruitore, la data di scadenza 
	 *dell'iscrizione. Se la data è scaduta, il fruitore viene rimosso dall'elenco dei fruitori.
	 * @param d
	 */
	public void controlloScadenza(LocalDateTime d){
		if( !fruitori.isEmpty()){//d data corrente in cui controllo

			for(int i = fruitori.size()-1; i>=0; i--){//se la data scadenza è prima della data corrente -> data scadenza già scaduta e quindi  il fruitore viene eliminato dall'elenco
				this.controlloScadenza(fruitori.get(i)); //specie extract method
			}

		} 
	}
/**
 * Metodo controlloScadenza necessario per il funzionamento del metodo controlloScadenza.
 * @param f
 * @return
 */
	public boolean controlloScadenza(Fruitore f) {//dovrà essere richiamato in controllloScadenza per tutti 
		LocalDateTime la = LocalDateTime.now();//METODO CONTROLLO SCADENZA PER TUTTI MODIFICATO
		if (la.isAfter(f.getScadenzaIscrizione())) {
			this.rimozioneFruitore(f);
			return true;
		}
		return false;
	}


	/**
	 * Il metodo controlloPresenzaFruitore controlla che l'id inserito dal fruitore sia presente nell'elenco dei fruitori.
	 * 	Questo metodo è utilizzato ogni volta che il fruitore seleziona un'opzione dal menù.
	 * @return
	 */
	public Fruitore controlloPresenzaFruitore(String valore){
		if(!fruitori.isEmpty()){
			for(Fruitore f: fruitori){
				if(valore.equals(f.getId())){
					return f;
				}
			}
		}
		return null;
	}


	/**	
	 * Il Metodo rinnovoFruitore permette di rinnovare l' iscrizione di un determinato fruitore,
	 * ovviamente se la richiesta di rinnovo è effettuata prima della scadenza dell'iscrizione e dopo
	 * un certo numero di giorni antecedenti la scadenza.
	 * @param f
	 */
	public boolean rinnovoFruitore(Fruitore f){
		LocalDateTime la = LocalDateTime.now();
		if (la.isBefore(f.getScadenzaIscrizione()) && la.isAfter(Data.menoMinuto(ConstantsCittadino.NUM_TEMPO_PRIMA_RINNOVO,f.getScadenzaIscrizione()))) {
			f.setInizioIscrizione(la);
			f.setScadenzaIscrizione(Data.cambiaMinuto(ConstantsCittadino.NUM_SCADENZA, la));
			return true;
		} 
		return false;
	}
/**
 * Metodo tempoRinnovoFruitoe permette di calcolare il tempo che il fruitore ha a disposizione
 * per rinnovare la sua iscrizione.
 * @param f
 * @return
 */
	public LocalDateTime tempoRinnovoFruitore(Fruitore f) {
		LocalDateTime la = LocalDateTime.now();
		if (la.isBefore(Data.menoMinuto(ConstantsCittadino.NUM_TEMPO_PRIMA_RINNOVO,f.getScadenzaIscrizione()))) { //è possibile rinnovare da calcoloTerminiPrescritti in poi
			return Data.menoMinuto(ConstantsCittadino.NUM_TEMPO_PRIMA_RINNOVO,f.getScadenzaIscrizione());
		}
		return null;
	}
//MAI STATO CHIAMATO
///**
// * Metodo crea permette di creare un determinato prestito e di aggiungerlo ai prestiti del fruitore.
// * @param r
// * @param f
// * @return
// */
//	public Prestito crea(Risorsa r, Fruitore f){
//		Prestito p = new Prestito(r,f.getNome());
//		r.setNumeroLicenze(r.getNumeroLicenze()-1);
//		return p;
//	}

	public List<Fruitore> getStoricoFruitori() {
		return storicoFruitori;
	}
	
	public List<Fruitore> getFruitori() {
		return fruitori;
	}

	/**
	 * Metodo prestitiPerFruitore restituisce un HashMap
	 * contenente il nome fruitore e il suo id e il conto dei 
	 * prestiti effettuati nell'anno solare.
	 * @return
	 */
	public  HashMap<String, Integer> prestitiPerFruitore(){
		String s;
		int conto = 0;
		HashMap<String, Integer> array = new HashMap<>();
		if(!storicoFruitori.isEmpty()){
			for(Fruitore f: storicoFruitori){
				for(Prestito p: f.getPrestitiStoriciMiei()){
					if(p.getInizio_prestito().getYear() ==LocalDateTime.now().getYear()){
						conto++;
					}
				}
				s  =  f.getId()+ " " + f.getNome();
				array.put(s, conto);
				conto = 0;
			}	
		}
		return array;	
	}	


}
