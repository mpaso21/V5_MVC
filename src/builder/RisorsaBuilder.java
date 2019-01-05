package builder;

import entity.Risorsa;

public abstract class RisorsaBuilder<U> {

	protected String nome, genere, lingua;
	protected int numLicenze;
	
	public U titolo(String nome){
		this.nome = nome;
		return (U) this;
	}
	
	public U genere (String genere){
		this.genere = genere;
		return (U) this;
	}
	
	public U lingua (String lingua){
		this.lingua = lingua;
		return (U) this;
	}
	
	public U numLicenze(int numLicenze){
		this.numLicenze = numLicenze;
		return (U) this;
	}
	
	public abstract Risorsa build();
	
	
	
	
}
