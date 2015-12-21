public class LRUCache {
  PriorigyQueue<Item> members;
  List<int> keys;
  int mCapacity;

  class Item {
    int key;
    int freshIndex;
    int refCount;
    int value;
  }

  class ItemComparator implements Comparator<Item> {
    public int compare(Item item1, Item item2) {
      int temp = item2.freshIndex - item1.freshIndex;
      if (temp != 0) return temp;

      return (item1.refCount - item2.refCount);
    }
  }

  public LRUCache(int capacity) {
    mCapacity = capacity;
    members = new PriorigyQueue<>(capacity, new ItemComparator());
    keys = new ArrayList<int>(capacity);
  }
  public int get(int key) {
    for (Item i : members) {
      if (i.key == key) {
        i.refCount++;
        i.freshIndex == 0;
        return i.value;
      } else {
        i.freshIndex++;
      }
    }
    return -1;
  }
  public void set(int key, int value) {
    if (keys.contains(key)) {
      return;
    }
    if (members.size() == mCapacity) {
      Item tmp = members.poll();
      keys.remove(tmp.key);
    };
    members.add(new Item(key, value));
    keys.add(key);
  }
}
