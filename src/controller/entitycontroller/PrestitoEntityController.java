package controller.entitycontroller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import entity.Prestito;
import model.prestito.PrestitoModel;
import view.prestito.PrestitoView;


public class PrestitoEntityController {


	private PrestitoView prestitoView;
	private PrestitoModel prestitoModel;

	public PrestitoEntityController(PrestitoView prestitoView, PrestitoModel prestitoModel) {
		this.prestitoView = prestitoView;
		this.prestitoModel = prestitoModel;
	}

//TROPPI PARAMETRI
	public void aggiungiPrestito(Prestito p,List<Prestito> prestitiFruitore,List<Prestito> prestitiStoriciFruitore ){
		prestitoModel.aggiungiPrestito(p,prestitiFruitore, prestitiStoriciFruitore);
	}

	//SELEZIONA PRESTITO FILTRATO PER LIBRI E FILM
	//POSSO FARLO ANCHE NORMALE CIOE' SELEZIONA DA TUTTO 

	public Prestito selezionaPrestitoConFiltro(String nome,List<Prestito> prestitiFruitore){
		controlloScadenzaTuttiPrestitiFruitore(prestitiFruitore);
		if(!(prestitiFruitore.isEmpty())){
			List <Prestito> prestitiFiltrati = filtraPrestito(nome, prestitiFruitore);
			return prestitoView.seleziona(prestitiFiltrati);
		}
		else{
			return null;
		}
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
	
	public List<Prestito> filtraPrestito(String nome,List<Prestito> prestitiFruitore){
		List <Prestito> prestitiFiltrati = new ArrayList<Prestito>();
		for(Prestito p: prestitiFruitore){
			if(p.getTipoRisorsa().equalsIgnoreCase(nome)){
				prestitiFiltrati.add(p);
			}
		}
		return prestitiFiltrati;
	}
//POTRA' POI ESSERE TOLTO 
	public void controlloScadenzaTuttiPrestitiFruitore (List<Prestito> prestitiFruitore){
		prestitoModel.controlloScadenzaPrestitiFruitore(LocalDateTime.now(),prestitiFruitore);
	}

	public void stampaPrestiti(List<Prestito> prestiti){
		prestitoView.stampaTutti(prestiti);
	}


	public void prestitiPerAnno(){
		int valore = prestitoModel.prestitiPerAnno();
		prestitoView.stampaNumPrestitiPerAnno(valore);
	}


	public void proroghePerAnno(){
		int valore = prestitoModel.proroghePerAnno();
		prestitoView.stampaNumProroghePerAnno(valore);
	}
										//VA BENE QUI QUESTO METODO?
	public void risorsaPiuPrestata(){	//PER ANNO CORRENTE
		HashMap <String,Integer> a = prestitoModel.risorsaPiuPrestataPerAnno();
		if(a.size() == 0){
			prestitoView.stampaZeroPrestiti();
		}
		else{
			prestitoView.stampaPerRisorsaPiuPrestata(a);;
		}
	}
	
	public void limiteRaggiunto(){
		this.prestitoView.superatoLimitePrestiti();
	}
	public PrestitoView getPrestitoView() {
		return prestitoView;
	}

	public PrestitoModel getPrestitoModel() {
		return prestitoModel;
	}
}
