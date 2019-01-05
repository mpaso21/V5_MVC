package builder;

import entity.Libro;
import entity.Risorsa;

public class LibroBuilder<U> extends RisorsaBuilder<U> {

	private String autore;
	private int numeroPagine;
	private String casaEditrice;
	private int anno;
	
	public U autore(String autore){
		this.autore = autore;
		return (U) this;
	}
	
	public U casaEditrice (String casaEditrice){
		this.casaEditrice = casaEditrice;
		return (U) this;
	}
	
	
	public U numeroPagine(int numPag){
		this.numeroPagine = numeroPagine;
		return (U) this;
	}
	
	public U anno(int anno){
		this.anno = anno;
		return (U) this;
	}
	
	
	@Override
	public Risorsa build(){
		return new Libro(nome, autore,lingua, genere,  casaEditrice,anno, numeroPagine, numLicenze);
	}
}

