import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
public class LRUCache {
  Map<Integer, Integer> members = new HashMap<>();
  LinkedList<Integer> keys = new LinkedList<>();
  int mCapacity;

  public LRUCache(int capacity) {
    mCapacity = capacity;
  }

  public int get(int key) {
    if (!members.containsKey(key)) return -1;

    keys.remove(new Integer(key));
    keys.addLast(key);
    return members.get(key);
  }

  public void set(int key, int value) {
    if (members.containsKey(key)) {
      return;
    }

    if (keys.size() == mCapacity) {
      members.remove(keys.getFirst());
      keys.removeFirst();
    };
    members.put(key, value);
    keys.addLast(key);
  }
}
