package homework02MyIntTree;

public class MyIntTree implements IntTree {

	private int numero;
	private NodeList children;
	static int height;
	static int nodes;
	
	MyIntTree(int n) { // costruttore
		this.numero = n;
		this.children = new NodeList();
	}
	
	public MyIntTree() {} //secondo costruttore
	
	public NodeList getChildren() {return this.children;} //ritorna la NodeList

	@Override
	public int getValue() {return this.numero;} // restituisce il valore associato

	@Override
	public int childrenNumber() {return children.nChildren();} // restituisce il numero di figli (0 se e’ una foglia)

	@Override
	public int nodes() {
		IntTree nodotmp = new MyIntTree();
		nodes = nodes + this.childrenNumber();
		if (this.childrenNumber() != 0)
		{
			for(int i = 0; i<this.childrenNumber();i++)
			{
				nodotmp = children.selezionaNodo(i);
				nodotmp.nodes();
			}	
		}
		return nodes + 1;
	}

	@Override
	public int height() {
		IntTree nodotmp = new MyIntTree();
		if (this.childrenNumber() != 0) 
		{
			height++;
			for (int i = 0; i < this.childrenNumber(); i++) 
			{
				nodotmp = this.children.selezionaNodo(i);
				nodotmp.height();
			}
		}
		return height;
	}

	@Override
	public boolean equals(IntTree t) { //t1.equals(t2) e’ true se t1 e t2 sono isomorfi, ovvero indistunguibili
		boolean ris = false;
		String th = this.visitToString("");
		String tt = ((MyIntTree) t).visitToString("");
		if (th.equals(tt))
            ris = true;
		return ris;
	}

	@Override
	public void addChild(IntTree child) {children.aggiungiNod(child);} // aggiunge child come figlio

	@Override
	public IntTree followPath(int[] path) throws NoSuchTreeException
	{
		MyIntTree tmp = (MyIntTree)this;
        for (int i = 0; i < path.length; i++)
        {
            if (tmp.children.nChildren() < path[i]) throw new NoSuchTreeException();
            tmp = (MyIntTree) tmp.children.selezionaNodo(path[i]-1);
        }
        return tmp;	
	}
	
	public String visitToString(String s)
	{
		s = s + getValue() + " ";
		NodeList tmp = getChildren();
		for (int i = 0; i < childrenNumber(); i++)
			s = ((MyIntTree) tmp.selezionaNodo(i)).visitToString(s);
        return s;
	}

    @Override
    public void visit() {System.out.println(visitToString(""));}
	
    public String printInt(String s)
    {
    	s = s+ getValue();
        if (childrenNumber()!=0)
           s.concat("(");
        for(int i=0; i<childrenNumber(); i++) 
          s = ((MyIntTree) getChildren().selezionaNodo(i)).printInt(s);
        if (childrenNumber()!=0)
            s.concat(")");
    	return s;
    }
    
	@Override
	public void printIntTree() 
	{
		System.out.print(this.numero);
        if (childrenNumber()!=0)
            System.out.print("(");
        for(int i=0; i<childrenNumber(); i++) 
          getChildren().selezionaNodo(i).printIntTree();
        if (childrenNumber()!=0)
            System.out.print(")");
	}
}

class NodeList {
	private int num; // numero figli
	public Node lista = null;    // primo figlio

	NodeList() {
		this.lista = null;
	} // costruttore

	public int nChildren() {return this.num;} // ritorna il numero dei figli

	public void aggiungiNod(IntTree n) { // aggiunge un figlio all'albero
		lista = new Node(n, lista);
		num ++; 
	}

	public IntTree selezionaNodo(int n) {
		Node q = this.lista;
		for (int k = 0; k < n; k++) q = q.prossimo();
		return q.Ramo();
	}
	
	public String toString()
	{
		StringBuffer r = new StringBuffer();
		r.append("[" + this.num + "] -> [");
		for (int i = 0; i<this.nChildren();i++)
			r.append(this.selezionaNodo(i).getValue()+ ".");			
		r.append("]");	
		return r.toString();
	}
}

class Node 
{
	private IntTree child;
	private Node other;
	
	Node(IntTree h, Node n) 
	{
		this.child = h;
		this.other = n;
	}
	
	public Node(IntTree h) {this.child = h;}
	
	public IntTree Ramo() {return this.child;} // ritorna l'oggetto IntTree
	
	public void modifica(IntTree u){this.child = u;}
	
	public void concat(Node u) 
	{ // setta il nodo successivo
		 if (other == null) other = u;
		 else other.concat(u);
	} 
	
	public Node prossimo() {return this.other;} // ritorona il prossimo nodo
}