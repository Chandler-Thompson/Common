package DataStructures;

//TODO: fix Record object problem
public class BinarySearchTree<T extends Comparable<T>> {

	@SuppressWarnings("hiding")
	class Node<T extends Comparable<T>>{
		T key;//could be record's id, entity, relation, or property
		Record data;
		Node<T> left;
		Node<T> right;
		
		public Node(T key, Record data){
			this.key = key;
			this.data = data;
			left = null;
			right = null;
		}
		
		public Node(T key, Record data, Node<T> left, Node<T> right){
			this.key = key;
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}
	
	Node<T> root = null;
	
	public void insert(T key, Record data){
		root = insert(key, data, root);
	}
	
	private Node<T> insert(T key, Record data, Node<T> t){
		if(t == null)
			return new Node<T>(key, data, null, null);
		
		if(key.compareTo(t.key) < 0 )
			return t.left = insert(key, data, t.left);
		else if(key.compareTo(t.key) > 0)
			return t.right = insert(key, data, t.right);
		
		return t;
	}
	
}
