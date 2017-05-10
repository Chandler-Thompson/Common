package DataStructures;

import java.util.ArrayList;

public class Queue<T> {
	protected ArrayList<T> front;
	protected ArrayList<T> rear;

	// Workhorse constructor. Initialize variables.
	public Queue(){
		front = new ArrayList<T>();
		rear = new ArrayList<T>();
	}

	public ArrayList<T> getFront(){
		return front;
	}
	
	public ArrayList<T> getRear(){
		return rear;
	}
	
	// Adds x to the rear of the queue
	// Target complexity: O(1)
	public void enqueue(T x){
		rear.add(x);
	}

	// Removes and returns the element at the front of the queue
	// Throws NoSuchElementException if this queue is empty.
	// Target complexity: O(n)
	public T dequeue(){
		T returnValue = null;
		//shift everything from rear array to front array
		if(front.size() == 0){
			//reverse the order of elements from rear to front
			//to keep First-In-First-Out order
			for(int i = rear.size()-1; i >=0; i--){
				//move element from rear to front
				front.add(rear.get(rear.size()-1));
				//pop element from rear
				rear.remove(rear.size()-1);
			}
		}
		//pop the last value and return it
		returnValue = front.get(front.size()-1);
		front.remove(rear.size()-1);
		
		return returnValue;
	}

	// Returns true if the queue is empty
	public boolean isEmpty(){
		return front.size()==0 && rear.size()==0;
	}

	// Returns the size of the queue
	public int size(){
		return front.size()+rear.size();
	}

	// Create a pretty representation of the DynamicQueue.
 	// Example:
	// A B C D
	public String toString(){
		return front.toString()+" "+rear.toString();
	}

	// Create a pretty representation of the DynamicQueue for debugging.
	// Example:
	// front.toString: A B 
	// rear.toString: C D
	protected String toStringForDebugging(){
		return "front.toString: "+front.toString()
				+"rear.toString: "+rear.toString();
	}
}
