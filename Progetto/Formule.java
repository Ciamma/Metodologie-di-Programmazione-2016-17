package version3;

public class Formule {
	public static int contatore = 0;

	public static double percPm() {
		return ((Simulator.pm * 100) / (double) (Simulator.listaUomini.size()));
	}

	public static double percPa() {	//PERCENTUALI DELLE POPOLAZIONI
		return ((Simulator.pa * 100) / (double) (Simulator.listaUomini.size()));
	} 

	public static double percPp() {
		return ((Simulator.pp * 100) / (double) (Simulator.listaDonne.size()));
	}

	public static double percPs() {
		return ((Simulator.ps * 100) / (double) (Simulator.listaDonne.size()));
	}

	public static double formulaD() { //FORMULE RISOLUTORE
		return (double) (50 * Simulator.b) / (Simulator.a - Simulator.c);
	} 

	public static double formulaU() {
		return (double) (100 * (Simulator.a - Simulator.b)) / (Simulator.a - Simulator.b - Simulator.c);
	}

	public static boolean checkPerc(double c, double d) { //VERIFICA SE E' SITUAZIONE STABILE
		if (c > (d - 1) && c < (d + 1) ) return true;
		else return false;
	}
	
	public static double Dawkins(String t){ //CALCOLA LA STABILITà DI DAWKINS IN BASE AI VALORI INIZIALI a,b,c
		double ris = 0;
        switch(t){
        	case "morigerato": 
        		ris = ((Simulator.a - Simulator.b)) / (double)(Simulator.a - Simulator.b - Simulator.c);
        		break;
        	
        	case "avventuriero":
        		ris =  1 - Dawkins("morigerato");
        		break;
        	case "prudente":
				ris =  Simulator.b / (double)(2 * (Simulator.a - Simulator.c));
				break;
			case "spregiudicata":
				ris = 1 - Dawkins("prudente");
				break;
        }
        return ris;
        	
	}

	public static boolean checkSituazione() { //FA IL CHECK DELLE PERCENTUALI DELLE POPOLAZIONI PER VERIFICARE LA SITUAZIONE STABILE
		if (checkPerc(percPm(), formulaU()) && checkPerc(percPp(), formulaD())) {
			contatore ++;
			if(contatore > 5){
				Simulator.SituazioneStabile = true;
				return true;
			}
			else return false;
		} 
		else{
			contatore = 0;
			return false;
		}
	}
	
	public static double newPop(String t){	//ELABORA IL TASSO DI CRESCITA/DECRESCITA DELLA POPOLAZIONE
		double ris;
		double x = percPm()/100;
		double y = percPp()/100;
		if(t.equals("morigerato")) ris = x * (x - Dawkins("morigerato")) * (x - 1);
		else ris = y * (y - Dawkins("prudente")) * (y - 1);
		return ris;
	}
	
	public static int figli(String t){	//CREA I FIGLI IN BASE AL TASSO DI CRESCITA/DECRESCITA DELLA POPOLAZIONE
		double mor = newPop("morigerato")*percPm() + percPm();
		double prud = newPop("prudente")*percPp() + percPp();
		
		double x = (mor/100) * (Simulator.pm + Simulator.pa + Tool.countFigl("u") );	// MORIGERATI
		if(x - (int) x > 0.5) x = x + 1;
		
		double y =  (prud/100) * (Simulator.pp + Simulator.ps + Tool.countFigl("d") );	//PRUDENTI
		if (y - (int) y > 0.5) y = y + 1;
		
		if(t.equals("morigerato")) return (int) ( x - Simulator.pm);
		
		if(t.equals("avventuriero")) return (int) (Tool.countFigl("u") - ( x - Simulator.pm) );
		
		if(t.equals("prudente")) return (int) (y - Simulator.pp); 
		
		else return (int) (Tool.countFigl("d") - (y - Simulator.pp) );
	}
	
	public static void stampaFiglio(){
		double mor = (newPop("morigerato")*percPm() + percPm());
		double avv = 100 - mor;
		double pru = (newPop("prudente")*percPp() + percPp());
		double spre = 100 - pru;
		System.out.println("nuova percentuale uomini morigerati: " + mor + ", nuova percentuale uomini avventurieri: "+ avv );
		System.out.println("nuova percentuale donne prudenti: " + pru +", nuova percentuale donne spregiudicate: "+ spre );
	}
}
