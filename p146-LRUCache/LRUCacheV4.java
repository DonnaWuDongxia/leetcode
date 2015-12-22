import java.util.HashMap;
import java.util.Map;

public class LRUCache {
  Map<Integer, Node> members = new HashMap<>();
  LinkedQueue queue = new LinkedQueue();
  int mCapacity;

  class Node {
    int key;
    int value;
    Node next;
    Node previous;

    public Node(int key, int value) {
      this.key = key;
      this.value = value;
      this.next = null;
      this.previous = null;
    }
    public String toString() {
      return "[" + key + ", " + value + "]";
    }
  }

  class LinkedQueue {
    private Node head = null;
    private Node tail = null;

    public void push(Node newNode) {
      if ((head == null) && (tail == null)) {
        head = newNode;
        tail = newNode;
        return;
      }
      Node oldTail = tail;
      newNode.previous = oldTail;
      newNode.next = null;
      oldTail.next = newNode;
      tail = newNode;
      return;
    }

    public void promte(Node node) {
      if (head == tail) return;

      remove(node);
      push(node);
    }

    public Node getFirst() {
      return head;
    }

    public void remove(Node node) {
      if (node.next != null) {
        node.next.previous = node.previous;
      } else if (tail.equals(node)) {
        tail = node.previous;
      }
      if (node.previous != null) {
        node.previous.next = node.next;
      } else if (head.equals(node)) {
        head = node.next;
      }
    }

    public void print() {
      Node t = head;
      System.out.println("The queue:");
      while(t != null){
        System.out.println(t.toString());
        t = t.next;
      }
    }
  }

  public LRUCache(int capacity) {
    mCapacity = capacity;
  }

  public void printCache() {
    System.out.println("Members:");
    for (Integer k : members.keySet()) {
      System.out.println("<" + k + ", " + members.get(k).toString() + ">");
    }
    queue.print();
  }

  public int get(int key) {
    if (!members.containsKey(key))
      return -1;

    Node found = members.get(key);
    queue.promte(found);
    System.out.println("In get function");
    printCache();
    return found.value;
  }

  public void set(int key, int value) {
    if (members.containsKey(key)) {
      Node found = members.get(key);
      found.value = value;
      queue.promte(found);
      return;
    }

    if (members.size() >= mCapacity) {
      Node oldest = queue.getFirst();
      members.remove(oldest.key);
      queue.remove(oldest);
    }
    Node newNode = new Node(key, value);
    members.put(key, newNode);
    queue.push(newNode);

    System.out.println("In Set function");
    printCache();
  }
}
