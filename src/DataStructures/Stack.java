package DataStructures;

public class Stack<T>{

	private Node<T> topNode;
	
	public Stack(){
		topNode = null;
	}
	
	public void push(T data){
		topNode = new Node<T>(data, topNode);
	}
	
	public T pop(){
		T tempData = null;
		if (!isEmpty()){
			tempData = topNode.data;
			topNode = topNode.next;
		}
		return tempData;
	}
	
	public T peek(){
		return topNode.data;
	}
	
	public boolean isEmpty(){
		return topNode == null;
	}
	
}

class Node<T>{
	protected T data;
	protected Node<T> next;
	public Node(T data, Node<T> next){
		this.data = data;
		this.next = next;
	}
}
