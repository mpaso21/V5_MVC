package controller.entitycontroller;

import java.util.List;

import entity.Risorsa;
import model.risorsa.LibroModel;
import mylib.ConstantsPrestito;
import view.risorsa.LibroView;

public class LibroEntityController extends RisorsaEntityController {

	public LibroEntityController() {
		super(new LibroView(), new LibroModel());
	}

	public List<Risorsa> ricercaAutore(String inputAutore){
		return ( (LibroModel) getRisorsaModel()).ricercaLibroAutore(inputAutore);	
	}
	
	@Override
	public int getNumMaxPrestiti() {
		return ConstantsPrestito.NUM_MAX_PRESTITI_LIBRO;
	}
}
