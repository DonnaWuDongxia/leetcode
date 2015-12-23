import java.util.Map;
import java.util.HashMap;

class TrieNode {
  char value;
  boolean isWord = false;
  Map<Character, TrieNode> children = new HashMap<>();

  public TrieNode() {}
  public TrieNode(char value) {
    this.value = value;
  }
}

public class Trie {
  private TrieNode root;

  public Trie() {
    root = new TrieNode();
  }

  // Inserts a word into the trie.
  public void insert(String word) {
    char c;
    TrieNode tmp = root;
    int wLength = word.length();
    for (int i = 0; i < wLength; i++) {
      c = word.charAt(i);
      if (!tmp.children.containsKey(c)) {
        tmp.children.put(c, new TrieNode(c));
      }
      tmp = tmp.children.get(c);
    }
    tmp.isWord = true;
  }

  public TrieNode position(String word) {
    char c;
    TrieNode tmp = root;
    int wLength = word.length();
    for (int i = 0; i < wLength; i++) {
      c = word.charAt(i);
      if (!tmp.children.containsKey(c)) return null;

      tmp = tmp.children.get(c);
      if (i == wLength - 1) return tmp;
    }
    return null;
  }

  // Returns if the word is in the trie.
  public boolean search(String word) {
    TrieNode p = position(word);
    return (p != null) && p.isWord;
  }

  // Returns if there is any word in the trie
  // that starts with the given prefix.
  public boolean startsWith(String prefix) {
    return (position(prefix) != null);
  }
}

// Your Trie object will be instantiated and called as such:

// Trie trie = new Trie();

// trie.insert("somestring");

// trie.search("key");
