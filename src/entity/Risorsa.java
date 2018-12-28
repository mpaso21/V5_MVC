package entity;


import mylib.ConstantsRisorsa;
import mylib.UtilitaControllo;

/**
 * Classe Risorsa rappresenta una risorsa, di qualunque tipo, presente nell'archivio,
 * che può essere acquisita da un fruitore.
 * @author Marika
 *
 */
public class Risorsa {

	protected String nome;
	protected String genere;
	protected String lingua;
	protected int numeroLicenze;
	protected String tipo;
	/**
	 *  Costruttore Risorsa. Una risorsa è costituita da un nome, genere, lingua, da un numero di licenze
	 *  e da un tipo di Risorsa.
	 * @param nome
	 */
	public Risorsa(String nome, String genere, String lingua){
		this.nome = nome;
		this.genere = genere; //GENERE SPOSTATO QUI PERCHE' COMUNE A LIBRO/FILM-> REFACTOR MOVE FIELD 
		this.lingua = lingua; //LINGUA SPOSTATO QUI PERCHE' COMUNE A LIBRO/FILM-> REFACTOR MOVE FIELD 
		this.numeroLicenze = UtilitaControllo.randomcompreso(ConstantsRisorsa.MIN_LICENZA,ConstantsRisorsa.MAX_LICENZA); //TOLTO METODO GENERA NUM LICENZA E COLLOCATO DIRETTAMENTE QUI
		this.tipo = ConstantsRisorsa.RISORSA; //INSERITO CAMPO PER TIPO RISORSA												//METODO GENERA NUM LICENZA POCO TESTABILE PERCHE?
	}
	//TOLTO METODO GENERA NUM LICENZA
	
	//METODO TOSTRING, STRINGA STORICO, STRINGA STORICO INTESTAZIONE SPOSTATI IN RISORSA VIEW PERCHE'
	//RELATIVI ALLE STAMPE -> REFACTOR MOVE METHOD
	
	//HO RISPETTATO IL SINGLE RESPONSABILITY PERCHE' HO SUDDIVISO LE RESPONSABILITA', RISORSA SI OCCUPA
//SOLO DI GESTIRE GLI ATTRIBUTI, NON DELLE STAMPE
	
	//METODO EQUALS LASCIATO IN RISORSA PERCHE' APPARTIENE A UNA RISORSA.
	//NON NEL RISORSA MODEL CHE RAPPRESENTA UN ARRAY DI RISORSE.
	/**
	 * Metodo risorsaUguale controlla se due risorse sono uguali.
	 * Per uguaglianza di risorsa si intende nome (titolo) uguale.
	 * @param r
	 * @return
	 */
	public boolean equals(Risorsa r) {
		if(r.getNome().equalsIgnoreCase(this.getNome())) {
			return true;
		}
		return false;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumeroLicenze() {
		return numeroLicenze;
	}

	public void setNumeroLicenze(int numeroLicenze) {
		this.numeroLicenze = numeroLicenze;
	}

	public String getGenere() {
		return genere;
	}

	public String getLingua() {
		return lingua;
	}

	
}
