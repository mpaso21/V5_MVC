package controller.entitycontroller;

import java.time.LocalDateTime;
import java.util.List;

import entity.Fruitore;
import entity.Prestito;
import entity.Risorsa;
import model.PrestitoModel;
import model.RisorsaModel;
import view.PrestitoView;
import view.RisorsaView;


public class PrestitoEntityController {

	//INCONTROLLER METTO METODI IN MODEL PER EVITARE UN GET?
	private PrestitoView prestitoView;
	private PrestitoModel prestitoModel;
	
	public PrestitoEntityController(PrestitoView prestitoView, PrestitoModel prestitoModel) {
		this.prestitoView = prestitoView;
		this.prestitoModel = prestitoModel;
	}
	


	public void aggiungiPrestito(Prestito p,List<Prestito> prestitiFruitore){
		this.prestitoModel.aggiungiPrestito(p,prestitiFruitore);
	}
	
	public void controlloScadenzaTuttiPrestitiFruitore (List<Prestito> prestitiFruitore){
		this.prestitoModel.controlloScadenzaPrestitiFruitore(LocalDateTime.now(),prestitiFruitore);
	}


	public PrestitoView getPrestitoView() {
		return prestitoView;
	}

	public PrestitoModel getPrestitoModel() {
		return prestitoModel;
	}

	public Prestito selezionaPrestito(List<Prestito> prestitiFruitore){
		controlloScadenzaTuttiPrestitiFruitore(prestitiFruitore);
		if(!(prestitiFruitore.isEmpty())){
			return prestitoView.seleziona(prestitiFruitore);
		}
		else{
			return null;
		}
	}
	
	public void stampaPrestiti(List<Prestito> prestiti){
		this.prestitoView.stampaTutti(prestiti);
	}

	


}
