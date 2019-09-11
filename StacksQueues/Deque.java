import java.util.Iterator;
import java.util.Random;
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
      it[tail] = item;
      N++;
      if (N == it.length) resize(it.length * 2);
      tail = (head + N) % it.length;
    }

    // add the item to the front
    public void addFirst(Item item) {
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
      StdOut.print("head(addFirst): ");
      StdOut.println(head);
      StdOut.print("tail(addFirst): ");
      StdOut.println(tail);
      it[head] = item;
    }

    // remove and return the item from the last
    public Item removeLast() {
      if (N == 0) throw new NoSuchElementException("nada aquí...");
      StdOut.print("tail: ");
      StdOut.println(tail);
      tail = (tail - 1) % it.length;
      if (tail < 0)  tail += it.length; 
      StdOut.print("tail: ");
      StdOut.println(tail);
      Item item = it[tail];
      it[tail] = null;
      N--;      
      if (N > 0 && N == it.length / 4)
        resize(it.length / 2);
      StdOut.print("tail: ");
      StdOut.println(tail);
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
    //public Iterator<Item> iterator() {

    //}

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
      d.addLast("queso");
      d.addFirst("Papa");
      d.addLast("coco");
      d.addLast("zapote");
      d.addFirst("yuca");
      d.addFirst("arroz");
      d.addFirst("aceite");
      d.addFirst("ñame");
      //d.addLast("corozo");
      StdOut.println(d);

    }

    public static void test3() {
      Deque<String> d = new Deque<>();
      d.addFirst("Papa");
      StdOut.println(d);
      d.addLast("coco");
      StdOut.println(d);
      d.removeLast();
      StdOut.println(d);
      d.addLast("zapote");
      StdOut.println(d);
      d.addFirst("yuca");
      StdOut.println(d);
      d.removeLast();
      StdOut.println(d);
      d.addFirst("arroz");
      StdOut.println(d);
      d.addLast("corozo");
      StdOut.println(d);
    }

    // Test reading from a text file
    // Text file like: green yellow blue ^ $ pink 
    // addFirst = "^", addLast = "$", removeFirst = "^-", removeLast = "$-"
    public static void test4() {
      Random num = new Random();
      int randNum;
      Deque<String> d = new Deque<>();
      while (!StdIn.isEmpty()) {
        String s = StdIn.readString();
        if (s.equals("^")) {
          StdOut.println(d.removeFirst());
        }
        else if (s.equals("$")) {
          StdOut.println(d.removeLast());
        }
        else {
          randNum = num.nextInt(100);
          if (randNum < 50) {
            d.addFirst(s);
          }
          else {
            d.addLast(s);
          }
        }
      }
    }

    public static void main(String[] args) {
      //test1();
      //test2();
      test4();
    }

}
