package action;

import java.time.LocalDateTime;

import controller.Controller;

import controller.entitycontroller.PrestitoEntityController;
import entity.Fruitore;
import entity.Prestito;
import mylib.ConstantsRisorsa;
import mylib.InputDati;

public class AzioneRichiestaProroga {

	private Fruitore f;
//	private PrestitoEntityController libroPrestitoController;
//	private PrestitoEntityController filmPrestitoController;
	private Controller main;
	
	public AzioneRichiestaProroga(Fruitore f, Controller main){
		this.f = f;
		this.main = main;
//		this.libroPrestitoController = libroPrestitoController;
//		this.filmPrestitoController = filmPrestitoController;
	}


	/**
	 * Metodo prorogaPrestito permette ad un fruitore, di prorogare la scadenza di un
	 * determinato prestito. Tale proroga può essere richiesta una sola volta.
	 * @param la
	 */	
	//METODO UTILIZZATO SEMPRE
	public void prorogaConInput(LocalDateTime la){
		int selezione = InputDati.selezionaElementoDaArray(ConstantsRisorsa.TIPOLOGIE_RISORSE);
		String selezioneKey = ConstantsRisorsa.TIPOLOGIE_RISORSE[selezione];
		
		f.controlloScadenzaPrestitiFruitore();
		
		Prestito p = main.getPrestitoControllerByKey(selezioneKey).selezionaPrestitoConFiltro(selezioneKey,f.getPrestiti());
		proroga(p, selezioneKey, la);
//		}
//		else{
//			p = filmPrestitoController.selezionaPrestitoConFiltro(ConstantsRisorsa.FILM,f.getPrestiti());
//			verificaProroga(la, p, filmPrestitoController);
//		}
	}
	
	//METODO UTILIZZATO NEL TEST
	public void proroga(Prestito p,String selezioneKey, LocalDateTime la){
		//verificaProroga(la, p, main.getPrestitoControllerByKey(selezioneKey));
		
		PrestitoEntityController controller = main.getPrestitoControllerByKey(selezioneKey);
		if(p == null){//SE IL FRUITORE NON HA I PRESTITI
			controller.getPrestitoView().stampaErroreVuoto();
			return;
		}
		if(p.rinnovo()){ 
			calcoloProroga(la, p, controller);
		}
		else{
			controller.getPrestitoView().stampaNoPrestito();
		}
	}
	
//	private void verificaProroga(LocalDateTime la, Prestito p, PrestitoEntityController controller) { 
//		if(p == null){//SE IL FRUITORE NON HA I PRESTITI
//			controller.getPrestitoView().stampaErroreVuoto();
//			return;
//		}
//		if(p.rinnovo()){ 
//			calcoloProroga(la, p, controller);
//		}
//		else{
//			controller.getPrestitoView().stampaNoPrestito();
//		}
//	}
	
	private void calcoloProroga(LocalDateTime la, Prestito p, PrestitoEntityController controller){
		
		if (la.isBefore(p.getFine_prestito()) && la.isAfter(calcoloTerminiPrescrittiProroga(p))) {
			p.setInizio_prestito(la);
			p.setFine_prestito(p.calcolo_fine_prestito());
			p.setNumero_rinnovo(p.getNumero_rinnovo()+1);
			controller.getPrestitoView().stampaSiRinnovo();

		} else if (!la.isAfter(calcoloTerminiPrescrittiProroga(p))) { 
			LocalDateTime d = calcoloTerminiPrescrittiProroga(p);
			controller.getPrestitoView().stampaRinnovoTraPoco(d);
		}
	}


	private LocalDateTime calcoloTerminiPrescrittiProroga(Prestito p){
		Calcolo calcoloProroga = new CalcoloInizioProroga(p);
		return calcoloProroga.calcolo();
	}
}
