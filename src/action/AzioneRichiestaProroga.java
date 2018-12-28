package action;

import java.time.LocalDateTime;
import controller.entitycontroller.PrestitoEntityController;
import entity.Fruitore;
import entity.Prestito;
import mylib.ConstantsRisorsa;
import mylib.InputDati;

public class AzioneRichiestaProroga {

	private Fruitore f;
	private PrestitoEntityController libroPrestitoController;
	private PrestitoEntityController filmPrestitoController;

	public AzioneRichiestaProroga(Fruitore f, PrestitoEntityController libroPrestitoController, 
									PrestitoEntityController filmPrestitoController){
		this.f = f;
		this.libroPrestitoController = libroPrestitoController;
		this.filmPrestitoController = filmPrestitoController;
	}


	/**
	 * Metodo prorogaPrestito permette ad un fruitore, di prorogare la scadenza di un
	 * determinato prestito. Tale proroga può essere richiesta una sola volta.
	 * @param la
	 */	
	public void proroga(LocalDateTime la){
		Prestito p;
		int selezione = InputDati.selezionaElementoDaArray(ConstantsRisorsa.TIPOLOGIE_RISORSE);
		if(ConstantsRisorsa.TIPOLOGIE_RISORSE[selezione].equals("LIBRI")){
			p = libroPrestitoController.selezionaPrestitoConFiltro(ConstantsRisorsa.LIBRO,f.getPrestiti());
			verificaProroga(la, p, libroPrestitoController);
		}
		else{
			p = filmPrestitoController.selezionaPrestitoConFiltro(ConstantsRisorsa.FILM,f.getPrestiti());
			verificaProroga(la, p, filmPrestitoController);
		}
	}
	
	public void verificaProroga(LocalDateTime la, Prestito p, PrestitoEntityController controller) { 
		if(p == null){
			controller.getPrestitoView().stampaErroreVuoto();
			return;
		}
		if(p.rinnovo()){ 
			prorogaCalcolo(la, p, controller);
		}
		else{
			controller.getPrestitoView().stampaNoPrestito();
		}
	}
	
	public void prorogaCalcolo(LocalDateTime la, Prestito p, PrestitoEntityController controller){
		if (la.isBefore(p.getFine_prestito()) && la.isAfter(calcoloTerminiPrescrittiProroga(p))) {

			p.setInizio_prestito(la);
			p.setFine_prestito(p.calcolo_fine_prestito());
			p.setNumero_rinnovo(p.getNumero_rinnovo()+1);
			controller.getPrestitoView().stampaSiRinnovo();

		} else if (!la.isAfter(calcoloTerminiPrescrittiProroga(p))) { 
			LocalDateTime d;
			d = calcoloTerminiPrescrittiProroga(p);
			controller.getPrestitoView().stampaRinnovoTraPoco(d);
		}
	}




	public LocalDateTime calcoloTerminiPrescrittiProroga(Prestito p){
		Calcolo calcoloProroga = new CalcoloInizioProroga(p);
		return calcoloProroga.calcolo();
	}
}
