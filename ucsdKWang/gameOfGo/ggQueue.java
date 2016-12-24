package ucsdKWang.gameOfGo; 
import java.util.*;
/**
 * @author Tianyi Wang
 * @since 02-02-2016
 */

public class ggQueue {

	private ggPiece[] queue;
	// the initial size of the array before it is full
	private static final int START_SIZE = 10;
	// the number of elements in the queue
	private int nelems;
	// the pointer of the index to remove an element
	private int front;
	// pointer of the index to add an element
	private int rear;

	/** Constructor */
	public ggQueue() {
		queue =  new ggPiece[START_SIZE];
		front = 0;
		rear = 0;
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
	public void add(ggPiece newItem) {
		//if ( hasItem(newItem) ) return;
		// if the array is full, double the length
		if ( size() == queue.length ) {
			// the new length of the queue array
			int newLength = queue.length + queue.length;
			// the index of queue to be copied
			int j = front;

			ggPiece[] newQueue = new ggPiece[newLength];
			for ( int i = 0; i < size(); i++ ) {
				newQueue[i] = queue[front];
				front = (front + 1) % queue.length;
			}
			front = 0;
			rear = size();
			queue = newQueue;
		}
		// Now the array is not full, add element to the rear
		queue[rear] = newItem;
		rear = (rear + 1) % queue.length; 
		nelems++;
	}

	/** Removes the T from the storage and returns
      * that T as the value of this function.
      * @return value of the removed T.
      */  
	public ggPiece remove() {
		if ( queue[front] == null ) {
			throw new NoSuchElementException();
		}
		ggPiece item = queue[front];
		queue[front] = null;
		front = (front + 1) % queue.length;
		nelems--;
		return item;
	}

	public ggPiece get() {
		if ( queue[front] == null ) {
			throw new NoSuchElementException();
		}
		ggPiece item = queue[front];
		return item;
	}

	/** Returns the size of the storage 
      * @return the size of the storage
      */ 
	public int size() {
		return this.nelems;
	}

	/**
	  * get the index of rear, used for Junit test
	  * @return rear 
	  */
	public int getRearIndex() {
		return rear;
	}
	
	public boolean hasItem(ggPiece item){
		if ( size() == 0 ) return false;
		if ( front < rear) {
			for (int i = front; i<rear;i++){
				if (item.getX() == queue[i].getX() && item.getY()==queue[i].getY()) return true;
			}
		}
		else {
			for ( int i=front; i<size();i++){
				if (item.getX() == queue[i].getX() && item.getY()==queue[i].getY()) return true;
			}
			for ( int j=0; j<rear;j++){
				if (item.getX() == queue[j].getX() && item.getY()==queue[j].getY()) return true;
			}
		}
		return false;
	}

	/**
	  * get the index of the front element, 
	  * used for Junit test
	  * @return front 
	  */
	public int getFrontIndex() {
		return front;
	} 

	/**
	 * get the element in the front 
	 * used for JUnit Test
	 * @return Element in the front
	 */
	public ggPiece getFront() {
		return queue[front];
	}
	public static void main(String[] args) {
		ggQueue queue = new ggQueue();
		ggBoard board = new ggBoard(false);
		queue.add(new ggPiece(false,false,board,1,2));
		queue.add(new ggPiece(false,false,board,2,3));
		queue.add(new ggPiece(false,true,board,2,3));
		System.out.println(queue.size());

	}
}