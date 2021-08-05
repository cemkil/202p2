package code;

/* 
 * ASSIGNMENT 2
 * AUTHOR:  <Cem KILINC>
 * Class : ArrayDeque
 *
 * You are not allowed to use Java containers!
 * You must implement the Array Deque yourself
 *
 * MODIFY 
 * 
 * */

import given.iDeque;
import java.util.Arrays;
import java.util.Iterator;

import given.Util;


/*
 * You must have a circular implementation. 
 * 
 * WARNING: Modulo operator (%) might create issues with negative numbers.
 * Use Math.floorMod instead if you are having issues
 */
public class ArrayDeque<E> implements iDeque<E> {

  private E[] A; //Do not change this name!
  private int containSize;
  private int front;
  /*
   * ADD FIELDS IF NEEDED
   */
	
	public ArrayDeque() {
		this(1000);
    /*
     * ADD CODE IF NEEDED
     */
	}
	
	public ArrayDeque(int initialCapacity) {
	   if(initialCapacity < 1)
	      throw new IllegalArgumentException();
		A = createNewArrayWithSize(initialCapacity);
		front = 0;
		containSize = 0;
		/*
		 * ADD CODE IF NEEDED
		 */
	}
	
	// This is given to you for your convenience since creating arrays of generics is not straightforward in Java
	@SuppressWarnings({"unchecked" })
  private E[] createNewArrayWithSize(int size) {
	  return (E[]) new Object[size];
	}
	
	//Modify this such that the dequeue prints from front to back!
	//Hint, after you implement the iterator, use that!
  public String toString() {
    
    /*
     * MODIFY THE BELOW CODE
     */
    if (isEmpty()) return "";
    
    StringBuilder sb = new StringBuilder(1000);
    sb.append("[");
    Iterator<E> iter = iterator();
    while(iter.hasNext()) {
      E e = iter.next();
      if(e == null)
        continue;
      sb.append(e);
      if(!iter.hasNext())
        sb.append("]");
      else
        sb.append(", ");
    }
    return sb.toString();
  }
	
  /*
   * ADD METHODS IF NEEDED
   */

  /*
   * Below are the interface methods to be overriden
   */
	
  @Override
  public int size() {

    return containSize;
  }

  @Override
  public boolean isEmpty() {

    return containSize == 0;
  }

  @Override
  public void addFront(E o) {

    if(containSize == A.length){
      resizeStorage();
    }
    front = Math.floorMod(front-1, A.length);
    A[front]=o;
    containSize++;


  }

  private void resizeStorage() {
     E[] M = createNewArrayWithSize(A.length * 2);
     for( int i = 0 ; i< A.length ; i++){
       int j  = Math.floorMod(front + i , A.length);
       M[i] = A[j];
     }
     front = 0 ;
     A = M;
  }

  @Override
  public E removeFront() {
  if(isEmpty()){
    return null; }
  E o = A[front];
  A[front] = null;
  front = Math.floorMod(front+1, A.length);
  containSize--;
  return o;
  }

  @Override
  public E front() {
    if (isEmpty()){
      return null;
    }
    return A[front];
  }

  @Override
  public void addBehind(E o) {
   if (containSize == A.length){
     resizeStorage();
   }
   int rear = Math.floorMod(front+containSize, A.length);
   A[rear] = o;
   containSize++;
  }

  @Override
  public E removeBehind() {
    if (isEmpty()){
      return null;
    }
    int rear = Math.floorMod(front+containSize-1, A.length);
    E o = A[rear];
    A[rear] = null;
    containSize--;
    return o;
  }

  @Override
  public E behind() {
    if (isEmpty()){
      return null;
    }
    return A[Math.floorMod(front+containSize-1, A.length)];
  }

  @Override
  public void clear() {
    A = createNewArrayWithSize(100);
    front = 0;
    containSize = 0;
    
  }
  
  //Must print from front to back
  @Override
  public Iterator<E> iterator() {
    // TODO Auto-generated method stub
    //Hint: Fill in the ArrayDequeIterator given below and return a new instance of it
    return new ArrayDequeIterator();
  }
  
  private final class ArrayDequeIterator implements Iterator<E> {
    private int count = front;
    /*
     * 
     * ADD A CONSTRUCTOR IF NEEDED
     * Note that you can freely access everything about the outer class!
     * 
     */

    @Override
    public boolean hasNext() {
      return A[Math.floorMod(count, A.length)] != null;
    }

    @Override
    public E next() {
      E e = A[Math.floorMod(count, A.length)];
      count++;
      return e;
    }        
  }
}
