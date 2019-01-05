package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import action.Calcolo;
import action.CalcoloScadenzaIscrizione;
import mylib.Data;

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
	private List<Prestito> prestitiAttualiMiei;
	private List<Prestito> prestitiStoriciMiei;

	//private int prestitiFilm;
	//private int prestitiLibro;//AGGIUNTI PER CALCOLO POSSIBILITA RICHIESTA PRESTITIFILM E LIBRO

	//HO TOLTO L'ATTRIBUTO DI TIPO ARCHIVIOPRESTITI CHIAMATO ARCHIVIO STORICO PRESTITI
	// QUESTO RAPPRESENTA IL MIO PRESTITO MODEL -> IN PRESTITO MODEL
	/**
	 * Costruttore classe Fruitore. Ciascun fruitore è composto da un nome, da una data
	 * di iscrizione e da una data di scadenza dell'iscrizione.
	 * @param nome
	 */
	public Fruitore(String nome, int età){
		super(nome, età);
		this.inizio_iscrizione = Data.creaData(); 
		this.scadenza_iscrizione = calcolo_fine_iscrizione(); //Data.cambiaAnno(5, inizio_iscrizione);
//		prestitiFilm = 0;   	
//		prestitiLibro = 0;  
		prestitiAttualiMiei = new ArrayList<>();
		prestitiStoriciMiei = new ArrayList<>();
	}   
	
	//CAMBIA MINUTO IO USEREI UN METODO QUI 
	public LocalDateTime calcolo_fine_iscrizione(){
		Calcolo calcoloFine = new CalcoloScadenzaIscrizione(this);
		return calcoloFine.calcolo();
	}
	
	//TOLTO METODO CREA FRUITORE STATIC CHE CREAVA FRUITORE DA INPUT -> INSERITO IN FRUITORE VIEW
	//ANCHE IL TO STRING PER STAMPARE ANAGRAFICHE FRUITORE

	
	//METODO RINNOVO SPOSTATO IN FRUITORE CONTROLLER E SPEZZATO -> REFACTOR EXTRACT METHOD E MOVE METHOD
	
	//METODO SELEZIONA PRESTITO IN VIEW PERCHE' PRENDE INPUT DA UTENTE
	
	//CONTROLLO SCADENZA PRESTITI SPOSTATO
	//STAMPA CON NUMERI IN VIEW
	
	//PROROGA PRESTITO IN AZIONE A PARTE
	//RICHIEDI PRESTITO IN AZIONE A PARTE

	
//	public boolean controlloRichiestaPrestitoLibro(){ 
//		return this.prestitiLibro>= ConstantsPrestito.NUM_MAX_PRESTITI_LIBRO? false : true;
//	}
	
//	/**
//	 * Metodo controlloRichiestaPrestitoLibro restituisce un valore indicante
//	 * la possibilità o meno di effettuare prestiti per la categoria libro.
//	 * @return
//	 */
//	public boolean controlloRichiestaPrestitoLibro(){
//		int valore = getPrestitiPerTipo(ConstantsRisorsa.LIBRO, prestitiAttualiMiei).size();
//		return valore >=ConstantsPrestito.NUM_MAX_PRESTITI_LIBRO? false : true;
//	}
//	
//	/**
//	 *  Metodo controlloRichiestaPrestitoFilm restituisce un valore indicante	
//	 * la possibilità o meno di effettuare prestiti per la categoria film.
//	 * @return
//	 */
//	public boolean controlloRichiestaPrestitoFilm(){
////		int valore = getPrestitiPerTipo(ConstantsRisorsa.FILM, prestitiAttualiMiei).size();
////		return valore >=ConstantsPrestito.NUM_MAX_PRESTITI_FILM? false : true;
//		return controlloRichiestaPrestitoRisorsa(ConstantsRisorsa.FILM, ConstantsPrestito.NUM_MAX_PRESTITI_FILM);
//	}
	
	//PIU GENERICO
	public boolean controlloRichiestaPrestitoRisorsa(String tipo, int valoreMax){
		int valore = getPrestitiPerTipo(tipo, prestitiAttualiMiei).size();
		return valore >=valoreMax? false : true;
	}

	/**
	 * Metodo getPrestitiPerTipo consente di ottenere i prestiti relativi a una
	 * determinata risorsa. Per esempio, libri ottengo una Lista di prestiti 
	 * esclusivamente di libri.
	 * @param tipoPrestito
	 * @param prestiti
	 * @return
	 */
	//C'E ANCHE IN PRESTITO ENTITY CONTROLLER NON VA BENE RIPETUTO
	 
	//POTREBBE ESSERE SPOSTATO IN PRESTITO MODEL 
	//MA VA BENE ANCHE QUI 
	public List<Prestito> getPrestitiPerTipo(String tipoPrestito, List<Prestito> prestiti) {
		List<Prestito> prestitiTipo = new ArrayList<Prestito>();
		for(Prestito p : prestiti) { //passo l'arrayList prestiti generico perchè cosi posso usarlo sia per prestiti storici che prestiti attuali
			if(p.getTipoRisorsa().equalsIgnoreCase(tipoPrestito)){
				prestitiTipo.add(p);
			}
		}
		return prestitiTipo;
	}

	/**
	 * Metodo controlloScadenzaPrestitiFruitore controlla per ogni prestito, contenuto
	 * nell'elenco di prestiti dei fruitori, la scadenza.Se il prestito è scaduto, viene
	 * rimosso dall'elenco.
	 * @param d
	 */ //HO TOLTO PARAMETRO DATA E L'HO INSERITO ALL'INTERNO
	//METODO INSERITO QUI PERCHE' IL MIO MODEL RAPPRESENTA L'ARCHIVIO STORICO DI PRESTITI E QUINID
	//NON DEVE CONTROLLARE LE SCADENZE
	public void controlloScadenzaPrestitiFruitore() {
		LocalDateTime data = LocalDateTime.now();
		if (!this.prestitiAttualiMiei.isEmpty()) {
				List<Prestito> toRemove = new ArrayList<Prestito>();
				for (Prestito p : prestitiAttualiMiei) {
					if (p.getFine_prestito().isBefore(data)) {
						toRemove.add(p);
						p.getR().setNumeroLicenze(p.getR().getNumeroLicenze()+1);
					} 
				}
				prestitiAttualiMiei.removeAll(toRemove);
			} 
		} 
	
	public List<Prestito> getPrestitiStoriciMiei() {
		return prestitiStoriciMiei;
	}

//	public int getPrestitiFilm() {
//		return prestitiFilm;
//	}
//
//
//	public int getPrestitiLibro() {
//		return prestitiLibro;
//	}
//
//
//	public void setPrestitiFilm(int prestitiFilm) {
//		this.prestitiFilm = prestitiFilm;
//	}
//
//
//	public void setPrestitiLibro(int prestitiLibro) {
//		this.prestitiLibro = prestitiLibro;
//	}


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
		return prestitiAttualiMiei;
	}

}
