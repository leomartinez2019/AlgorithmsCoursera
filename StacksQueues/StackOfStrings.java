public class StackOfStrings {
  private Node first = null;

  private class Node {
    String item;
    Node next;
  }

  void push(String item) {
    Node oldfirst = first;
    first = new Node();
    first.item = item;
    first.next = oldfirst;
  }

  String pop() {
    String item = first.item;
    first = first.next;
    return item;
  }

  boolean isEmpty() {
    return first == null;
  }

  public static void main(String[] args) {
    StackOfStrings stack = new StackOfStrings();
    while (!StdIn.isEmpty()) {
      String s = StdIn.readString();
      if (s.equals("-")) {
        StdOut.print(stack.pop());
        StdOut.print(" ");
      }
      else stack.push(s);
    }
    StdOut.print("\n");
  }
}
