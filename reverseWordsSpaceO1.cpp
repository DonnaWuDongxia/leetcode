#include <iostream>
#include <string>
/*
 * Try to solve it in-place in O(1) space.
 * Idea:
 *    1. reverse the whole string first.
 *    2. reverse each word, and shrink the string if nessarily.
 *
 * Notice:
 *    Need to notice that we should adjust the current working index and
 *    wordLength if the whole string was shrinked.
 */
using namespace std;

class Solution {
  public:
    void revertString(string &s, int startIndex, int length) {
      if (length < 2) return;
      char tmpChar;

      for (int i = 0; i < length/2; i++) {
        tmpChar = s[i + startIndex];
        s[i + startIndex] = s[length - i - 1 + startIndex];
        s[length - i - 1 + startIndex] = tmpChar;
      }
    }

    void shrinkString(string &s, int startIndex, int length) {
      if (length < 1) return;
      int newLen = s.length() - length;
      for (int i = startIndex; i < newLen; i++) {
        s[i] = s[i + length];
      }
      s.resize(newLen);
      //s.shrink_to_fit();
    }

    void reverseWords(string &s) {
      // Revert the whole string first.
      revertString(s, 0, s.length());

      int wordStart = 0;
      int wordLength = 0;
      int spaceStart = 0;
      int spaceLength = 0;
      // Revert each word to the right order.
      for (int i = 0; i <= s.length(); i++) {
        if (i == s.length()) {
          if ((s[i - 1] == ' ') && spaceLength > 0) {
            int originalLen = s.length();
            s.resize(originalLen - spaceLength);
            //s.shrink_to_fit();
            spaceLength = 0;
          } else if (wordLength > 1) {
            revertString(s, wordStart, wordLength);
            wordLength = 0;
          }
        } else if (s[i] == ' ') {
          spaceLength++;
          if ((i == 0) || (s[i - 1] != ' ')) {
            spaceStart = i;
            revertString(s, wordStart, wordLength);
            wordLength = 0;
          }
        } else {
          wordLength++;
          if ((i == 0) || (s[i - 1] == ' ')) {
            wordStart = i;
            if ((spaceStart == 0) && (spaceLength > 0)) {
              shrinkString(s, spaceStart, spaceLength);
              i = i - spaceLength;
              wordStart = wordStart - spaceLength;
            } else if (spaceLength > 1) {
              shrinkString(s, spaceStart + 1, spaceLength - 1);
              i = i - spaceLength + 1;
              wordStart = wordStart - spaceLength + 1;
            }
            spaceLength = 0;
          }
        }
      }
    }
};

int main() {
  Solution s = Solution();
  string str = string("1    b    sdga    ");
  s.reverseWords(str);
  cout << str << endl;
  cout << str.length() << endl;
  return 0;
}

