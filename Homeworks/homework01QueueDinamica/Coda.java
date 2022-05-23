package homework01QueueDinamica;

public class Coda 
{
	private int lunghezza;
	public Queue testa;
	public Queue coda;
	
	Coda()
	{
		this.testa = null;
		this.coda = null;
		this.lunghezza = 0;
	}
	
	public boolean is_empty () {return lunghezza == 0;} //restituisce true se l'oggetto Coda è vuoto, false altrimenti
	
	public void inserisci(int b)
	{ // inserisce un intero nella coda
		Queue tmp = new Queue(b);
		if(this.is_empty() == true) 
		{
			testa = tmp;
			coda = tmp;
		}
        else 
        {
        	coda.concat(tmp);
        	coda = tmp;        	
        }
		this.lunghezza ++;
	}
	
	
	public int estrai() throws Coda_Vuota_Exception
	{// estrae un elemento dalla coda
		Queue estratto = testa;
		if(this.is_empty() == true) throw new Coda_Vuota_Exception();
		else
		{
			testa = testa.prossimo();
			this.lunghezza --;
		}
		
		return estratto.ritornaNum();
	}
}

class Queue 
{
	private int num;
	private Queue link;
	
	Queue (int w) {this.num = w;} //costruttore
	
	public void concat(Queue u){this.link= u;} //setta l'oggetto Queue al link dell'oggetto
	
	public int ritornaNum(){return this.num;} //ritorna il numero dell'oggetto Queue 
	
	public Queue prossimo() {return this.link;} //ritorna la Queue successiva
}