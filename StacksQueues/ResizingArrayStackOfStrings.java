public class ResizingArrayStackOfStrings {
  private String[] s;
  private int N = 0;

  public ResizingArrayStackOfStrings() {
    s = new String[1];
  }

  public int sizeoff() {
    return s.length;
  }

  public boolean isEmpty() {
    return N == 0;
  }

  public void push(String item) {
    if (N == s.length) resize(2 * s.length);
    s[N++] = item;
  }

  private void resize(int capacity) {
    String[] copy = new String[capacity];
    for (int i = 0; i < N; i++)
      copy[i] = s[i];
    s = copy;
  }

  public String pop() {
    String item = s[--N];
    s[N] = null;
    if (N > 0 && N == s.length / 4)
      resize(s.length / 2);
    return item;
  }

public static void main(String[] args) {
    ResizingArrayStackOfStrings stack = new ResizingArrayStackOfStrings();
    while (!StdIn.isEmpty()) {
      String s = StdIn.readString();
      if (s.equals("-")) {
        StdOut.print(stack.pop());
        StdOut.print(stack.sizeoff());
        StdOut.print("\n");
      }
      else {
        stack.push(s);
        System.out.println(stack.sizeoff());
      }
    }
    StdOut.print("\n");
  }

}
