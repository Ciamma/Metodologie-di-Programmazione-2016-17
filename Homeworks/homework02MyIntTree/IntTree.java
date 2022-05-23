package homework02MyIntTree;

public interface IntTree {// contratto degli alberi di interi
	
	public int getValue(); // restituisce il valore associato alla radice     

	public int childrenNumber(); // restituisce il numero di figli di this (0 se e’ una foglia)
	
	public int nodes(); // restituisce il numero di nodi di this; 1 se this una foglia   

	public int height();// restituisce la lunghezza del cammino piu’ lungo dalla radice a una foglia
	
	public boolean equals(IntTree t);   
	/*
	 * t1.equals(t2) e’ true se t1 e t2 sono isomorfi, ovvero indistunguibili ad
	 * un osservatore esterno
	 */
	
	public void addChild(IntTree child); // aggiunge child come primo figlio di this

	public IntTree followPath(int[] path) throws NoSuchTreeException;
	/*
	 * restituisce il sottoalbero individuato da path. Ad esempio, se path =
	 * [2,3,1], restituisce il primo figlio del terzo figlio del secondo figlio
	 * di this; se un tale sottoalbero non esiste lancia NoSuchTreeException
	 */
	
	public void visit();
	/*
	 * stampa la sequenza di valori associati ai nodi dell’albero corrispondente
	 * ad una visita in profondita’ (depth-first) pre-order
	 */
	
	public void printIntTree();
	/*
	 * pritty prints this, ad esempio usando le parentesi, oppure l’indentazione
	 */

}