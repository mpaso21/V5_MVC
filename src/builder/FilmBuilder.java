package builder;

import entity.Film;
import entity.Risorsa;

public class FilmBuilder<U> extends RisorsaBuilder<U> {
	
	private String regista;
	private int durata;
	private int annoDiUscita;
	private String casaProduttrice;
	
	public U regista(String regista){
		this.regista = regista;
		return (U) this;
	}
	
	public U durata(int durata){
		this.durata = durata;
		return (U) this;
	}
	
	public U annoDiUscita(int anno){
		this.annoDiUscita = anno;
		return (U) this;
	}
	
	public U casaProduttrice(String casaProduttrice){
		this.casaProduttrice = casaProduttrice;
		return (U) this;
	}
	
	@Override
	public Risorsa build(){
		return new Film(nome ,regista,durata, 
				annoDiUscita, casaProduttrice, lingua,genere, numLicenze);
	}

}
