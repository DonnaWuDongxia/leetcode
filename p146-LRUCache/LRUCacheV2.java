import java.util.Map;
import java.util.HashMap;
public class LRUCache {
  Map<Integer, Item> members; 
  int mCapacity;

  class Item {
    int key;
    int priority;
    int value;

    public Item(int key, int value) {
      this.key = key;
      this.value = value;
      this.priority = 0;
    }
  }

  public LRUCache(int capacity) {
    mCapacity = capacity;
    members = new HashMap<>();
  }

  public int get(int key) {
    int result = -1;
    if (members.containsKey(key)) {
      for (Item i : members.values()) {
        if (i.key == key) {
          i.priority = 0;
          result = i.value;
        } else {
          i.priority++;
        }
      }
    }
    return result;
  }

  public void set(int key, int value) {
    if (members.containsKey(key)) {
      return;
    }
    Item oldest = null;
    for (Item i : members.values()) {
      i.priority++;
      if (oldest == null) {
        oldest = i;
      } else if (oldest.priority < i.priority) {
        oldest = i;
      }
    }
    if (members.size() == mCapacity) {    
      members.remove(oldest.key);
    };
    members.put(key, new Item(key, value));
  }
}
