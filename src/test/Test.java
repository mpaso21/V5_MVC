package test;

import static org.junit.Assert.*;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import action.AzioneRichiestaPrestito;
import action.AzioneRichiestaProroga;
import controller.Controller;
import controller.FruitoreController;

import controller.entitycontroller.FilmEntityController;
import controller.entitycontroller.LibroEntityController;
import controller.entitycontroller.RisorsaEntityController;
import entity.Film;
import entity.Fruitore;
import entity.Libro;
import entity.Risorsa;
import mylib.ConstantsRisorsa;
import mylib.Data;


public class Test {

	@org.junit.Test
	public void prestitoLibro() {
		Controller main = new Controller();
		RisorsaEntityController rec = main.getRisorsaControllerByKey(ConstantsRisorsa.LIBRO);
	
		//RisorsaEntityController rec = main.getLibroController();
		Fruitore f = new Fruitore("marika", 23);
		Libro l = new Libro("Mattia", "Pirandello", "IT", "COM", "MOND", 1923, 322, 1);
		rec.aggiungiRisorsa(l);
		AzioneRichiestaPrestito a = new AzioneRichiestaPrestito(f, main);
		a.azioneRichiestaPrestito(ConstantsRisorsa.LIBRO, l);
		assertTrue((f.getPrestitiPerTipo(ConstantsRisorsa.LIBRO, f.getPrestiti()).size() == 1) &&
				(f.getPrestitiPerTipo(ConstantsRisorsa.FILM, f.getPrestiti()).size() == 0));
	}

	@org.junit.Test
	public void prestitoFilm() {
		Controller main = new Controller();
		RisorsaEntityController rec = main.getRisorsaControllerByKey(ConstantsRisorsa.FILM);
		//RisorsaEntityController rec = main.getFilmController();
		Fruitore f = new Fruitore("marika", 23);
		Film l = new Film("Marika", "Marika", 123, 1000, "MERDADORI", "IT", "COM", 1);
		rec.aggiungiRisorsa(l);
		AzioneRichiestaPrestito a = new AzioneRichiestaPrestito(f, main);
		a.azioneRichiestaPrestito(ConstantsRisorsa.FILM, l);
		assertTrue((f.getPrestitiPerTipo(ConstantsRisorsa.LIBRO, f.getPrestiti()).size() == 0) &&
				(f.getPrestitiPerTipo(ConstantsRisorsa.FILM, f.getPrestiti()).size() == 1));
	}

	@org.junit.Test
	public void prorogaLibroNonAncoraPossibile() {
		Controller main = new Controller();

		RisorsaEntityController rec = main.getRisorsaControllerByKey(ConstantsRisorsa.LIBRO);
		//RisorsaEntityController rec = main.getLibroController();
		Fruitore f = new Fruitore("marika", 23);
		Libro l = new Libro("Mattia", "Pirandello", "IT", "COM", "MOND", 1923, 322, 1);
		rec.aggiungiRisorsa(l);
		
		AzioneRichiestaPrestito a = new AzioneRichiestaPrestito(f, main);
		a.azioneRichiestaPrestito(ConstantsRisorsa.LIBRO, l);
		
		AzioneRichiestaProroga proroga = new AzioneRichiestaProroga(f, main);
		proroga.proroga(f.getPrestiti().get(0),ConstantsRisorsa.LIBRO, LocalDateTime.now()); //TEST SENZA MODIFICA
		
		assertTrue(f.getPrestiti().size() == 1 && f.getPrestiti().get(0).getNumero_rinnovo()==0);
		//NON PROROGA PERCHE' DEVO ASPETTARE DA DUE MINUTI IN POI 
	}
	
	@org.junit.Test
	public void prorogaLibroPossibile() {

		Controller main = new Controller();
		RisorsaEntityController rec = main.getRisorsaControllerByKey(ConstantsRisorsa.LIBRO);

	//	RisorsaEntityController rec = main.getLibroController();
		Fruitore f = new Fruitore("marika", 23);
		Libro l = new Libro("Mattia", "Pirandello", "IT", "COM", "MOND", 1923, 322, 1);
		rec.aggiungiRisorsa(l);
		
		AzioneRichiestaPrestito a = new AzioneRichiestaPrestito(f, main);
		a.azioneRichiestaPrestito(ConstantsRisorsa.LIBRO, l);
		
		AzioneRichiestaProroga proroga = new AzioneRichiestaProroga(f, main);//TEST SENZA MODIFICA
		proroga.proroga(f.getPrestiti().get(0),ConstantsRisorsa.LIBRO, LocalDateTime.now().plusMinutes(3));
		
		assertTrue(f.getPrestiti().size() == 1 && f.getPrestiti().get(0).getNumero_rinnovo()==1);
	}
	

	@org.junit.Test
	public void aggiungiFruitoreMinorenne() {
		Fruitore f = new Fruitore("MARIKA", 12);
	
		Controller main = new Controller();
		main.getFruitore().getFruitoreModel().aggiungiFruitore(f); //TEST SENZA MODIFICA
		
		assertTrue(main.getFruitore().getFruitoreModel().getFruitori().size()==0);
	}
	
	@org.junit.Test
	public void aggiungiFruitoreMaggiorenne() {
		Fruitore f = new Fruitore("MARIKA", 22);
	
		Controller main = new Controller();
		main.getFruitore().getFruitoreModel().aggiungiFruitore(f);//TEST SENZA MODIFICA
		
		assertTrue(main.getFruitore().getFruitoreModel().getFruitori().size()==1);
	}
	
	

	
	@org.junit.Test
	public void rinnovo() {
		Fruitore f = new Fruitore("MARIKA", 20); 
		Controller main = new Controller();
		FruitoreController controller = new FruitoreController(main);
		controller.getFruitoreModel().aggiungiFruitore(f);
		
		controller.rinnovoIscrizione(f); //TEST SENZA MODIFICA
		
		assertTrue(Data.controlloDataNelPassato(f.getScadenzaIscrizione()) == false);
	}
	
	@org.junit.Test
	public void ricercaPerTitolo() {
	
		Libro l = new Libro("Mattia Pascal", "Pirandello", "IT", "COM", "MOND", 1923, 322, 1);
		Controller main = new Controller();
		RisorsaEntityController rec = main.getRisorsaControllerByKey(ConstantsRisorsa.LIBRO);

		//RisorsaEntityController rec = main.getLibroController();
		rec.aggiungiRisorsa(l);

		List<Risorsa> risorse = rec.ricercaTitolo("The Secret"); //TEST SENZA MODIFICA
		//List <Risorsa> risorse = main.getOperatore().ricercaTitoloPerTest("The Secret", 0);
		
		assertTrue(risorse.size()==0 );
	}
	
	@org.junit.Test
	public void ricercaPerGenere() {
	
		Film l = new Film("Marika", "Marika", 123, 1000, "MERDADORI", "IT", "COM" ,1);
		Controller main = new Controller();
		RisorsaEntityController rec = main.getRisorsaControllerByKey(ConstantsRisorsa.FILM);

		//RisorsaEntityController rec = main.getFilmController();
		rec.aggiungiRisorsa(l);

		List<Risorsa> risorse = rec.ricercaGenere("COM"); //TEST SENZA MODIFICA
		//List <Risorsa> risorse = main.getOperatore().ricercaGenerePerTest("COM", 1);
		
		assertTrue(risorse.size()==1 );
	}

	//NON FUNZIONANO
	
	@org.junit.Test
	public void ricercaPerAutore() {
	
		Libro l = new Libro("Mattia Pascal", "Pirandello", "IT", "COM", "MOND", 1923, 322, 1);
		Controller main = new Controller();
		RisorsaEntityController rec = main.getRisorsaControllerByKey(ConstantsRisorsa.LIBRO);
		//RisorsaEntityController rec = main.getLibroController();
		rec.aggiungiRisorsa(l);

		List <Risorsa> risorse = ( (LibroEntityController) rec ).ricercaAutore("Pirandello");
		
		//List <Risorsa> risorse = main.getOperatore().ricercaAutoreSenzaInput("Pirandello");
		
		assertTrue(risorse.size()==1 );
	}
	
	@org.junit.Test
	public void ricercaPerRegista() {
	
		Film l = new Film("Marika", "Marika", 123, 1000, "MERDADORI", "IT", "COM", 1);
		Controller main = new Controller();
		RisorsaEntityController rec = main.getRisorsaControllerByKey(ConstantsRisorsa.FILM);
		//RisorsaEntityController rec = main.getFilmController();
		rec.aggiungiRisorsa(l);

		List <Risorsa> risorse = ( (FilmEntityController) rec ).ricercaRegista("Marika");
		//List <Risorsa> risorse = main.getOperatore().ricercaRegistaSenzaInput("Marika");
		
		assertTrue(risorse.size()==1 );
	}
	
	@org.junit.Test
	public void aggiungiLibro() {
		Controller main = new Controller();
		RisorsaEntityController rec = main.getRisorsaControllerByKey(ConstantsRisorsa.LIBRO);
		RisorsaEntityController recF = main.getRisorsaControllerByKey(ConstantsRisorsa.FILM);
//		RisorsaEntityController rec = main.getLibroController();
//		RisorsaEntityController recF = main.getFilmController();
		Libro l = new Libro("Mattia", "Pirandello", "IT", "COM", "MOND", 1923, 322, 2);
		rec.aggiungiRisorsa(l); //TEST SENZA MODIFICA
		
		assertTrue(rec.getRisorsaModel().getArchivio().size() ==1 &&
				recF.getRisorsaModel().getArchivio().size()==0);
	}
	
	
	@org.junit.Test
	public void aggiungiFilm() {
		Controller main = new Controller();
		RisorsaEntityController recF = main.getRisorsaControllerByKey(ConstantsRisorsa.FILM);
		//RisorsaEntityController recF = main.getFilmController();
		RisorsaEntityController rec = main.getRisorsaControllerByKey(ConstantsRisorsa.LIBRO);	
		//RisorsaEntityController rec = main.getLibroController();
		Film l = new Film("Marika", "Marika", 123, 1000, "MERDADORI", "IT", "COM", 2);
		recF.aggiungiRisorsa(l); //TEST SENZA MODIFICA
		
		assertTrue(recF.getRisorsaModel().getArchivio().size()==1 &&
				rec.getRisorsaModel().getArchivio().size() ==0);
	}
	
	@org.junit.Test
	public void rimuoviFilm() {
		Controller main = new Controller();
		RisorsaEntityController recF = main.getRisorsaControllerByKey(ConstantsRisorsa.FILM);
	//	RisorsaEntityController recF = main.getFilmController();
		Film l = new Film("Marika", "Marika", 123, 1000, "MERDADORI", "IT", "COM", 1);
		recF.aggiungiRisorsa(l);
		
		recF.rimuoviRisorsa(l); //RISPETTO A RIMUOVI PROG PRECEDENTE E' STATO MODIFICATO PER RENDERLO TESTABILE
		assertTrue(recF.getRisorsaModel().getArchivio().size()==0);
	}
	
	@org.junit.Test
	public void numPrestitiPerAnno(){
		Fruitore f = new Fruitore("MARIKA", 19);
		Controller main = new Controller();
		FruitoreController controller = new FruitoreController(main);
		controller.getFruitoreModel().aggiungiFruitore(f);
		
		RisorsaEntityController rec = main.getRisorsaControllerByKey(ConstantsRisorsa.LIBRO);
		//RisorsaEntityController rec = main.getLibroController();
		Libro l = new Libro("Mattia", "Pirandello", "IT", "COM", "MOND", 1923, 322, 2);
		rec.aggiungiRisorsa(l);
		
		AzioneRichiestaPrestito a = new AzioneRichiestaPrestito(f, main);
		a.azioneRichiestaPrestito(ConstantsRisorsa.LIBRO, l);

		
		assertTrue(main.getOperatore().numPrestitiTotali() ==1);
		
	}

	
	@org.junit.Test
	public void numProroghePerAnno(){
		
		Controller main = new Controller();
		FruitoreController controller = new FruitoreController(main);
		Fruitore f = new Fruitore("MARIKA", 19);
		controller.getFruitoreModel().aggiungiFruitore(f);
		
		RisorsaEntityController rec = main.getRisorsaControllerByKey(ConstantsRisorsa.LIBRO);
	//	RisorsaEntityController rec = main.getLibroController();
		Libro l = new Libro("Mattia", "Pirandello", "IT", "COM", "MOND", 1923, 322, 2);
		rec.aggiungiRisorsa(l);
		
		AzioneRichiestaPrestito a = new AzioneRichiestaPrestito(f, main);
		a.azioneRichiestaPrestito(ConstantsRisorsa.LIBRO, l);
		
		AzioneRichiestaProroga proroga = new AzioneRichiestaProroga(f, main);
		proroga.proroga(f.getPrestiti().get(0),ConstantsRisorsa.LIBRO, LocalDateTime.now());
		
		assertTrue(main.getOperatore().numProrogheTotali() ==0);
		
	}
	
	@org.junit.Test
	public void risorsaPiuPrestata(){
		Controller main = new Controller();
		RisorsaEntityController rec = main.getRisorsaControllerByKey(ConstantsRisorsa.LIBRO);
		RisorsaEntityController filmC = main.getRisorsaControllerByKey(ConstantsRisorsa.FILM);
//		RisorsaEntityController rec = main.getLibroController();
//		RisorsaEntityController filmC = main.getFilmController();

		Fruitore f = new Fruitore("marika", 23);
		Libro l = new Libro("Mattia", "Pirandello", "IT", "COM", "MOND", 1923, 322,2); //COSTRUTTORE MODIFICATO CON INSERIMENTO NUM LICENZE PER TESTARE
		Film film = new Film("Marika", "Marika", 123, 1000, "MERDADORI", "IT", "COM", 1);
		
		rec.aggiungiRisorsa(l);
		filmC.aggiungiRisorsa(film);
		
		AzioneRichiestaPrestito a = new AzioneRichiestaPrestito(f, main);
		a.azioneRichiestaPrestito(ConstantsRisorsa.LIBRO, l);
		a.azioneRichiestaPrestito(ConstantsRisorsa.LIBRO, l);
		a.azioneRichiestaPrestito(ConstantsRisorsa.FILM, film);
		
		Map <String, Integer> ris = main.getOperatore().risorsaPiuPrestataTotale();
		//System.out.println(Arrays.asList(ris));
		String key = "LIBRO->Mattia";
		//String key1 = "FILM->Marika";
		
		assertTrue(ris.get(key)==2 &&  ris.size()==1);
		
	}
	
	@org.junit.Test
	public void numPrestitiPerFruitore(){
		Controller main = new Controller();
		RisorsaEntityController rec = main.getRisorsaControllerByKey(ConstantsRisorsa.LIBRO);
		RisorsaEntityController filmC = main.getRisorsaControllerByKey(ConstantsRisorsa.FILM);
//		RisorsaEntityController rec = main.getLibroController();
//		RisorsaEntityController filmC = main.getFilmController();

		Fruitore f = new Fruitore("marika", 23);
		f.setId("mari21");
		
		main.getFruitore().getFruitoreModel().aggiungiFruitore(f);
		
		Libro l = new Libro("Mattia", "Pirandello", "IT", "COM", "MOND", 1923, 322,2); //COSTRUTTORE MODIFICATO CON INSERIMENTO NUM LICENZE PER TESTARE
		Film film = new Film("Marika", "Marika", 123, 1000, "MERDADORI", "IT", "COM", 1);
		
		rec.aggiungiRisorsa(l);
		filmC.aggiungiRisorsa(film);
		
		AzioneRichiestaPrestito a = new AzioneRichiestaPrestito(f, main);
		a.azioneRichiestaPrestito(ConstantsRisorsa.LIBRO, l);
		a.azioneRichiestaPrestito(ConstantsRisorsa.FILM, film);
		
		Map <String, Integer> ris = main.getFruitore().numPrestitiPerFruitore();
		System.out.println(Arrays.asList(ris));
		String key = "mari21 marika"; //STAMPA ID E NOME E POI TI ESCE = NUMERO DI PRESTITI
		
		
		assertTrue(ris.get(key)==2 &&  ris.size()==1);//RIS HO UN SOLO FRUITORE //SIZE QUANTE COPIE HO NEL MIO HASHMAP
		
	}

	

	
	
//	@org.junit.Test
//	public void stampaPrestiti() {
//		LoginController main = new LoginController();
//		RisorsaEntityController rec = main.getFilmController();
//		Fruitore f = new Fruitore("marika", 23);
//		Film l = new Film("Marika", "Marika", 123, 1000, "MERDADORI", "IT", "COM");
//		rec.aggiungiRisorsa(l);
//		AzioneRichiestaPrestito a = new AzioneRichiestaPrestito(f, main);
//		a.rifatto(ConstantsRisorsa.FILM, l);
//		
//		// Create a stream to hold the output
//	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//	    PrintStream ps = new PrintStream(baos);
//	    // IMPORTANT: Save the old System.out!
//	    PrintStream old = System.out;
//	    // Tell Java to use your special stream
//	    System.setOut(ps);
//	    // Print some output: goes to your special stream
//	    main.getFruitore().stampaPrestitiAttuali(f);
//	    // Put things back
//	    System.out.flush();
//	    System.setOut(old);
//	    
////	    String s = ''
////	    		
////	    		
////	    		"L'ARCHIVIO E' VUOTO\n"
////	    		"FRUITORE:  | CATEGORIA |         TITOLO |  AUTORE/REGISTA |      INIZIO PRESTITO |        FINE PRESTITO | PROROGA |
////	    		1      marika |     FILM |          Marika |          Marika |  28-12-2018 17-48-32 |  28-12-2018 17-51-32 | NO"
//
//	    System.out.println(baos.toString());
//		fail(baos.toString());
//	}
	
	
	
}
