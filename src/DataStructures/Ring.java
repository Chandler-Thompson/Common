package DataStructures;

public class Ring<T>{

	//using A to get rid of "The type parameter T is hiding the type T" warning
	class Node<A>{
		A value;
		Node<A> next;
		public Node(Node<A> next, A value){
			this.value = value;
			this.next = next;
		}
	}
	
	Node<T> current;
	
	public Ring(T value){
		current = new Node<T>(null, value);
		current.next = current;
	}
	
	public void advance(){
		current = current.next;
	}
	
	public T getValue(){
		return current.value;
	}
	
	public void add(T value){
		Node<T> newNode = new Node<T>(current.next, value);
		current.next = newNode;
	}
	
	public void remove(){
		current.next = current.next.next;
	}
	
}
