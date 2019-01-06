package mylib;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Data {
	//static
	private static  LocalDateTime data;
	private static String dataString;
	
	public static LocalDateTime creaData(){
		data = LocalDateTime.now();
		return data;
	}
	
	public static String convertoData(LocalDateTime d){
		 dataString = d.format(DateTimeFormatter.ofPattern("dd-MM-yyy HH-mm-ss"));
		 return dataString;
	}
	//da cancellare
	public static LocalDateTime cambiaAnno(int a){
		return data.plusYears(a);
	}

	public static LocalDateTime cambiaAnno(int a, LocalDateTime r){
		return r.plusYears(a);
	}
	public static LocalDateTime cambiaGiorno(int g, LocalDateTime t){
		return t.minusDays(g);
	}
	
	public static LocalDateTime cambiaMinuto(int m, LocalDateTime t){
		return t.plusMinutes(m);
	
	}
	
	public static LocalDateTime menoMinuto(int m, LocalDateTime t){
		return t.minusMinutes(m);
	}
	
	public static boolean controlloDataNelPassato(LocalDateTime data) {
		LocalDateTime la = LocalDateTime.now();//METODO CONTROLLO SCADENZA PER TUTTI MODIFICATO
		if (la.isAfter(data)) {
			return true;
		}
		return false;
	}
}
