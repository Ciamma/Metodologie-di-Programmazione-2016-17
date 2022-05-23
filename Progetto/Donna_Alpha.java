package version3;

public class Donna_Alpha implements Persona{
	public int generazione;
	private String type;
	public boolean bCopulato;
	public volatile boolean bAccoppiato;
	public int figli;
	public int figlie;
	
	public Donna_Alpha(String a){ //COSTRUTTORE
		this.type = a;
		this.generazione = 0;
		this.bCopulato = false;		// BOOLEANO CHE CI DICE SE L'INDIVIDUO HA COPULATO
		this.bAccoppiato = false;	// BOOLEANO CHE CI DICE SE HA AVUTO UNA RELAZIONE NELLA DETERMINATA GENERAZIONE
		this.figli = 0;
		this.figlie = 0;
	}
	
	@Override
	public int getGenerazione() {return this.generazione;} //RITORNA LA GENERAZIONE (L'età)

	@Override
	public boolean getBAccoppiato() {return this.bAccoppiato;} //RITORNA IL bAccoppiato

	@Override
	public boolean getBCopulato() {return this.bCopulato;} //RITORNA IL bCopulato

	@Override
	public String getType() {return this.type;} //RITORNA IL TIPO DI DONNA
	
	@Override
	public void aumentaGenerazione(){this.generazione++;} //AUMENTA DI UNO L'età DELLA DONNA
	
	public void generaFigl(String t){	//METODO PER AUMENTA IL NUMERO DI FIGLI/E
		if(t.equals("u")) figli++;
		else figlie++;
	}
	
	public void creaFigli(Uomo_Alpha u) {	//METODO CHE GENERA FIGLI/E DALL'ACCOPPIAMENTO CON UN UOMO
		int n = 1 + (int)(Math.random() * 3);
		for(int i = 0;i<n; i++){
			int q = (int)(Math.random()*2); ;
			if(q == 1) generaFigl("u");
			else generaFigl("d");
		}
	}	
}
