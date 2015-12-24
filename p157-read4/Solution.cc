// Forward declaration of the read4 API.

int read4(char *buf);

class Solution {
  public:
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */

    int read(char *buf, int n) {
      int i = n/4;
      int result = 0;
      int tmp, r;
      char b[4];
      while(i--) {
        tmp = read4(buf + result);
        result += tmp;
        if (tmp < 4) return result;
      }
      tmp = n - result;
      if (tmp > 0) {
        r = read4(b);
        tmp = r < tmp ? r : tmp;
        r = 0;
        while(r < tmp) {
          buf[result++] = b[r++];
        }
      }
      return result;
    }
};
