package version3;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Scanner;

public class Simulator {
    public static int a, b, c, popolazione_tot, pm, pa, pp, ps, nGenerazione;
    public static volatile ArrayList<Donna_Alpha> listaDonne;
    public static volatile ArrayList<Uomo_Alpha> listaUomini;
    public static boolean SituazioneStabile, EvoluzioneImpossibile;
    public static String situazione;

    static {
        popolazione_tot = pm = pa = pp = ps = nGenerazione = 0;
        SituazioneStabile = EvoluzioneImpossibile = false;
        listaUomini = new ArrayList<Uomo_Alpha>();
        listaDonne = new ArrayList<Donna_Alpha>();
        situazione = "";
    }

    public static boolean getSituazioneStabile() {return SituazioneStabile;} //RITORNA LA SITUAZIONE STABILE

    public static boolean getEvoluzioneImpossibile() {return EvoluzioneImpossibile;} //RITORNA LA SITUAZIONE IMPOSSIBILE

    public static int getNGenerazione() {return nGenerazione;} //RITORNA LA GENERAZIONE

    public static boolean checkDonne() { //VERIFICA SE CI SONO DONNE CHE NON HANNO FATTO ANCORA NULLA
        int check = 0;
        for (Donna_Alpha i : listaDonne)
            if (i.getBAccoppiato())
                check++;
        if (check == listaDonne.size())
            return true;
        else
            return false;
    }

    public static boolean checkUomini() { //VERIFICA SE CI SONO UOMINI CHE NON HANNO FATTO ANCORA NULLA
        int bCheck_u = 0;
        for (Uomo_Alpha i : listaUomini)
            if (i.getBAccoppiato())
                bCheck_u++;
        if (bCheck_u == listaUomini.size())
            return true;
        else
            return false;
    }

    public static void setRisorse(int aa, int bb, int cc, int m, int aaa, int p, int s) { //SETTA LE RISORSE DEL SIMULATORE
        a = aa;
        b = bb;
        c = cc;
        Tool.generaUomo(m, "morigerato");
        Tool.generaUomo(aaa, "avventuriero");
        Tool.generaDonna(p, "prudente");
        Tool.generaDonna(s, "spregiudicata");
        popolazione_tot = popolazione_tot + m + aaa + p + s;
    }

    public static void start() {for (Uomo_Alpha t : listaUomini) t.start();} //PARTONO TUTTI GLI UOMINI

    public static void inizializza() { //INIZIALIZZA IL SIMULATORE
        Scanner sc = new Scanner(System.in);
        System.out.print("inserisci a: ");
        int a = sc.nextInt();
        System.out.print("inserisci b: "); //GESTIONE VARIABILI a,b,c
        int b = sc.nextInt();
        System.out.print("inserisci c: ");
        int c = sc.nextInt();
        System.out.print("inserisci popolazione morigerati: "); 
        int m = sc.nextInt();
        System.out.print("inserisci popolazione avventurieri: ");
        int aa = sc.nextInt();									//GESTIONE POPOLAZIONI
        System.out.print("inserisci popolazione prudenti: ");
        int p = sc.nextInt();
        System.out.print("inserisci popolazione spregiudicate: ");
        int s = sc.nextInt();
        setRisorse(a, b, c, m, aa, p, s);
        sc.close();
        System.out.println("");
    }

    public static void toStringGen() { //SCRIVE IL RESOCONTO DELLA GENERAZIONE APPENA PASSATA
        if (Formule.checkSituazione()) {
            System.out.println("Alla generazione numero " + nGenerazione 
            		+ " la popolazione si è stabilizzata, ottenendo una popolazione formata da: ");
            System.out.println(Formule.percPm() + " morigerati, " + Formule.percPa() + " avventurieri, " 
            		+ Formule.percPp() + " prudenti e " + Formule.percPs() + " spregiudicate.");
        } 
        else{
        	System.out.println("La generzione " + getNGenerazione() + " è passata, sono nate " + Tool.countCoppie() + " coppie, "+ Tool.countPali() + " uomini sono andati a vuoto. Sono nati " 
        + (Tool.countFigl("u")+Tool.countFigl("d")) + " figli. %M: "+Formule.percPm()+", %A: "+Formule.percPa()+", %P: "+Formule.percPp()+", %S: "+Formule.percPs());
       
        }
        		
    }

    public static void devastazione() { //SE LA POPOLZIONE RAGGIUNGE NUMERI TROPPO ALTI, VIENE DIMEZZATA, CERCANDO DI TENERE LE STESSE PERCENTULI
        int prud = Tool.countd("prudente")/2;
        int spreg = Tool.countd("spregiudicata")/2;
        int mor = Tool.countu("morigerato")/2;
        int avv = Tool.countu("avventuriero")/2;
        for(int i = 0; i < listaDonne.size() ; i++){
            if(spreg == 0 && prud == 0) break;
            else{
                Donna_Alpha d = listaDonne.get(i);
                if(d.getType().equals("prudente")){
                    listaDonne.remove(d);
                    prud--;
                    pp--;
                }
                else{
                    listaDonne.remove(d);
                    spreg--;
                    ps--;
                }
            }
        }
        for(int i = 0; i < listaUomini.size() ; i++){
            if(mor == 0 && avv == 0) break;
            else{
                Uomo_Alpha d = listaUomini.get(i);
                if(d.getType().equals("morigerato")){
                    listaUomini.remove(d);
                    mor--;
                    pm--;
                }
                else{
                    listaUomini.remove(d);
                    avv--;
                    pa--;
                }
            }
        }
    }

    public static void creaFigli(){ //CREA LE NUOVE GENERAZIONI IN BASE ALLE FORMULE DEL TASSO DI CRESCITA/DECRESCITA
        int prud = Formule.figli("prudente");
        int spreg = Formule.figli("spregiudicata");
        int mor = Formule.figli("morigerato");
        int avv = Formule.figli("avventuriero");
        for(int i = 0 ; i < prud; i++){
            Donna_Alpha d  = new Donna_Alpha("prudente");
            d.bAccoppiato = true;
            d.generazione = -1;
            listaDonne.add(d);
            pp ++;
        }
        for(int j = 0; j< spreg ; j++){
            Donna_Alpha d = new Donna_Alpha("spregiudicata");
            d.bAccoppiato = true;
            d.generazione = -1;
            listaDonne.add(d);
            ps++;
        }

        for(int i = 0 ; i < mor; i++){
            Uomo_Alpha u = new Uomo_Alpha("morigerato");
            u.bAccoppiato = true;
            u.generazione = -1;
            listaUomini.add(u);
            pm++;
        }
        for(int j = 0 ; j < avv; j++){
            Uomo_Alpha u = new Uomo_Alpha("avventuriero");
            u.bAccoppiato = true;
            u.generazione = -1;
            listaUomini.add(u);
            pa++;
        }
    }

    public static void reset() { //RESETTA DONNE E UOMINI PRIMA DI PROCEDERE VERSO LA NUOVA GENERAZIONE
        for (int i = 0; i < listaDonne.size(); i++) {	//SISTEMA LE DONNE
            Donna_Alpha d = listaDonne.get(i);			
            d.aumentaGenerazione();
            if (d.getGenerazione() > 2) {	//SE HANNO SUPERATO LA GENERAZIONE LIMITE MUOIONO
                listaDonne.remove(d);
                i--;
                if (d.getType().equals("prudente")) pp--;
                else ps--;
            }
            d.figli = 0;
            d.figlie = 0;
            d.bCopulato = false;
            d.bAccoppiato = false;
        }
        for (int i = 0; i < listaUomini.size(); i++) { //SISTEMA GLI UOMINI (SIMILE ALLE DONNE)
            Uomo_Alpha u = listaUomini.get(i);
            u.aumentaGenerazione();
            if (u.getState() == State.NEW)	//I THREAD FIGLI SONO ANCORA INATTIVI, QUINDI BISOGNA FARLI PARTIRE !!
                u.start();
            if (u.getGenerazione() > 2) {	//SE HANNO SUPERATO LA GENERAZIONE LIMITE MUOIONO
                u.interrupt();
                listaUomini.remove(u);
                i--;
                if (u.getType().equals("morigerato")) pm--;
                else pa--;
            }
            u.bCopulato = false;
        }
    }

    public static void main(String[] args) {
        inizializza();
        Formule.checkSituazione();
        double inizio = System.currentTimeMillis();
        while(listaDonne.size() > 100 && listaUomini.size() > 100 ) devastazione();
        start();
        while (SituazioneStabile == false) {
            if (listaDonne.isEmpty() || listaUomini.isEmpty()) {
                if (listaDonne.isEmpty()) System.out.println("Non ci sono Donne !!!");
                if (listaUomini.isEmpty()) System.out.println("Non ci sono Uomini !!!");
                EvoluzioneImpossibile = true;
                for (Uomo_Alpha u : listaUomini) u.interrupt();
                System.out.println("EVOLUZIONE INSTABILE");
                System.out.println("Non c'è evoluzione, con questi parametri si procede verso l'estinzione della specie.");
                break;
            }
            if (Formule.checkSituazione()) {
                for (Uomo_Alpha u : listaUomini) u.interrupt();
                toStringGen();
                SituazioneStabile = true;
                break;
            }
            if (checkUomini()) {
                toStringGen();
                nGenerazione++;
                creaFigli();
                reset();
                if(listaDonne.size() > 200 && listaUomini.size() > 200 ){
                	devastazione();
                    System.out.println("a causa di una guerra la popolazione si è dimezzata. %M: "+Formule.percPm()+", %A: "+Formule.percPa()+", %P: "+Formule.percPp()+", %S: "+Formule.percPs());
                    System.out.println("");
                }
                for (Uomo_Alpha u : listaUomini)
                    u.bAccoppiato = false;
                
            }
        }
        double fine = System.currentTimeMillis();
        System.out.println("Tempo di esecuione: "+ (fine-inizio)/1000+" s.");
    }
}