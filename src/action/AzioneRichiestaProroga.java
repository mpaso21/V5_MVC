package action;

import java.time.LocalDateTime;

import controller.entitycontroller.PrestitoEntityController;
import controller.entitycontroller.RisorsaEntityController;
import entity.Fruitore;
import entity.Prestito;
import mylib.Constants;
import mylib.Data;
import mylib.InputDati;

public class AzioneRichiestaProroga {

	private Fruitore f;
	private PrestitoEntityController prestitoController;
	
	
	public AzioneRichiestaProroga(Fruitore f, PrestitoEntityController prestitoController){
		this.f = f;
		this.prestitoController = prestitoController;
		}
	
	
	/**
	 * Metodo prorogaPrestito permette ad un fruitore, di prorogare la scadenza di un
	 * determinato prestito. Tale proroga può essere richiesta una sola volta.
	 * @param la
	 */
	public void proroga(LocalDateTime la) { 
		Prestito p = prestitoController.selezionaPrestito(f.getPrestiti());
		if(p == null){
			prestitoController.getPrestitoView().stampaErroreVuoto();
			return;
		}
		if(p.rinnovo()){ 
			prorogaPrestito(la, p);//VUOL DIRE CHE E' LA PRIMA VOLTA CHE RINNOVO
		}
		else{
			prestitoController.getPrestitoView().stampaNoPrestito();
		}
	}
	
		public void prorogaPrestito(LocalDateTime la, Prestito p){
			
			if (la.isBefore(p.getFine_prestito()) && la.isAfter(calcoloTerminiPrescrittiProroga(p))) {

				p.setInizio_prestito(la);
				p.setFine_prestito(p.calcolo_fine_prestito());
				p.setNumero_rinnovo(p.getNumero_rinnovo()+1);
				prestitoController.getPrestitoView().stampaSiRinnovo();

			} else if (!la.isAfter(calcoloTerminiPrescrittiProroga(p))) { // puoi rinnovarli da calcolo termini in poi
				LocalDateTime d;
				d = calcoloTerminiPrescrittiProroga(p);
				prestitoController.getPrestitoView().stampaRinnovoTraPoco(d);
			}
		}
	
	
	public LocalDateTime calcoloTerminiPrescrittiProroga(Prestito p){
		CalcoliPerPrestito calcolo = new CalcoliPerPrestito(p);
		return calcolo.calcoloTerminiPrescrittiProroga();
	}
}
