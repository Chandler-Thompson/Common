package DataStructures;

public class LinkedList<T> {

	class Node{
		public T value;
		public Node right;
		public Node down;
		public Node(){
			value = null;
			right = null;
			down = null;
		}
	}
	
	public final Node head;
	
	public LinkedList(){
		head = new Node();
	}
	
	public void addRight(T value){
		Node node;
		for(node = head; node.right != null; node = node.right);//go all the way to the right
		
		node.right = new Node();
		node.right.value = value;
	}
	
	public void addEnd(T value){
		Node node;
		for(node = head; node.right != null; node = node.down);//go all the way down
		for(node = node; node.right != null; node = node.right);//go all the way to the right
		
		node.right = new Node();
		node.right.value = value;
	}
	
}
