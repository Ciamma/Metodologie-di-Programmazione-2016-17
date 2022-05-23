package esonero2;

public class MyRunner extends Runner{

	public MyRunner(String nome, Pista p, int delay){
		super(nome, p, delay);
	}
	
	public void run(){
		while(!p.arrivato()){
			p.step();
			try {
				sleep(delay);
			} catch (InterruptedException e) {
				System.out.println("catturato");
				break;
			}
			p.leave();
			p = p.getNext();
			if(p.arrivato()) break;
		}	
	}
}
