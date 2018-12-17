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


public class FruitoreModel {
	private List<Fruitore> fruitori;
	private ArrayList<Prestito> prestiti;
	
	public FruitoreModel() {
		this.fruitori = new ArrayList<>();
		prestiti = new ArrayList<>();	
		}
	
	/**
	 * Il metodo iscrizioneFruitore permette l'aggiunta di un fruitore all'elenco dei fruitori, 
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
	
	public boolean controlloScadenza(Fruitore f) {//dovrà essere richiamato in controllloScadenza per tutti 
		LocalDateTime la = LocalDateTime.now();//METODO CONTROLLO SCADENZA PER TUTTI MODIFICATO
		if (la.isAfter(f.getScadenzaIscrizione())) {
			this.rimozioneFruitore(f);
			return true;
		}
		return false;
	}
	
	
	/**
	 * Il metodo selezionaFruitore controlla che l'id inserito dal fruitore sia presente nell'elenco dei fruitori.
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

	public List<Fruitore> getFruitori() {
		return fruitori;
	}

	public void setFruitori(List<Fruitore> fruitori) {
		this.fruitori = fruitori;
	}
/**	
 * Il Metodo rinnovoFruitore permette di rinnovare l' iscrizione di un determinato fruitore.
 * @param f
 */
	public boolean rinnovoFruitore(Fruitore f){
		LocalDateTime la = LocalDateTime.now();
		if (la.isBefore(f.getScadenzaIscrizione()) && la.isAfter(Data.menoMinuto(Constants.NUM_TEMPO_PRIMA_RINNOVO,f.getScadenzaIscrizione()))) {
			f.setInizioIscrizione(la);
			f.setScadenzaIscrizione(Data.cambiaMinuto(Constants.NUM_SCADENZA, la));
			return true;
		} 
		return false;
	}
	
	public LocalDateTime tempoRinnovoFruitore(Fruitore f) {
		LocalDateTime la = LocalDateTime.now();
		if (la.isBefore(Data.menoMinuto(Constants.NUM_TEMPO_PRIMA_RINNOVO,f.getScadenzaIscrizione()))) { //è possibile rinnovare da calcoloTerminiPrescritti in poi
			return Data.menoMinuto(Constants.NUM_TEMPO_PRIMA_RINNOVO,f.getScadenzaIscrizione());
		}
		return null;
	}

	
	public Prestito crea(Risorsa r, Fruitore f){
		Prestito p = new Prestito(r,f.getNome());
		prestiti.add(p);
		r.setNumeroLicenze(r.getNumeroLicenze()-1);
		return p;
	}

	
		
}
