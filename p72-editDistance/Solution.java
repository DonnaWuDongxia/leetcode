import java.util.Vector;
public class Solution {
  class DupChar {
    char value;
    int sIndex;
    int tIndex;
    public DupChar(char value, int sIndex, int tIndex) {
      this.value = value;
      this.sIndex = sIndex;
      this.tIndex = tIndex;
    }
  }

  class OrderLists {
    int bestLength;
    Vector<Vector<DupChar>> bestLists = new Vector<Vector<DupChar>>();
    Vector<Vector<DupChar>> secondLists = new Vector<Vector<DupChar>>();
  }

  public int minDistance(String word1, String word2) {
    Vector<DupChar> dups = new Vector<>();
    OrderLists lists = new OrderLists();

    lists.bestLength = 0;

    int tmp = -1;
    char c;
    for (int i = 0; i < word2.length(); i++) {
      c = word2.charAt(i);
      tmp = word1.indexOf(c);
      if (tmp >= 0) {
        dups.add(new DupChar(c, i, tmp));
      }
    }
    if (dups.size() < 0) return word2.length();

    DupChar checkNode, lastChar;
    Vector<DupChar> list;
    Vector<Vector<DupChar>> updatedSecondLists, updatedBestLists;
    int i, j;
    for (i = 0; i < dups.size(); i++) {
      checkNode = dups.get(i);
      if (i == 0) {
        list = new Vector<DupChar>();
        list.add(checkNode);
        lists.bestLists.add(list);
        lists.bestLength = 1;
        continue;
      }
      updatedSecondLists = new Vector<Vector<DupChar>>();
      for (j = 0; j < lists.secondLists.size(); j++) {
        list = lists.secondLists.get(j);
        lastChar = list.lastElement();
        if (lastChar.tIndex < checkNode.tIndex) {
          list = new Vector<DupChar>(list);
          list.add(checkNode);
          updatedSecondLists.add(list);
        }
      }
      updatedBestLists = new Vector<Vector<DupChar>>();
      for (j = 0; j < lists.bestLists.size(); j++) {
        list = lists.bestLists.get(j);
        lastChar = list.lastElement();
        if (lastChar.tIndex < checkNode.tIndex) {
          list = new Vector<DupChar>(list);
          list.add(checkNode);
          updatedBestLists.add(list);
        }
      }
      if (updatedBestLists.size() > 0) {
        lists.secondLists.clear();
        lists.secondLists.addAll(lists.bestLists);
        lists.secondLists.addAll(updatedSecondLists);

        lists.bestLists.clear();
        lists.bestLists = updatedBestLists;
        lists.bestLength++;
      } else {
        lists.bestLists.addAll(updatedSecondLists);
      }
    }

    int dLength = Math.abs(word1.length() - word2.length());

    return (word2.length() - lists.bestLength) + dLength;
  }
}
