package version3;

public class Tool {
	
	// GENERANO I PRIMI UOMINI E DONNE CHE DARANNO INIZIO AL SIMULATORE	
	public synchronized static void generaUomo(int n, String t) {
		for (int i = 0; i < n; i++) {
			Uomo_Alpha m = new Uomo_Alpha(t);
			Simulator.listaUomini.add(m);
			if (m.getType().equals("morigerato"))
				Simulator.pm++;
			else
				Simulator.pa++;
		}
	}

	public static void generaDonna(int n, String t) { 
		for (int i = 0; i < n; i++) {
			Donna_Alpha m = new Donna_Alpha(t);
			Simulator.listaDonne.add(m);
			if (m.getType().equals("prudente"))
				Simulator.pp++;
			else
				Simulator.ps++;
		}
	}
	
	/* COUNTER DEL SIMULATORE: CONTATORE DELLE COPPIE, DEGLI AVVENTURIERI CHE HANNO INCONTRATO
	UNA PRUDENTE, DEI FIGLI, TIPOLOGIE UOMINI E DONNE */
	
	public static int countCoppie() {
		int accoppiati = 0;
		for (Uomo_Alpha u : Simulator.listaUomini)
			if (u.bAccoppiato == true && u.bCopulato == true)
				accoppiati++;
		return accoppiati;
	}

	public static int countPali() {
		int coppie_sbagliate = 0;
		for (Uomo_Alpha u : Simulator.listaUomini)
			if (u.bAccoppiato == true && u.getBCopulato() == false)
				coppie_sbagliate++;
		return coppie_sbagliate;
	}

	public static int countFigl(String m){
		int figl = 0;
		if(m.equals("u")) for(Donna_Alpha d: Simulator.listaDonne) figl = figl + d.figli;
		else for(Donna_Alpha d: Simulator.listaDonne) figl = figl + d.figlie;
		return figl;
	}

	public static int countu(String c) {
		int u = 0;
		for (Uomo_Alpha m : Simulator.listaUomini) if (m.getType().equals(c)) u++;
		return u;
	}

	public static int countd(String c) {
		int d = 0;
		for (Donna_Alpha u : Simulator.listaDonne) if (u.getType().equals(c)) d++;
		return d;
	}
	
	
}
