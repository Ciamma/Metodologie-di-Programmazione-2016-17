package esonero1;

public class MyBinTree implements BinTree {

	private int radice;
	private MyBinTree leftChild;
	private MyBinTree rightChild;
	static StringBuffer s = new StringBuffer();
	
	public MyBinTree(int radice, MyBinTree t1,MyBinTree t2)
	{ // costruttore MyBinTree
		this.radice = radice;
		this.leftChild = t1;
		this.rightChild = t2;
	}
	
	public MyBinTree(int radice) { //costruttore in caso sia foglia
		this.radice = radice;
		this.leftChild = null;
		this.rightChild = null;
	}
	
	public boolean hasChildrenLeft() {return this.leftChild != null;}  // metodo che controlla se ci sono figli sinistri
	public boolean hasChildrenRight() {return this.rightChild != null;} // metodo che ritorna se ci sono figli destri
	
	@Override
	public int root() {return this.radice;} // ritorna l'int radice

	@Override
	public BinTree leftChild() {return this.leftChild;} // ritorna l'oggetto figlio sinistro

	@Override
	public BinTree rightChild() {return this.rightChild;} // ritorna l'oggetto figlio destro

	@Override
	public void flip() // metodo che scambia figlio sinistro e figlio destro
	{
		MyBinTree tmp = this.leftChild;
		leftChild = rightChild;
		rightChild = tmp;
		if (hasChildrenLeft() == true) leftChild.flip();
		if (hasChildrenRight() == true) rightChild.flip();		
	}
	
	public String toString(){ // toString fatto da me per stampare l'albero binario
		
		s.append("(" + this.radice);
		if (hasChildrenLeft() == true) leftChild.toString();
		if (hasChildrenRight() == true) rightChild.toString();
		else s.append(")");
		s.append(")");
		return s.toString();
	}
	
}