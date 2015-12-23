import java.util.Vector;
import java.util.Map;
import java.util.HashMap;

/**
 * Definition for singly-linked list with a random pointer.
 */
class RandomListNode {
  int label;
  RandomListNode next, random;
  RandomListNode(int x) { this.label = x; }
};

class NodeWraper{
  int index;
  RandomListNode node;
  int randomIndex = -1;
  public NodeWraper(int index, RandomListNode node) {
    this.index = index;
    this.node = node;
  }
}

public class Solution {
  public RandomListNode copyRandomList(RandomListNode head) {
    if (head == null) return null;

    Vector<NodeWraper> wrapers = new Vector<>();
    Map<RandomListNode, NodeWraper> members = new HashMap<>();

    RandomListNode tmp = head;
    RandomListNode newNode;
    NodeWraper wraper;
    int i = 0;
    while(tmp != null) {
      newNode = new RandomListNode(tmp.label);
      wraper = new NodeWraper(i++, newNode);
      wrapers.add(wraper);
      members.put(tmp, wraper);
      tmp = tmp.next;
    }

    int length = wrapers.size();
    for (RandomListNode orig : members.keySet()) {
      // Fill the "next" pointer.
      wraper = members.get(orig);
      if (wraper.index == length -1) {
        wraper.node.next = null;
      } else {
        wraper.node.next = wrapers.get(wraper.index + 1).node;
      }

      // Fill the "random" pointer.
      if (orig.random == null) {
        wraper.node.random = null;
      } else {
        wraper.node.random = members.get(orig.random).node;
      }
    }
    return wrapers.get(0).node;
  }
}
