package homework02MyIntTree;

public class Test {
	public static void main(String[] args) throws NoSuchTreeException {
		MyIntTree t = new MyIntTree(7); // crea un albero con un solo nodo etichettato 
		System.out.println(t.nodes()); // stampa 1 
		System.out.println(t.height()); // stampa 0
		MyIntTree t1 = new MyIntTree(9);
		MyIntTree t2 = new MyIntTree(9);
		t1.addChild(new MyIntTree(5));
		t2.addChild(new MyIntTree(5));
		System.out.println(t1.equals(t2)); // stampa true
		t1.addChild(t2);
		t1.addChild(new MyIntTree(3));
		t1.visit(); // stampa 9 3 9 5 5
		int[] path = {2,1};
		t.addChild(t1.followPath(path));
		t.visit(); // stampa 7 5
		System.out.println(t1.nodes());
		//t.followPath(path); // lancia NoSuchTreeException
	}

}
