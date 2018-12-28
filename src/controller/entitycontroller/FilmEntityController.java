package controller.entitycontroller;

import java.util.List;

import entity.Risorsa;
import model.risorsa.FilmModel;
import mylib.ConstantsPrestito;
import view.risorsa.FilmView;

public class FilmEntityController extends RisorsaEntityController {

	public FilmEntityController() {
		super(new FilmView(), new FilmModel());
	}

	public List<Risorsa> ricercaRegista(String inputRegista){
		return ( (FilmModel) getRisorsaModel()).ricercaFilmRegista(inputRegista);	
	}
	
	@Override
	public int getNumMaxPrestiti() {
		return ConstantsPrestito.NUM_MAX_PRESTITI_FILM;
	}
}
