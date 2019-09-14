import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int head = 0;
    private int tail = 0;

    private Item[] q;
    private int N = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
      q = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
      return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
      return N;
    }

    // add the item
    public void enqueue(Item item) {
      if (item == null) throw new IllegalArgumentException("no null arguments allowed");
      q[tail++] = item;
      if (tail == q.length) tail = 0;
      N++;
      if (N == q.length) resize(q.length * 2);
    }

    // remove and return a random item
    public Item dequeue() {
      if (N == 0) throw new NoSuchElementException("nothing here...");
      Item item = q[head];
      q[head++] = null;
      if (head == q.length) head = 0;
      N--;
      if (N > 0 && N == q.length / 4) resize(q.length / 2);
      return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
      int num = StdRandom.uniform(N);
      return q[num + head];
    }

    private void resize(int capacity) {
      Item[] copy = (Item[]) new Object[capacity];
      int c = 0;
      int j;
      for (int i = head; i < head + N; i++) {
        j = i % q.length;
        copy[c++] = q[j];
      }
      head = 0;
      tail = head + N;
      q = copy;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
      return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
      private int indx = head;
      public boolean hasNext() {
        return q[(indx) % q.length] != null;
      }
      public Item next() {
        if (q[indx] == null) throw new NoSuchElementException();
        Item item = q[indx];
        indx = (indx + 1) % q.length;
        return item;
      }
      public void remove() {
        throw new UnsupportedOperationException();
      }
    }

    public String toString() {
      String res = "";
      for (int i = 0; i < q.length; i++) {
        if (q[i] == null)  res += "Null";
        else res += q[i];
        res += "-";
      }
      return res;
    }

    // unit testing (required)
    public static void main(String[] args) {
      RandomizedQueue<String> r = new RandomizedQueue<>();
      r.enqueue("aa");
      r.enqueue("bb");
      r.enqueue("cc");
      r.enqueue("dd");
      r.enqueue("ee");
      r.enqueue("ff");
      StdOut.println(r);
      for (int k = 0; k < 20; k++){
        StdOut.print(r.sample());
        StdOut.print("-");
      }
      StdOut.println("");
      for (String elem : r) StdOut.println(elem);
    }

}
