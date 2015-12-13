#include <iostream>
#include <string>
using namespace std;

class Solution {
  public:
    void revertString(string &s, int startIndex, int length) {
      char tmpChar;

      for (int i = 0; i < length/2; i++) {
        tmpChar = s[i + startIndex];
        s[i + startIndex] = s[length - i - 1 + startIndex];
        s[length - i - 1 + startIndex] = tmpChar;
      }
    }

    void reverseWords(string &s) {
      if (s[0] == ' ' && (s.length() == 1)) {
        s = "";
        return;
      }
      // Revert the whole string first.
      revertString(s, 0, s.length());

      int startIndex = 0;
      // Revert each word to the right order.
      for (int i = 0; i < s.length(); i++) {
        if (s[i] == ' ' || i == s.length()) {
          if ((startIndex == 0) && (i >= s.length())) {
            s = "";
            return;
          }

          if (i - startIndex > 1) revertString(s, startIndex, i - startIndex);

          while((s[i + 1] == ' ') && i < s.length()) i++;

          if ((startIndex == 0) &&(i == s.length() - 1)) {
            s = "";
            return;
          }

          startIndex = i + 1;
        }
      }
    }
};

int main() {
  Solution s = Solution();
  string str = string(" 1");
  s.reverseWords(str);
  cout << str << endl;
  return 0;
}

