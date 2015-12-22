class TrieNode {
  TrieNode left;
  TrieNode right;
  TrieNode parent;
  String word;

  public TrieNode(String word) {
    this.word = word;
    this.parent = null;
    this.left = null;
    this.right = null;
  }

  /*
  public int getLevel() {
    int leftLevel = (left == null) ? 0 : left.getLevel();
    int rightLevel = (right == null) ? 0 : right.getLevel();

    return (leftLevel > rightLevel) ? (leftLevel + 1) : (right + 1);
  }
  */
}
public class Trie {
  private TrieNode root;

  // Inserts a word into the trie.
  public void insert(String word) {
    TrieNode newNode = new TrieNode(word);
    if (root == null) {
      root = newNode;
      return;
    }

    TrieNode parent = findParent(word, root);
    if (parent == null) throw new Error("Invalid target parent.");

    int comparedResult = word.compareTo(parent.word); 

    // Already exist.
    if (comparedResult == 0) return;

    newNode.parent = parent;
    if (comparedResult > 0) {
      if (parent.right != null) throw new Error("Exsiting right on parent.");

      parent.right = newNode;
    } else {
      if (parent.left != null) throw new Error("Exsiting left on parent.");

      parent.left = newNode;
    }
  }

  /*
   * Returns:
   *  null: the parent should be the root, no items in the tree.
   */
  public TrieNode findParent(String word, TrieNode tree) {
    if (tree == null) return null;

    TrieNode parent;
    int comparedResult;
    TrieNode tmp = tree;
    do {
      comparedResult = word.compareTo(tmp.word);
      if (comparedResult == 0) return tmp;

      parent = tmp;
      tmp = comparedResult > 0 ? parent.right : parent.left;
    } while(tmp != null);

    return parent;
  }

  // Returns if the word is in the trie.
  public boolean search(String word) {
    TrieNode p = findParent(word, root);
    if ((p != null) && word.equals(p.word)) return true;

    return false;
  }

  // Returns if there is any word in the trie
  // that starts with the given prefix.
  public boolean startsWith(String prefix) {
    if (root == null) return false;

    int comparedResult;
    TrieNode tmp = root;
    do {
      comparedResult = prefix.compareTo(tmp.word);
      if (comparedResult == 0) return true;

      if (comparedResult > 0) {
        tmp = tmp.right;
      } else {
        if (tmp.word.startsWith(prefix)) return true;

        tmp = tmp.left;
      }
    } while(tmp != null);

    return false;
  }
}

class Runner {
  public static void main(String[] args) {
    Trie trie = new Trie();

    trie.insert("ab");

    System.out.println(trie.search("a"));
    System.out.println(trie.startsWith("a"));
  }
}
