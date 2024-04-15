/**
 * The DoubleNode class is linked both ways for use in a linked list. 
 * @author Chenkai Hong
 * @version October 18, 2023
 */

public class DoubleNode<E> {
	private DoubleNode next;
	private DoubleNode previous;
	private E value;
	
	//Constructor for the DoubleNode class. i
	//@param value The value of the double node.
	public DoubleNode(E value) {
		next = null;
		previous = null;
		this.value = value;
	}

	//Gets the next DoubleNode.
	//@return The next DoubleNode.
	public DoubleNode getNext() {
		return next;
	}
	
	//Gets the previous DoubleNode.
	//@return The previous DoubleNode.
	public DoubleNode getPrevious() {
		return previous;
	}
	
	//Gets the value of the DoubleNode.
	//@return The Value.
	public E getValue() {
		return value;
	}
	
	//Sets the next DoubleNode.
	//@param newNext The new next DoubleNode.
	public void setNext(DoubleNode newNext) {
		next = newNext;
	}
	
	//Sets the previous DoubleNode.
	//@param newPrevious The new previous DoubleNode.
	public void setPrevious(DoubleNode newPrevious) {
		previous = newPrevious;
	}

	//Sets the value.
	//@param value The new value.	
	public void setValue(E value) {
		this.value = value;
	}
}
