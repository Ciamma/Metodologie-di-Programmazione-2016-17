package homework03MyNetwork;

import java.util.*;

public class MyNetwork<T> implements Network<T> {
	private T source; // definisco source & target
	private T target;

	public HashMap<T, ArrayList<T>> lista_nodi; // definisco l'hashmap

	public MyNetwork() { // definisco il costruttore
		this.lista_nodi = new HashMap<T, ArrayList<T>>();
		this.source = null;
		this.target = null;
	}

	@Override
	public T source() { // ritorna source
		return this.source;
	}

	@Override
	public T target() { // ritorna target
		return this.target;
	}

	@Override
	public void setSource(T newsource) throws NoSuchNodeException { // setta
																	// source
																	// con un
		if (lista_nodi.containsKey(newsource))
			this.source = (T) newsource;
		else
			throw new NoSuchNodeException();

	}

	@Override
	public void setTarget(T newtarget) throws NoSuchNodeException {
		if (lista_nodi.containsKey(newtarget))
			this.target = (T) newtarget;
		else
			throw new NoSuchNodeException();

	}

	@Override
	public void addNode(T v) {
		if (lista_nodi.containsKey(v))
			;
		else
			this.lista_nodi.put(v, new ArrayList<T>());
	}

	@Override
	public void addEdge(T p, T a) throws NoSuchNodeException {
		this.lista_nodi.get(p).add((T) a);
	}

	public ArrayList<T> BFS() {
		ArrayList<T> queue = new ArrayList<>();
		HashMap<T, T> padri = new HashMap<>();
		padri.put(source, source);
		queue.add(source);
		while (queue.size() != 0) {
			T v = queue.remove(0);
			for (T n : lista_nodi.get(v)) {
				if (padri.containsKey(n) == false) {
					padri.put(n, v);
					queue.add(n);
				}
			}
		}
		if (padri.containsKey(target) == false)
			return null;
		queue.clear();
		T v = target;
		queue.add(v);
		while (v.equals(source) == false) {
			queue.add(0, padri.get(v));
			v = padri.get(v);
		}
		return queue;
	}

	@Override
	public List<T> shortestPath() throws NoSuchPathException {
		if (this.source() == null || this.target() == null)
			throw new NoSuchPathException();
		ArrayList<T> ris = new ArrayList<>();
		ris = this.BFS();
		if (ris == null)
			throw new NoSuchPathException();
		else
			return ris;
	}

}
