import java.util.Iterator;
import java.util.NoSuchElementException;

//public class Deque<Item> implements Iterable<Item> {
public class Deque<Item> {

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
      StdOut.println("------addItem Start---------");
      StdOut.println("tail: " + tail);
      StdOut.println("head: " + head);
      StdOut.println("it.length: " + it.length);
      it[tail] = item;
      N++;
      if (N == it.length) resize(it.length * 2);
      tail = (head + N);;
      //tail = (tail + 1) % it.length;
      StdOut.println("tail: " + tail);
      StdOut.println("N: " + N);
      StdOut.println("------addItem End---------");
    }

    // add the item to the front
    public void addFirst(Item item) {
      if (isEmpty()) {
        it[head] = item;
      } else {
        head = (head + 1) % it.length;
        it[head] = item;
      }
      N++;
      if (N == it.length) resize(it.length * 2);
    }

    // remove and return the item from the last
    //public Item removeLast() {
      
    //}

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
    //public Iterator<Item> iterator() {

    //}

    private void resize(int capacity) {
      StdOut.println("------Resize start --------");
      Item[] copy = (Item[]) new Object[capacity];
      int c = 0;
      int j;
      //int temp = tail < head ? tail + N : tail;
      for (int i = head; i < head + N; i++) {
        j = i % it.length;
        copy[c++] = it[j];
        //i = (i + 1) % it.length; 
      }
      head = 0;
      //tail = (head + N); //% it.length;
      //tail = (head + N); //% it.length;
      it = copy;
      StdOut.print("head: ");
      StdOut.println(head);
      StdOut.print("tail: ");
      StdOut.println(tail);
      StdOut.print("capacity: ");
      StdOut.println(capacity);
      StdOut.println("------Resize end --------");
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
    public static void test1() {
      Deque<String> d = new Deque<>();
      d.addLast("Bardzo1");
      StdOut.println(d);
      d.addLast("Bardzo2");
      StdOut.println(d);
      d.removeFirst();
      StdOut.println(d);
      d.addLast("Bardzo3");
      StdOut.println(d);
      d.removeFirst();
      d.addLast("Bardzo4");
      StdOut.println(d);
      //d.removeFirst();
      d.addLast("Bardzo5");
      d.addLast("Bardzo6");
      d.addLast("Bardzo7");
      d.addLast("Bardzo8");
      d.removeFirst();
      d.removeFirst();
      d.removeFirst();
      //StdOut.println(d.isEmpty());
      //StdOut.println(d.size());
      StdOut.println(d);
    }

    public static void test2() {
      Deque<String> d = new Deque<>();
      d.addFirst("Papa");
      d.addLast("queso");
      StdOut.println(d);

    }

    public static void main(String[] args) {
      test2();
    }

}
