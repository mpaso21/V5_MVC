package action;


import controller.LoginController;
import controller.entitycontroller.PrestitoEntityController;
import controller.entitycontroller.RisorsaEntityController;
import entity.Fruitore;
import entity.Prestito;
import entity.Risorsa;
import mylib.ConstantsRisorsa;
import mylib.InputDati;

public class AzioneRichiestaPrestito {

	private Fruitore f;
	private LoginController main;


	private TableInfoPerPrestito info;
	//TANTI PARAMETRI->  INTRODUCE PARAMETER OBJECT CLASSE TABLE INFO PER PRESTITO
	public AzioneRichiestaPrestito(Fruitore f, LoginController main){
		this.f = f;
		this.main = main;
		this.info = new TableInfoPerPrestito(main);
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
	//QUALE ALTERNATIVA E' MEGLIO?
	private boolean controllo(RisorsaEntityController c){
		if(c.controlloSizeArchivio()==0){
			c.getRisorsaView().stampaErroreArchivio();
			return true;
		}
		return false;
	}
	
	private boolean controlloPerRichiestaSpecifica(String tipo, int valoreMax){
		return f.controlloRichiestaPrestitoRisorsa(tipo, valoreMax);
	}
	
	private void prendoRisorsa(String nome, PrestitoEntityController contr, RisorsaEntityController ris){ 
		Risorsa r = ris.selezionaRisorsaConFiltro(nome, ris.risorse());//SBAGLIA
		this.prendoRisorsa(r,  nome, contr, ris);
	}
	//TEST
	private void prendoRisorsa(Risorsa r, String nome, PrestitoEntityController contr, RisorsaEntityController ris){ 
		if(r.getNumeroLicenze()>0){ 
			Prestito p = new Prestito(r, f.getNome()); 
			contr.aggiungiPrestito(p, f.getPrestiti(), f.getPrestitiStoriciMiei());
			r.setNumeroLicenze(r.getNumeroLicenze()-1);		
		}
		else{
			ris.getRisorsaView().stampaRisorsaCopieEsaurite(r);
		}
	}
	
	public void rifatto(){
		int selezione = InputDati.selezionaElementoDaArray(ConstantsRisorsa.TIPOLOGIE_RISORSE);
		String selezioneKey = ConstantsRisorsa.TIPOLOGIE_RISORSE[selezione];
		RisorsaEntityController rec = info.getRisorsaControllerByKey(selezioneKey);
		salvaPrestitoPerRisorsa(rec, info.getPrestitoControllerByKey(selezioneKey),
				selezioneKey, rec.getNumMaxPrestiti() );
	}
	
	//test
	public void rifatto(String selezioneKey, Risorsa r){
		RisorsaEntityController rec = info.getRisorsaControllerByKey(selezioneKey);
		salvaPrestitoPerRisorsa(r, rec, info.getPrestitoControllerByKey(selezioneKey),
				selezioneKey, rec.getNumMaxPrestiti() );
	}
	
	private void salvaPrestitoPerRisorsa(RisorsaEntityController rec, PrestitoEntityController pec, String tipoRisorsa, int maxRisorsa)
	{
		if(controllo(rec)) return;
		if(this.controlloPerRichiestaSpecifica(tipoRisorsa, maxRisorsa)){ //SELEZIONE LA RISORSA GIUSTA
			prendoRisorsa(tipoRisorsa, pec, rec);

		}
		else{
			pec.limiteRaggiunto(); //LIMITE RAGGIuNTO DI PRESTITI
		}
	}
	
//TEST
	private void salvaPrestitoPerRisorsa(Risorsa r, RisorsaEntityController rec, PrestitoEntityController pec, String tipoRisorsa, int maxRisorsa)
	{
		if(controllo(rec)) return;
		if(this.controlloPerRichiestaSpecifica(tipoRisorsa, maxRisorsa)){ //SELEZIONE LA RISORSA GIUSTA
			prendoRisorsa(r, tipoRisorsa, pec, rec);

		}
		else{
			pec.limiteRaggiunto(); //LIMITE RAGGIuNTO DI PRESTITI
		}
	}

	
}
