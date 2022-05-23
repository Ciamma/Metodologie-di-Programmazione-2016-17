package version3;

import java.util.ArrayList;

public class Uomo_Alpha extends Thread implements Persona {
	public int generazione;
	private String type;
	public boolean bCopulato;
	public volatile boolean bAccoppiato;
	
	Uomo_Alpha(String t){
		this.type  = t;
		this.generazione = 0;
		this.bCopulato = false;		// BOOLEANO CHE CI DICE SE L'INDIVIDUO HA COPULATO
		this.bAccoppiato = false;	// BOOLEANO CHE CI DICE SE HA AVUTO UNA RELAZIONE NELLA DETERMINATA GENERAZIONE
	}
	
	@Override
	public void aumentaGenerazione(){this.generazione++;} //AUMENTA DI UNO LA GENERAZIONE DELL'UOMO

	@Override
	public int getGenerazione() {return this.generazione;} //RITORNA LA GENERAZIONE (L'età)

	@Override
	public boolean getBAccoppiato() {return this.bAccoppiato;} //RITORNA IL bAccoppiato

	@Override
	public boolean getBCopulato() {return this.bCopulato;} //RITORNA IL bCopulato

	@Override
	public String getType() {return this.type;} //RITORNA IL TIPO DI UOMO
	
	public synchronized ArrayList<Donna_Alpha> avaiable(){ //RITORNA UNA LISTA DI DONNE CHE NON SI SONO ANCORA ACCOPPIATE
		ArrayList<Donna_Alpha> a = new ArrayList<>();
		for(int i = 0; i<Simulator.listaDonne.size();i++) if(Simulator.listaDonne.get(i).bAccoppiato == false) a.add(Simulator.listaDonne.get(i));
		return a;
	}
	
	public synchronized Donna_Alpha cercadonna(ArrayList<Donna_Alpha> a){ //L'UOMO SCEGLIE CASUALMENTE UNA DONNA TRA LE DISPONIBILI
		Donna_Alpha d = new Donna_Alpha("Snaporaz");
		ArrayList<Donna_Alpha> donne = avaiable();
		while(d.getType().equals("Snaporaz")){
			if(donne.isEmpty()){
				break;
			}	
			else{
				int random  = (int)(Math.random()*donne.size()); 
				d = donne.get(random);
			}
		}
		d.bAccoppiato = true;
		return d;
	}
	
	public synchronized void Copula(Donna_Alpha d) { //L'UOMO COPULA CON LA DONNA
		if (this.getType()== "morigerato"){
			if (d.getType() == "prudente"){			//INCONTRO TRA UN MORIGERATO E UNA PRUDENTE
				d.creaFigli(this);
				this.bCopulato=true;				//SETTA LA COPULAZIONE A TRUE
				d.bCopulato = true;
			}
			else{									//INCONTRO TRA UN MORIGERATO E UNA SPREGIUDICATA
				d.creaFigli(this);
				this.bCopulato=true;			//SETTA LA COPULAZIONE A TRUE
				d.bCopulato = true;
			}
		}
		else {
			if (d.getType() == "spregiudicata"){		//INCONTRO TRA UN AVVENTURIERO E UNA SPREGIUDICATA
				d.creaFigli(this);
				this.bCopulato=true;			//SETTA LA COPULAZIONE A TRUE
				d.bCopulato = true;
			}
			else{
			}                   //INCONTRO TRA UN AVVENTURIERO E UNA PRUDENTE
		}
		this.bAccoppiato = true;
	}
	
	public synchronized void run() {
		while(!Simulator.getSituazioneStabile()){
			if(bAccoppiato == false){
				Donna_Alpha d = cercadonna(Simulator.listaDonne);
				if(d.getType().equals("Snaporaz")) {
					this.bAccoppiato = true;
				}
				else{
					this.Copula(d);
					this.bAccoppiato = true;
				}	
			}
			else{
				try {
					sleep(10);
				} catch (InterruptedException e) { if(isInterrupted()) break;}
			}
		}
	}

}
