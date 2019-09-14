import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Deque<Item> implements Iterable<Item> {

    // Mantener dos pointers: head y tail
    private int head = 0;
    private int tail = 0;

    private Item[] it;
    private int N = 0;

    // construct an empty deque
    public Deque() {
      it = (Item[]) new Object[1]; // Ugly cast!!!
    }

    // is the deque empty?
    public boolean isEmpty() {
      return N == 0;
    }

    // return the number of items on the deque
    public int size() {
      return N;
    }

    // add the item to the back
    public void addLast(Item item) {
      if (item == null) throw new IllegalArgumentException("no null arguments allowed");
      it[tail++] = item;
      if (tail == it.length) tail = 0;
      N++;
      if (N == it.length) resize(it.length * 2);
    }

    // add the item to the front
    public void addFirst(Item item) {
      if (item == null) throw new IllegalArgumentException("no null arguments allowed");
      head--;
      if (head < 0) head += it.length;
      it[head] = item;
      N++;
      if (N == it.length) resize(it.length * 2);
    }

    // remove and return the item from the last
    public Item removeLast() {
      if (N == 0) throw new NoSuchElementException("nothing here...");
      tail--;
      if (tail < 0)  tail += it.length; 
      Item item = it[tail];
      it[tail] = null;
      N--;      
      if (N > 0 && N == it.length / 4) resize(it.length / 2);
      return item;
    }

    // remove and return the item from the front
    public Item removeFirst() {
      if (N == 0) throw new NoSuchElementException("nada aquí...");
      Item item = it[head];      
      it[head++] = null;
      if (head == it.length) head = 0;
      N--;
      if (N > 0 && N == it.length / 4) resize(it.length / 2);
      return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
      return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
      private int indx = head;
      public boolean hasNext() {
        return it[(indx) % it.length] != null;
      }
      public Item next() {
        if (it[indx] == null) throw new NoSuchElementException();
        Item item = it[indx];
        indx = (indx + 1) % it.length;
        return item;
      }
      public void remove() {
        throw new UnsupportedOperationException();
      }
    }

    private void resize(int capacity) {
      Item[] copy = (Item[]) new Object[capacity];
      int c = 0;
      int j;
      for (int i = head; i < head + N; i++) {
        j = i % it.length;
        copy[c++] = it[j];
      }
      head = 0;
      tail = head + N;
      it = copy;
    }

    public String toString() {
      String res = "";
      for (int i = 0; i < it.length; i++) {
        if (it[i] == null)  res += "Null";
        else res += it[i];
        res += "-";
      }
      return res;
    }

    // unit testing (required)
    public static void main(String[] args) {
      Deque<String> d = new Deque<>();
      d.addFirst("a");
      d.addLast("z");
      d.removeFirst();
      d.removeLast();
      d.addFirst("aa");
      d.addLast("bb");
      d.addLast("cc");
      d.addLast("dd");
      d.addLast("ee");
      StdOut.println(d);
      for (String elem : d) StdOut.println(elem);
    }

}
