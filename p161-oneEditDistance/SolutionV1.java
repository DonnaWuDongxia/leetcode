public class Solution {
  public boolean isOneEditDistance(String s, String t) {
    if (s.equals(t)) return false;

    if (Math.max(s.length(), t.length()) == 1) return true;

    int len = Math.min(s.length(), t.length());

    if (len == 1) {
      if (s.length() == 1) {
        return t.indexOf(s) >= 0;
      } else {
        return s.indexOf(t) >= 0;
      }
    }

    int unmatched = Math.abs(s.length() - len);
    if (unmatched > 1) return false;

    if (s.charAt(0) == t.charAt(0)) {
      return isOneEditDistance(s.substring(1), t.substring(1));
    } else if (s.substring(1).equals(t.substring(1))) {
      return true;
    } else if (s.charAt(0) == t.charAt(1)) {
      return s.equals(t.substring(1));
    } else {
      return t.equals(s.substring(1));
    }
  }
}
