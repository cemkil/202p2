package code;

/*
 * ASSIGNMENT 2
 * AUTHOR:  <Cem Kilinc>
 * Class : LLDeque
 *
 * You are not allowed to use Java containers!
 * You must implement the linked list yourself
 * Note that it should be a doubly linked list
 *
 * MODIFY
 *
 * */

import given.iDeque;
import java.util.Iterator;
import given.Util;

//If you have been following the class, it should be obvious by now how to implement a Deque wth a doubly linked list
public class LLDeque<E> implements iDeque<E> {

  //Use sentinel nodes. See slides if needed
  private Node<E> header;
  private Node<E> trailer;
  private int size;
  /*
   * ADD FIELDS IF NEEDED
   */

  // The nested node class, provided for your convenience. Feel free to modify
  private class Node<T> {
    private T element;
    private Node<T> next;
    private Node<T> prev;
    /*
     * ADD FIELDS IF NEEDED
     */

    Node(T d, Node<T> n, Node<T> p) {
      element = d;
      next = n;
      prev = p;
    }

    /*
     * ADD METHODS IF NEEDED
     */
  }

  public LLDeque() {
    //Remember how we initialized the sentinel nodes
    header  = new Node<E>(null, null, header);
    trailer = new Node<E>(null, trailer, header);
    header.next = trailer;
    size=0;
    /*
     * ADD CODE IF NEEDED
     */
  }

  public String toString() {
    if(isEmpty())
      return "";
    StringBuilder sb = new StringBuilder(1000);
    sb.append("[");
    Node<E> tmp = header.next;
    while(tmp.next != trailer) {
      sb.append(tmp.element.toString());
      sb.append(", ");
      tmp = tmp.next;
    }
    sb.append(tmp.element.toString());
    sb.append("]");
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
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public void addFront(E o) {

      Node<E> myNode = new Node<>(o , header.next , header);
      header.next.prev = myNode;
      header.next = myNode;



    size ++;

  }

  @Override
  public E removeFront() {


    if (size() >= 1) {
      E element = header.next.element;
      header.next = header.next.next;
      header.next.prev = header;
      size--;
      return  element;
    }else{
      return null;
    }




  }

  @Override
  public E front() {
    if (isEmpty()){
      return  null;
    }
    return header.next.element;
  }

  @Override
  public void addBehind(E o) {
    Node<E> newNode = new Node<>(o , trailer , trailer.prev);
    trailer.prev.next = newNode;
    trailer.prev = newNode;
    size++;

  }

  @Override
  public E removeBehind() {

    if(size()<1){
      return null;
    }
    E element = trailer.prev.element;
    trailer.prev = trailer.prev.prev;
    trailer.prev.next = trailer;
    size--;
    return element;
  }

  @Override
    public E behind() {
     if(isEmpty()){
       return null;
     }
      return trailer.prev.element;
    }

  @Override
  public void clear() {
    header  = new Node<E>(null, null, header);
    trailer = new Node<E>(null, trailer, header);
    header.next = trailer;
    size=0;

  }

  @Override
  public Iterator<E> iterator() {
    // TODO Auto-generated method stub
    //Hint: Fill in the LLDequeIterator given below and return a new instance of it
    return new LLDequeIterator();
  }

  private final class LLDequeIterator implements Iterator<E> {

    Node<E> currentNode;
    /*
     *
     * ADD A CONSTRUCTOR IF NEEDED
     * Note that you can freely access everything about the outer class!
     *
     */
    public LLDequeIterator(){
      currentNode=header;

    }

    @Override
    public boolean hasNext() {

      return currentNode.next != trailer;
    }

    @Override
    public E next() {
      currentNode = currentNode.next;
      return (E) currentNode.element;
    }
  }

}
