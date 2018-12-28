package test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import action.AzioneRichiestaPrestito;
import controller.LoginController;
import controller.entitycontroller.LibroEntityController;
import controller.entitycontroller.RisorsaEntityController;
import entity.Film;
import entity.Fruitore;
import entity.Libro;
import entity.Risorsa;
import mylib.ConstantsRisorsa;

public class Test {

	@org.junit.Test
	public void prestitoLibro() {
		LoginController main = new LoginController();
		RisorsaEntityController rec = main.getLibroController();
		Fruitore f = new Fruitore("marika", 23);
		Libro l = new Libro("Mattia", "Pirandello", "IT", "COM", "MOND", 1923, 322);
		rec.aggiungiRisorsa(l);
		AzioneRichiestaPrestito a = new AzioneRichiestaPrestito(f, main);
		a.rifatto(ConstantsRisorsa.LIBRO, l);
		assertTrue((f.getPrestitiPerTipo(ConstantsRisorsa.LIBRO, f.getPrestiti()).size() == 1) &&
				(f.getPrestitiPerTipo(ConstantsRisorsa.FILM, f.getPrestiti()).size() == 0));
	}

	@org.junit.Test
	public void prestitoFilm() {
		LoginController main = new LoginController();
		RisorsaEntityController rec = main.getFilmController();
		Fruitore f = new Fruitore("marika", 23);
		Film l = new Film("Marika", "Marika", 123, 1000, "MERDADORI", "IT", "COM");
		rec.aggiungiRisorsa(l);
		AzioneRichiestaPrestito a = new AzioneRichiestaPrestito(f, main);
		a.rifatto(ConstantsRisorsa.FILM, l);
		assertTrue((f.getPrestitiPerTipo(ConstantsRisorsa.LIBRO, f.getPrestiti()).size() == 0) &&
				(f.getPrestitiPerTipo(ConstantsRisorsa.FILM, f.getPrestiti()).size() == 1));
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
