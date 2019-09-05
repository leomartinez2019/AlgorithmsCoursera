import java.util.Iterator;

public class LinkedIterableQueueOfItems<T> implements Iterable<T> {
//public class LinkedIterableQueueOfItems<T> {
  private Node first, last;

  private class Node {
    T item;
    Node next;
  }

  public boolean isEmpty() {
    return first == null;
  }

  public void enqueue(T item) {
    Node oldlast = last;
    last = new Node();
    last.item = item;
    last.next = null;
    if (isEmpty()) first = last;
    else oldlast.next = last;
  }

  public T dequeue() {
    T item = first.item;
    first = first.next;
    if (isEmpty()) last = null;
    return item;
  }

  public Iterator<T> iterator() {
    return new ListIterator();
  }

  private class ListIterator implements Iterator<T> {
    private Node current = first;
    public boolean hasNext() {
      return current != null;
    }
    public T next() {
      T item = current.item;
      current = current.next;
      return item;
    }
  }

  public static void main(String[] args) {
    LinkedIterableQueueOfItems<String> q = new LinkedIterableQueueOfItems<>();
    q.enqueue("hello1");
    q.enqueue("hello2");
    q.enqueue("hello3");
    StdOut.print(q.isEmpty());
    StdOut.print("\n");
    //StdOut.print(q.dequeue());
    //StdOut.print(q.dequeue());
    for (String elem : q)
      StdOut.println(elem);
    StdOut.print("\n");
  }
}
