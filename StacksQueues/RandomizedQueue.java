import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int head = 0;
    private int tail = 0;

    private Item[] q;
    private int numElems = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
      q = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
      return numElems == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
      return numElems;
    }

    // add the item
    public void enqueue(Item item) {
      if (item == null) throw new IllegalArgumentException("no null arguments allowed");
      q[tail++] = item;
      if (tail == q.length) tail = 0;
      numElems++;
      if (numElems == q.length) resize(q.length * 2);
    }

    // remove and return a random item
    public Item dequeue() {
      if (isEmpty()) throw new NoSuchElementException("nothing here...");
      Item item = q[head];
      q[head++] = null;
      if (head == q.length) head = 0;
      numElems--;
      if (numElems > 0 && numElems == q.length / 4) resize(q.length / 2);
      return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
      if (isEmpty()) throw new NoSuchElementException("nothing here...");
      int num = StdRandom.uniform(numElems);
      return q[num + head];
    }

    private void resize(int capacity) {
      Item[] copy = (Item[]) new Object[capacity];
      int c = 0;
      int j;
      for (int i = head; i < head + numElems; i++) {
        j = i % q.length;
        copy[c++] = q[j];
      }
      head = 0;
      tail = head + numElems;
      q = copy;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
      return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
      private int[] lista = new int[numElems];
      private int indice = head;
      private int conteo = 0;
      public ListIterator() {
        while (indice != tail) {
          lista[conteo++] = indice++;
          if (indice == q.length) indice = 0;
        }
        StdRandom.shuffle(lista);
      }
      int indx = 0;
      public boolean hasNext() {
        //return q[(indx) % q.length] != null;
        return indx < numElems;
      }
      public Item next() {
        if (!hasNext()) throw new NoSuchElementException();
        Item item = q[lista[indx++]];
        return item;
      }
      public void remove() {
        throw new UnsupportedOperationException();
      }
    }

    // unit testing (required)
    public static void main(String[] args) {
      RandomizedQueue<String> r = new RandomizedQueue<>();
      r.enqueue("aa");
      r.enqueue("bb");
      r.enqueue("cc");
      r.enqueue("dd");
      r.dequeue();
      r.enqueue("ff");
      r.enqueue("gg");
      r.dequeue();
      for (String elem : r) StdOut.println(elem);
    }

}
