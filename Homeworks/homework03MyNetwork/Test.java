package homework03MyNetwork;

public class Test {
	public static void main(String[] args) throws NoSuchPathException {
		MyNetwork<Integer> prova = new MyNetwork<>();
		
		prova.addNode(2); // aggiungo i nodi
		prova.addNode(5);
		prova.addNode(11);
		prova.addNode(10);
		prova.addNode(9);
		prova.addNode(7);
		prova.addNode(8);
		prova.addNode(3);
		
		
		try {
			prova.addEdge(5, 11);  // aggiungo gli archi
			prova.addEdge(11, 2);
			prova.addEdge(11, 9);
			prova.addEdge(11, 10);
			prova.addEdge(7, 11);
			prova.addEdge(1, 8);
			prova.addEdge(8, 9);
			prova.addEdge(3, 10);
			prova.addEdge(3, 8);
			prova.addEdge(10, 3);
			prova.setSource(7);
			prova.setTarget(3);
		} catch (NoSuchNodeException e) {
			e.printStackTrace();
		}
		
		System.out.println(prova.shortestPath());  //printo lo shortestPath
		
	}

}
