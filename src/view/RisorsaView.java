package view;


import java.util.List;


import entity.Risorsa;
import mylib.Constants;
import mylib.InputDati;


public abstract class RisorsaView {
	
	
	
	public abstract Risorsa crea();
	
	public abstract String stampa(Risorsa r);
	
	public abstract void stampaCategoria();

	/**	
	 * Metodo selezionaRisorsaPerCategoria utile per metodo selezionaRisorsa.
	 * @param c
	 * @return
	 */	
	public Risorsa selezionaRisorsa(List<Risorsa> archivio){
			Risorsa r = null;
			this.stampaTutti( archivio);
			int valore = InputDati.leggiIntero(Constants.NUM_RISORSA, 1,archivio.size());
			r = archivio.get(valore -1);
			return r;
		}
		
		
	
	/**
	 * Metodo stampaTuttiFilm fornisce a video una rappresentazione di tutti i film
	 * presenti in archivio.
	 * @param film
	 */
		public void stampaTutti(List<Risorsa> archivio) {
			StringBuilder sb = new StringBuilder();
			if(archivio == null || archivio.isEmpty()) {
				stampaErroreArchivio();
			}
			else{
				String stringa = this.stampaConNumeri(archivio);
				sb.append(stringa);
				System.out.println(sb.toString());
			}
		}
		
		/**	
		 * Metodo stampaConNumeri stampa a video un'elenco puntato dell'ArrayList 
		 * passato come parametro.
		 * @param arr
		 * @return
		 */
		public String stampaConNumeri(List<Risorsa> arr){
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < arr.size(); i++){
				sb.append(i+1 + " ");
				sb.append(stampa(arr.get(i)));//TO STRING IN BASE AL TIPO DI RISORSA
				sb.append("\n");
			}
			return sb.toString();
		}
		

		

		
		public void stampaRisorseTrovate(List <Risorsa> risorseTrovate) {
			if(risorseTrovate!=null && !risorseTrovate.isEmpty()) {
				int i = 1;
				System.out.println("Elenco risorse trovate: ");
				for(Risorsa r : risorseTrovate) {
					System.out.println(i++ + " " + stampa(r)+ "\n");
				}

			} else {
				System.out.println(Constants.RISORSA_TITOLO);
			}
		}
		
		public void stampaErroreArchivio(){
			System.out.println(Constants.ERRORE_ARCHIVIO);
		}
		
		public void stampaRisorsaCopieEsaurite(Risorsa r){
			System.out.println(r.getNome() + " " +Constants.COPIE_ESAURITE);
		}
		
}
