import java.util.LinkedList;
import java.util.Iterator;
public class LRUCache {
  LinkedList<Item> members;
  int mCapacity;

  class Item {
    int key;
    int value;

    public Item(int key, int value) {
      this.key = key;
      this.value = value;
    }
  }

  public LRUCache(int capacity) {
    mCapacity = capacity;
    members = new LinkedList<>();
  }

  public int get(int key) {
    Item found = null;
    Item tmp;
    Iterator<Item> itr = members.iterator();
    while(itr.hasNext()) {
      tmp = itr.next();
      if (tmp.key == key) {
        found = tmp;
        itr.remove();
      }
    }
    if (found != null) {
      members.addLast(found);
      return found.value;
    }
    return -1;
  }

  public void set(int key, int value) {
    for (Item i : members) {
      if (i.key == key) {
        System.out.println("Already exist key:" + key);
        return;
      }
    }

    if (members.size() == mCapacity) {
      members.pollFirst();
    };
    members.addLast(new Item(key, value));
  }
}
