import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {
   public static void main(String[] args) {
     int valor = Integer.parseInt(args[0]);
     RandomizedQueue<String> r = new RandomizedQueue<>();
     while (!StdIn.isEmpty()) {
       String s = StdIn.readString();
       r.enqueue(s);
     }
     for (int k = 0; k < valor; k++) StdOut.println(r.sample());
   }
}
