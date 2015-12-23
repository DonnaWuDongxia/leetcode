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

public class Solution {
  public RandomListNode copyRandomList(RandomListNode head) {
    if (head == null) return null;

    Map<RandomListNode, RandomListNode> members = new HashMap<>();

    RandomListNode tmp = head;
    while(tmp != null) {
      members.put(tmp, new RandomListNode(tmp.label));
      tmp = tmp.next;
    }

    for (RandomListNode orig : members.keySet()) {
      // Fill the "next" pointer.
      tmp = members.get(orig);
      if (orig.next == null) {
        tmp.next = null;
      } else {
        tmp.next = members.get(orig.next);
      }

      // Fill the "random" pointer.
      if (orig.random == null) {
        tmp.random = null;
      } else {
        tmp.random = members.get(orig.random);
      }
    }
    return members.get(head);
  }
}
