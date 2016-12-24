package ucsdKWang.gameOfGo;
import java.util.*;
/**
 * @author Tianyi Wang
 */

/** 
  * The head of the list is the top and tail is the bottom of MyStack
  */
public class ggStack<T> {

	private DoubleEndedLL<T> stack;

	/** Constructor */
	public ggStack() {
		stack = new DoubleEndedLL<T>();
	}

	/** Tests if the storage is empty. 
      * @return true a storage contains no items; false otherwise.
      */ 
	public boolean isEmpty() {
		return size() == 0;
	}

	/** Adds an element to a storage 
      * @param newItem - item to be added
      */  
	public void add(T newItem) {
		// add element to the head of the stack
		stack.addFirst( newItem );
	}

	/** Removes the object from the storage and returns
      * that object as the value of this function.
      * @return value of the removed object.
      */  
	public T remove() {
		if ( stack.size() == 0 ) {
			throw new NoSuchElementException();
		}

		return stack.removeFirst();
	}

	/** Returns the size of the storage 
      * @return the size of the storage
      */ 
	public int size() {
		return stack.size();
	}

	/**
	  * Returns the element on the top of the stack
	  * Used for JUnit Test
	  * @return element on the top
	  */
	public T get() {
		return stack.head.next.getElement();
	}
 
}