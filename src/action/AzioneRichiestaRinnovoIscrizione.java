package action;

import java.time.LocalDateTime;

import controller.Controller;

import entity.Fruitore;
import model.cittadino.FruitoreModel;
import mylib.ConstantsCittadino;
import mylib.Data;
import view.cittadino.FruitoreView;


public class AzioneRichiestaRinnovoIscrizione {


	private Fruitore f;
	private Controller main;
	
	public AzioneRichiestaRinnovoIscrizione(Fruitore f, Controller main) {
		this.f = f;
		this.main = main;
	}
	/**
	 * Metodo rinnovo controlla se � possibile rinnovare l'iscrizione del fruitore.
	 * Per poter rinnovare l'iscrizione del fruitore � necessario che la richiesta non avvenga prima dei 
	 * 10 giorni antecedenti la scandenza e non dopo la data di scadenza.
	 * @param la
	 * @param elenco
	 */
	public void rinnovo(Fruitore f){
		FruitoreModel model = main.getFruitore().getFruitoreModel();
		FruitoreView view = main.getFruitore().getFruitoreView();
		
		
		if(Data.controlloDataNelPassato(f.getScadenzaIscrizione())) { //EXTRACT METHOD + MOVE METHOD
			model.rimozioneFruitore(f);
			view.stampaFruitoreScaduto();
		} 
		else {
			controlloRinnovo(view);
//			LocalDateTime tempo = model.tempoRinnovoFruitore(f);
//			if( tempo == null ) {
//				model.rinnovoFruitore(f);
//				view.stampaIscrizioneRinnovata();
//			} 
//			else {
//				view.stampaRinnovoFruitoreTraPoco(Data.convertoData(tempo));
//			}
		}
	}
	
	//IO SPOSTEREI DI QUI ANCHE TEMPO RINNOVO E RINNOVO
	private void controlloRinnovo(FruitoreView v){
		LocalDateTime tempo = calcoloTempoRinnovoFruitore(f);
		if( tempo == null ) {
			rinnovoFruitore(f);
			v.stampaIscrizioneRinnovata();
		} 
		else {
			v.stampaRinnovoFruitoreTraPoco(Data.convertoData(tempo));
		}
	}
	
	private LocalDateTime calcoloTempoRinnovoFruitore(Fruitore f) {
		LocalDateTime la = LocalDateTime.now();
		if (la.isBefore(Data.menoMinuto(ConstantsCittadino.NUM_TEMPO_PRIMA_RINNOVO,f.getScadenzaIscrizione()))) { //� possibile rinnovare da calcoloTerminiPrescritti in poi
			return Data.menoMinuto(ConstantsCittadino.NUM_TEMPO_PRIMA_RINNOVO,f.getScadenzaIscrizione());
		}
		return null;
	}
	
	private boolean rinnovoFruitore(Fruitore f){
		LocalDateTime la = LocalDateTime.now();
		if (la.isBefore(f.getScadenzaIscrizione()) && la.isAfter(Data.menoMinuto(ConstantsCittadino.NUM_TEMPO_PRIMA_RINNOVO,f.getScadenzaIscrizione()))) {
			f.setInizioIscrizione(la);
			f.setScadenzaIscrizione(Data.cambiaMinuto(ConstantsCittadino.NUM_SCADENZA, la));
			return true;
		} 
		return false;
	}

}
