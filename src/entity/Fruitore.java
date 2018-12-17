package entity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import mylib.Constants;
import mylib.Data;
import mylib.InputDati;



/**
 * La classe Fruitore rappresenta un cittadino maggiorenne,
 * fruitore dei servizi di prestito, identificato mediante un ID.
 * @author Marika
 *
 */
public class Fruitore extends Cittadino{

	private LocalDateTime inizio_iscrizione;
	private LocalDateTime scadenza_iscrizione;
	private String id;
	private List<Prestito> prestiti;
	private int prestitiFilm;
	private int prestitiLibro;

	/**
	 * Costruttore classe Fruitore. Ciascun fruitore è composto da un nome, da una data
	 * di iscrizione e da una data di scadenza dell'iscrizione.
	 * @param nome
	 */
	public Fruitore(String nome, int età){
		super(nome, età);
		this.inizio_iscrizione = Data.creaData(); 
		this.scadenza_iscrizione = Data.cambiaMinuto(Constants.NUM_SCADENZA, inizio_iscrizione); //Data.cambiaAnno(5, inizio_iscrizione);
		prestitiFilm = 0;   
		prestitiLibro = 0;  
		prestiti = new ArrayList<>();
	}   

	
	public int getPrestitiFilm() {
		return prestitiFilm;
	}


	public int getPrestitiLibro() {
		return prestitiLibro;
	}


	public void setPrestitiFilm(int prestitiFilm) {
		this.prestitiFilm = prestitiFilm;
	}


	public void setPrestitiLibro(int prestitiLibro) {
		this.prestitiLibro = prestitiLibro;
	}


	public LocalDateTime getInizioIscrizione() {
		return inizio_iscrizione;
	}

	public LocalDateTime getScadenzaIscrizione() {
		return scadenza_iscrizione;
	}

	public void setInizioIscrizione(LocalDateTime inizio_iscrizione) {
		this.inizio_iscrizione = inizio_iscrizione;
	}

	public void setScadenzaIscrizione(LocalDateTime scadenza_iscrizione) {
		this.scadenza_iscrizione = scadenza_iscrizione;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Prestito> getPrestiti() {
		return prestiti;
	}
	
	public List<Prestito> getPrestitiPerTipo(String tipoPrestito) {
		List<Prestito> prestitiTipoLibro = new ArrayList<Prestito>();
		for(Prestito p : prestiti) {
			if(p.getTipoRisorsa().equalsIgnoreCase(tipoPrestito)){
				prestitiTipoLibro.add(p);
			}
		}
		return prestitiTipoLibro;
	}

	public void setPrestiti(ArrayList<Prestito> prestiti) {
		this.prestiti = prestiti;
	}
	
	/**
	 * Metodo controlloRichiestaPrestitoLibro restituisce un valore indicante
	 * la possibilità o meno di effettuare prestiti per la categoria libro.
	 * @return
	 */
	public boolean controlloRichiestaPrestitoLibro(){ 
		return this.prestitiLibro>= Libro.NUM_MAX_PRESTITI? false : true;
	}
	/**
	 *  Metodo controlloRichiestaPrestitoFilm restituisce un valore indicante	
	 * la possibilità o meno di effettuare prestiti per la categoria film.
	 * @return
	 */
	public boolean controlloRichiestaPrestitoFilm(){ 
		return this.prestitiFilm>= Film.NUM_MAX_PRESTITI? false : true;
	}
	




}
