public class Solution {
  public boolean needUpdate(int value, int step) {
    if (value < 0) return false;
    if (value <= step) return false;
    return true;
  }

  public void wallsAndGates(int[][] rooms) {
    int i, j;
    boolean dirty;
    int step = 1;
    do {
      dirty = false;
      for (i = 0; i < rooms.length; i++) {
        for (j = 0; j < rooms[i].length; j++) {
          if (rooms[i][j] == (step - 1)) {
            if (i > 0 && needUpdate(rooms[i - 1][j], step)) {
              rooms[i - 1][j] = step;
              if (!dirty) dirty = true;
            }
            if (i < (rooms.length - 1) && needUpdate(rooms[i + 1][j], step)) {
              rooms[i + 1][j] = step;
              if (!dirty) dirty = true;
            }
            if (j > 0 && needUpdate(rooms[i][j - 1], step)) {
              rooms[i][j - 1] = step;
              if (!dirty) dirty = true;
            }
            if (j < (rooms[i].length - 1) && needUpdate(rooms[i][j + 1], step)) {
              rooms[i][j + 1] = step;
              if (!dirty) dirty = true;
            }
          }
        }
      }
      step++;
    } while (!dirty);
  }
}
