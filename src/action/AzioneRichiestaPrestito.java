package action;


import controller.entitycontroller.PrestitoEntityController;
import controller.entitycontroller.RisorsaEntityController;
import entity.Fruitore;
import entity.Prestito;
import entity.Risorsa;
import mylib.Constants;
import mylib.InputDati;
import view.FilmView;
import view.LibroView;

public class AzioneRichiestaPrestito {
	
	private Fruitore f;
	private RisorsaEntityController libroController;
	private RisorsaEntityController filmController;
	private PrestitoEntityController libroPrestitoController;
	private PrestitoEntityController filmPrestitoController;
	
	public AzioneRichiestaPrestito(Fruitore f, RisorsaEntityController libroController, RisorsaEntityController filmController,
			PrestitoEntityController libroPrestitoController, PrestitoEntityController filmPrestitoController){
		this.f = f;
		this.libroController =libroController;
		this.filmController = filmController;
		this.libroPrestitoController = libroPrestitoController;
		this.filmPrestitoController = filmPrestitoController;
		}
	
	public void azione(){
		
		int selezione = InputDati.selezionaElementoDaArray(Constants.TIPOLOGIE_RISORSE);
		
		if(Constants.TIPOLOGIE_RISORSE[selezione].equals("LIBRI")){
			richiestaLibro();
		}
		else if(Constants.TIPOLOGIE_RISORSE[selezione].equals("FILM")){
			richiestaFilm();
		}
	}
	
	public void richiestaLibro(){
		if(libroController.controlloSizeArchivio() == 0){
			libroController.getRisorsaView().stampaErroreArchivio();
			return;
		}
		
		if(f.controlloRichiestaPrestitoLibro()){ //SELEZIONE LA RISORSA GIUSTA
			Risorsa r = libroController.selezionaRisorsa();
			
			if(r.getNumeroLicenze()>0){ 
				Prestito p = new Prestito(r, f.getNome()); //DA FARE IN CONSTANTS
				libroPrestitoController.aggiungiPrestito(p, f.getPrestiti());
				r.setNumeroLicenze(r.getNumeroLicenze()-1);
				f.setPrestitiLibro(f.getPrestitiLibro()+ 1);
			}
			else{
				libroController.getRisorsaView().stampaRisorsaCopieEsaurite(r);
			}
		}
	}
	
	public void richiestaFilm(){
		if(filmController.controlloSizeArchivio() == 0){
			filmController.getRisorsaView().stampaErroreArchivio();
			return;
		}
		
		if(f.controlloRichiestaPrestitoFilm()){ //SELEZIONE LA RISORSA GIUSTA
			Risorsa r = filmController.selezionaRisorsa();
			
			if(r.getNumeroLicenze()>0){ 
				Prestito p = new Prestito(r, f.getNome()); //DA FARE IN CONSTANTS
				filmPrestitoController.aggiungiPrestito(p, f.getPrestiti());
				r.setNumeroLicenze(r.getNumeroLicenze()-1);
				f.setPrestitiFilm(f.getPrestitiFilm()+ 1);
			}
			else{
				filmController.getRisorsaView().stampaRisorsaCopieEsaurite(r);
			}
		}
	}
}
