package esonero1;

public class Test {
	public static void main(String[] args) {
		MyBinTree t1 = new MyBinTree(1,new MyBinTree(2), new MyBinTree(3,new MyBinTree(4), new MyBinTree(5)));
		System.out.println(t1.leftChild().root()+ " "+ t1.rightChild().root());
		t1.flip();
		System.out.println(t1.leftChild().root()+ " " + t1.rightChild().root());
		System.out.println(t1.toString());
	}
	
	/* Supponiamo di rappresentare un albero con radice etichettata da n, figlio sinistro t1
	e figlio destro t2 mediante una tripla (n, t1, t2), e supponiamo che (n) rappresenti
	una foglia etichettata con n; supponiamo che t sia l’albero (1, (2), (3, (4), (5))),
	allora, dopo l’esecuzione di t.flip(), t sarà l’albero (1, (3, (5), (4)), (2)).*/
}
