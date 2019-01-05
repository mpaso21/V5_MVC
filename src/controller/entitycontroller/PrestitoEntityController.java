package controller.entitycontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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


	//METODO DELEGATI
	public void aggiungiPrestito(Prestito p,List<Prestito> prestitiFruitore,List<Prestito> prestitiStoriciFruitore ){
		prestitoModel.aggiungiPrestito(p,prestitiFruitore, prestitiStoriciFruitore);
	}

	//SELEZIONA PRESTITO FILTRATO PER LIBRI E FILM
	//IN PRESTITO ENTITY CONTROLLER PERCHE' MISCHIA VIEW E LOGICA
	public Prestito selezionaPrestitoConFiltro(String nome,List<Prestito> prestitiFruitore){
		
		if(!(prestitiFruitore.isEmpty())){
			List <Prestito> prestitiFiltrati = filtraPrestitoPerRisorsa(nome, prestitiFruitore);
			return prestitoView.seleziona(prestitiFiltrati);
		}
		else{
			return null;
		}
	}

	//PUO' ANDARE NEL MODEL
	private List<Prestito> filtraPrestitoPerRisorsa(String nome,List<Prestito> prestitiFruitore){
		List <Prestito> prestitiFiltrati = new ArrayList<Prestito>();
		for(Prestito p: prestitiFruitore){
			if(p.getTipoRisorsa().equalsIgnoreCase(nome)){
				prestitiFiltrati.add(p);
			}
		}
		return prestitiFiltrati;
	}


////POTRA' POI ESSERE TOLTO 
//	public void controlloScadenzaTuttiPrestitiFruitore (List<Prestito> prestitiFruitore){
//		prestitoModel.controlloScadenzaPrestitiFruitore(LocalDateTime.now(),prestitiFruitore);
//	}

	//METODI DELEGATI PER EVITARE TROPPI GET
	public void stampaPrestiti(List<Prestito> prestiti){
		prestitoView.stampaTutti(prestiti);
	}

	public int prestitiPerAnno(){
		return prestitoModel.prestitiPerAnno();
	}
	
	public int proroghePerAnno(){
		return prestitoModel.proroghePerAnno();
	}

	public Map<String, Integer> getMappaRisorsaPiuPrestata(){
		return prestitoModel.risorsaPiuPrestataPerAnno();
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
