package action;


import controller.Controller;

import controller.entitycontroller.PrestitoEntityController;
import controller.entitycontroller.RisorsaEntityController;
import entity.Fruitore;
import entity.Prestito;
import entity.Risorsa;
import mylib.ConstantsRisorsa;
import mylib.InputDati;

public class AzioneRichiestaPrestito {

	private Fruitore f;
	private Controller main;


	
	public AzioneRichiestaPrestito(Fruitore f, Controller main){
		this.f = f;
		this.main = main;
	}

//	public void azione(){
//
//		int selezione = InputDati.selezionaElementoDaArray(ConstantsRisorsa.TIPOLOGIE_RISORSE);
//
//		if(ConstantsRisorsa.TIPOLOGIE_RISORSE[selezione].equals("LIBRI")){ //DA MODIFICARE CON LA CONSTANTS
//			richiestaLibro();
//		}
//		else if(ConstantsRisorsa.TIPOLOGIE_RISORSE[selezione].equals("FILM")){
//			richiestaFilm();
//		}
//	}
//	
//	public void richiestaLibro(){
//		if(info.getLibroController().controlloSizeArchivio()==0){
//			info.getLibroController().getRisorsaView().stampaErroreArchivio();
//			return;
//		}
//
//		if(f.controlloRichiestaPrestitoLibro()){ 
//			Risorsa r = info.getLibroController().selezionaRisorsa();
//
//			if(r.getNumeroLicenze()>0){ 
//				Prestito p = new Prestito(r, f.getNome()); 
//				info.getLibroPrestitoController().aggiungiPrestito(p, f.getPrestiti(), f.getPrestitiStoriciMiei()); 
//																//AGGIUNGO A PRESTITI STORICI
//				r.setNumeroLicenze(r.getNumeroLicenze()-1);					
//		
//			}
//			else{
//				 info.getLibroController().getRisorsaView().stampaRisorsaCopieEsaurite(r);
//			}
//		}
//		
//		else{
//			main.getPrestitoLibroController().limiteRaggiunto();
//		}
//	}
//	
//	
//	public void richiestaFilm(){
//		if(info.getFilmController().controlloSizeArchivio() == 0){
//			info.getFilmController().getRisorsaView().stampaErroreArchivio();
//			return;
//		}
//
//		if(f.controlloRichiestaPrestitoFilm()){ //SELEZIONE LA RISORSA GIUSTA
//			Risorsa r =info.getFilmController().selezionaRisorsa();
//
//			if(r.getNumeroLicenze()>0){ 
//				Prestito p = new Prestito(r, f.getNome()); 
//				info.getFilmPrestitoController().aggiungiPrestito(p, f.getPrestiti(), f.getPrestitiStoriciMiei());
//														//AGGIUNGO A PRESTITISTORICI
//
//				r.setNumeroLicenze(r.getNumeroLicenze()-1);
//			}
//			else{
//				info.getFilmController().getRisorsaView().stampaRisorsaCopieEsaurite(r);
//			}
//		}
//
//		else{
//			main.getPrestitoFilmController().limiteRaggiunto();
//		}
//	}
	//----------------------------------------------------------------------------------------------------------

	/**
	 * Metodo richiestaPrestito permette, a un fruitore, di poter scegliere
	 * la risorsa (che può essere di tipo Libro o Film) presente nell'archivio. 
	 * Se il prestito va a buon fine, il numero di licenze di tale risorsa viene decrementato.
	 * @param a
	 */
	public void azioneRichiestaPrestitoInput(){
		int selezione = InputDati.selezionaElementoDaArray(ConstantsRisorsa.TIPOLOGIE_RISORSE);
		String selezioneKey = ConstantsRisorsa.TIPOLOGIE_RISORSE[selezione];
		
		RisorsaEntityController rec = main.getRisorsaControllerByKey(selezioneKey);
		salvaPrestitoPerRisorsa(rec, main.getPrestitoControllerByKey(selezioneKey),
				selezioneKey, rec.getNumMaxPrestiti() );
	}
	//TEST
	public void azioneRichiestaPrestito(String selezioneKey, Risorsa r){
		RisorsaEntityController rec = main.getRisorsaControllerByKey(selezioneKey);
		salvaPrestitoPerRisorsa(r, rec, main.getPrestitoControllerByKey(selezioneKey),
				selezioneKey, rec.getNumMaxPrestiti() );
	} //IN BASE AL REC CHE HO PASSATO MI CHIAMA IL GET NUM MAX PRESTITI CORRETTO
	
		
	private void salvaPrestitoPerRisorsa(RisorsaEntityController rec, PrestitoEntityController pec, String tipoRisorsa, int maxRisorsa)
	{
		if(controlloNonVuoto(rec)) return;
		if(f.controlloRichiestaPrestitoRisorsa(tipoRisorsa, maxRisorsa)){ //SELEZIONE LA RISORSA GIUSTA
			Risorsa r = rec.selezionaRisorsa();
			//Risorsa r = rec.selezionaRisorsaConFiltro(tipoRisorsa, rec.risorse());
			creaPrestito(r, pec, rec);
		}
		else{
			pec.limiteRaggiunto(); //LIMITE RAGGIUNTO DI PRESTITI
		}
	}
		
	//PUO'ESSERE SPOSTATO IN RISORSA ENTITYCONTROLLER
	private boolean controlloNonVuoto(RisorsaEntityController c){
		if(c.controlloSizeArchivio()==0){
			c.getRisorsaView().stampaErroreArchivio();
			return true;
		}
		return false;
	}
	
//	private boolean controlloPerRichiestaSpecifica(String tipo, int valoreMax){
//		return f.controlloRichiestaPrestitoRisorsa(tipo, valoreMax);
//	}

	
	//PASSAVO ANCHE COME PARAMETRO STRING NOME MA NON LO UTILIZZAVO
	private void creaPrestito(Risorsa r, PrestitoEntityController contr, RisorsaEntityController ris){ 
		Prestito p = null;
		if(r.getNumeroLicenze()>0){ 
			p = new Prestito(r, f); 
			contr.aggiungiPrestito(p, f.getPrestiti(), f.getPrestitiStoriciMiei());
			r.setNumeroLicenze(r.getNumeroLicenze()-1);
		}
		else{
			ris.getRisorsaView().stampaRisorsaCopieEsaurite(r);
		}
	}
	

	
	//TEST 
	private void salvaPrestitoPerRisorsa(Risorsa r, RisorsaEntityController rec, PrestitoEntityController pec, String tipoRisorsa, int maxRisorsa)
	{
		if(controlloNonVuoto(rec)) return;
		if(f.controlloRichiestaPrestitoRisorsa(tipoRisorsa, maxRisorsa)){ //SELEZIONE LA RISORSA GIUSTA
			creaPrestito(r, pec, rec);
		}
		else{
			pec.limiteRaggiunto(); //LIMITE RAGGIuNTO DI PRESTITI
		}
	}
	
	
	


	
}
