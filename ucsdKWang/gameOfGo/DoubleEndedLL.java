package ucsdKWang.gameOfGo;
import java.util.*;
/**
 * @author Tianyi Wang
 * @since 02-02-2016
 */

public class DoubleEndedLL<T> {

    public Node head;
    public Node tail;
    public int nelems;

    public DoubleEndedLL() {
        // head to be a dummy node
        head = new Node();
        tail = new Node();
        head.next = tail;
    }

    public class Node {
        T data;
        Node next;

        /** Constructor by default */
        public Node() {
            data = null;
            next = null;
        }
        /** 
         * Constructor with one param
         * @param item to be inserted as data
         */
        public Node( T item ) {
            data = item;
            next = null;
        }
        /** 
         * Constructor with two params
         * @param item - item to be inserted as data
         * @param n - Node as next
         */
        public Node( T item, Node n ) {
            data = item;
            next = n;
        }

        /** get the data of the Node
         *  @return data the data in the node
         */
        public T getElement() {
            return this.data;
        }

        /** set the data of the node
         *  @param T item the new data of the node
         */
        public void setElement( T item ) {
            data = item;
        }

    }

    /** Checks if the list is empty 
     * @return returns true if the list is empty, false otherwise
     */ 
    public boolean isEmpty() {
        return size() == 0;
    }
 
    /** Checks the size of the list 
     * @return returns the number of elements in the list
     */ 
    public int size() {
        return this.nelems;
    }
 

    /** Adds a new node to the front of the list 
     * @param newItem - an element to add
     */ 

    public void addFirst(T newItem) {
        if ( tail.getElement() == null ) {
            tail.setElement( newItem );
            head.next = tail;
        }

        else {
            Node newNode = new Node( newItem );
            newNode.next = head.next;
            head.next = newNode;
        }
        nelems++;
    }
 
    /** Adds a new node to the end of the list 
     * @param newItem - an element to add
     */

    public void addLast(T newItem) {
        Node newNode = new Node(newItem);
        if ( tail.getElement() == null ) {
            tail.setElement( newItem );
        }

        else {
            tail.next = newNode;
            tail = tail.next;
        }
        nelems++;
    }

    /** Removes a node from the beginning of the list
     * @return element the data found
     * @throws NullPointerException
     */
 
    public T removeFirst() {
        if ( isEmpty() ) {
            throw new NoSuchElementException();
        }

        Node removeNode = head.next;
        T element = removeNode.getElement();
        head.next = head.next.next;
        nelems--;
        return element;
    }
  

    /** Removes a node from the end of the list
     * @return element the data found
     * @throws NullPointerException
     */
    public T removeLast() {
        if ( isEmpty() ) {
            throw new NoSuchElementException();
        }

        Node removeNode = tail;
        T element = tail.getElement();

        if ( size() == 1 ) {
            tail.setElement( null );
        }

        else {
            Node pointer = head.next;
            int count = 1;
            while ( count < size() - 1 ) {
                pointer = pointer.next;
                count++;
            }
            tail = pointer;
            pointer.next = null;
        }
        nelems--;
        return element;
    }
    
 
}