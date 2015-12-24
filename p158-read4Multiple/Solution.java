/* The read4 API is defined in the parent class Reader4.
 * int read4(char[] buf);
 */

public class Solution extends Reader4 {
    char[] subBuf = new char[4];
    int remain = 0;
    int startIndex = 0;

  /**
   * @param buf Destination buffer
   * @param n   Maximum number of characters to read
   * @return    The number of characters read
   */

  public int read(char[] buf, int n) {
    if (n <= 0) return 0;

    int result = 0;
    int tmp, i;

    if (remain > 0) {
      tmp = n <= remain ? n : remain;
      while(tmp > 0) {
        buf[result++] = subBuf[startIndex++];
        remain--;
        tmp--;
      }
    }

    while(result < n) {
      tmp = read4(subBuf);
      if ((tmp + result) > n) {
        remain = tmp + result - n;
        tmp = tmp - remain;
        startIndex = tmp;
      }
      i = 0;
      while(i < tmp) {
        buf[result++] = subBuf[i++];
      }
      if (tmp < 4) return result;
    }
    return result;
  }
}
