package homework03MyNetwork;

import java.util.List;

public interface Network<T> { // contratto delle reti
	public T source();

	// restituisce il nodo sorgente di this;
	// null se la sorgente non e’ settata
	public T target();

	// restituisce il nodo destinazione di this;
	// null se la destinazione non e’ settata
	public void setSource(T newsource) throws NoSuchNodeException;

	// setta la sorgente della rete a newsource;
	// lancia l’eccezione se newsource non e’ un nodo della rete
	public void setTarget(T newtarget) throws NoSuchNodeException;

	// setta la destinazione della rete a newtarget;
	// lancia l’eccezione se newtarget non e’ un nodo della rete
	public void addNode(T v);

	// aggiunge a this un nuovo nodo v.
	// Non fa nulla se v e’ gia’ un nodo della rete
	public void addEdge(T p, T a) throws NoSuchNodeException;

	// aggiunge a this un nuovo arco, con partenza p e arrivo a.
	// Lancia l’eccezione se p o a non appartengono alla rete.
	public List<T> shortestPath() throws NoSuchPathException;
	/*
	 * restituisce una lista minimale <t1, ... tn> di oggetti di tipo T, tali
	 * che: t1 e tn sono sorgente e destinazione della rete e, per ogni coppia
	 * (ti, t(i+1)) di elementi consecutivi nella lista, esiste in this (almeno)
	 * un arco con partenza in ti e arrivo in t(i+1). Lancia l’eccezione se
	 * source o target non sono settati o se non c’e’ path che collega il primo
	 * al secondo
	 */
}
