import java.lang.IllegalArgumentException;
import java.lang.UnsupportedOperationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    // Mantener dos pointers: head y tail
    int head = 0;
    int tail = 0;

    Item[] it;
    int N = 0;

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
      it[tail] = item;
      N++;
      if (N == it.length) resize(it.length * 2);
      tail = (head + N) % it.length;
    }

    // add the item to the front
    public void addFirst(Item item) {
      if (item == null) throw new IllegalArgumentException("no null arguments allowed");
      N++;
      if (N == it.length) {
        resize(it.length * 2);
        tail = head + N;
      }
      if (it[head] != null) {
        head = (head - 1) % it.length;
        if (head < 0)  head += it.length;
        tail = (head + N) % it.length;
      }
      it[head] = item;
    }

    // remove and return the item from the last
    public Item removeLast() {
      if (N == 0) throw new NoSuchElementException("nothing here...");
      tail = (tail - 1) % it.length;
      if (tail < 0)  tail += it.length; 
      Item item = it[tail];
      it[tail] = null;
      N--;      
      if (N > 0 && N == it.length / 4)
        resize(it.length / 2);
      return item;
    }

    // remove and return the item from the front
    public Item removeFirst() {
      if (N == 0) throw new NoSuchElementException("nada aquí...");
      Item item = it[head];      
      it[head] = null;
      head = (head + 1) % it.length;
      N--;
      if (N > 0 && N == it.length / 4) {
        resize(it.length / 2);
        tail = head + N;
      }
      return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
      return new ListIterator();
    }

    public class ListIterator implements Iterator<Item> {
      private int indx = head;
      public boolean hasNext() {
        //StdOut.print("hasNext - indx: ");
        //StdOut.println(indx);
        return it[(indx) % it.length] != null;
      }
      public Item next() {
        if (it[indx] == null) throw new NoSuchElementException();
        Item item = it[indx];
        //StdOut.print("indx: ");
        //StdOut.println(indx);
        indx = (indx + 1) % it.length;
        //StdOut.print("indx: ");
        //StdOut.println(indx);
        //StdOut.print("item: ");
        //StdOut.println(item);
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
      //d.addFirst("a");
      //d.addLast("z");
      //d.removeFirst();
      //d.removeLast();
      d.addFirst("aa");
      d.addLast("bb");
      d.addLast("cc");
      d.addLast("dd");
      d.addLast("ee");
      StdOut.println(d);
      for (String elem : d) StdOut.println(elem);
    }

}
