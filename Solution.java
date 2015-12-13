public class Solution {
  public String reverseWords(String s) {
    s = s.trim();
    if (s.length() < 1 || s.indexOf(' ') < 0) return s;

    // Revert the whole string first.
    revertString(s, 0, s.length());

    int startIndex = 0;

    // Revert each word to the right order.
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == ' ') {
        revertString(startIndex, i - startIndex);
        while(s.charAt(i) == ' ') i++;
        startIndex = i + 1;
      }
    }
    return s;
  }

  public void revertString(String s, int startIndex, int length) {
    char tmpChar;

    for (int i = startIndex; i < length/2; i++) {
      tmpChar = s.charAt(i);
      s[i] = s[length - i - 1];
    }
  }

  public static void main() {
    Solution s = Solution();
    String str = "   hello sdgagd";
    s.reverseWords(str);
    system.out.println(str);
  }
}
