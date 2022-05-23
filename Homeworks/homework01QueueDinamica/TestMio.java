package homework01QueueDinamica;

import java.util.Scanner;

public class TestMio 
{
	Scanner sc = new Scanner(System.in);
	Coda c = new Coda();
	
	public void menu()
	{
		
		System.out.println("Cosa vogliamo farci con questa lista ?");
		System.out.println(" ");
		System.out.println("1- Inserisci numeri");
		System.out.println("2- Estrai numeri");
		System.out.println("3- testcode(Test professore)");
		System.out.println("4- Visualizza coda");
		System.out.println("5- chiudi");
	}
	
	
	public void inserisciNumero()
	{
		System.out.print("Quanti numeri vuoi inserire?: ");
		int n = sc.nextInt();
				
		for (int i = 0; i < n; i++)
			{
				System.out.print("Inserire il " + (i+1) +"° numero: ");
				int num = sc.nextInt();
				c.inserisci(num);
				System.out.println("Un numero è stato inserito. Adesso la coda è " + c.toString() );
				System.out.println(" ");
			}
	}
	
	public void estraiNumero()
	{
		System.out.print("Quanti numeri vuoi far uscire?: ");
		int deq = sc.nextInt();
		
		for (int k = 0; k < deq ; k++)
			{
				try 
				{
					System.out.println("Il " + (k+1) +"° numero ad uscire è: " + c.estrai()+", la coda ora è così: "+c.toString());
				} 
				catch (Coda_Vuota_Exception e) 
				{
					System.out.println("Ma che stai facendo, la coda è vuota!!! ");
					break;
				}
			}
		System.out.println("dopo averci lavorato, la coda che hai tra le mani è vuota?: " + c.is_empty());
		System.out.println(" ");
	}
	
	
	public void testProfessore()
	{
		System.out.println("");
		Coda c = new Coda();
		if (c.is_empty())
			System.out.println("coda inizialmente vuota");
		try {
			c.inserisci(7);
			c.inserisci(9);
			System.out.println(c.estrai()); // stampa 7
			c.estrai();
			c.estrai();
		} catch (Coda_Vuota_Exception e) {
			System.out.println("eccezione catturata!");
		}
		System.out.println("");
	}
	
	public static void main(String[] args) 
	{
		TestMio n = new TestMio();
		Scanner s = new Scanner(System.in);
		int choice = 0; 
		while (choice != 5) 
		{
			n.menu();
			System.out.print("Inserire scelta: ");
			choice = s.nextInt();
			if (choice == 1) n.inserisciNumero();
			if (choice == 2) n.estraiNumero();
			if (choice == 3) n.testProfessore();
			if (choice == 4) System.out.println(n.c.toString());	
		}
		
		s.close();	
	}
}

