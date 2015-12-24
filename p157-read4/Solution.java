/* The read4 API is defined in the parent class Reader4.
 * int read4(char[] buf);
 */

public class Solution extends Reader4 {

  /**
   * @param buf Destination buffer
   * @param n   Maximum number of characters to read
   * @return    The number of characters read
   */

  public int read(char[] buf, int n) {
    int result = 0;
    int mod = n % 4;
    int times = n / 4;
    int tmp, i;

    char[] subBuf = new char[4];
    if (n <= 0) return result;
    while(times > 0) {
      tmp = read4(subBuf);
      i = 0;
      while(i < tmp) {
        buf[result++] = subBuf[i++];
      }
      if (tmp < 4) return result;
      times--;
    }

    if (mod <= 0) return result;

    tmp = read4(subBuf);
    tmp = tmp > mod ? mod : tmp;
    i = 0;
    while(i < tmp) {
      buf[result++] = subBuf[i++];
    }
    return result;
  }
}
