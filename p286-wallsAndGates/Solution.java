public class Solution {
  public boolean needUpdate(int value, int step) {
    if (value < 0) return false;
    if (value <= step) return false;
    return true;
  }

  public void wallsAndGates(int[][] rooms) {
    for (int i = 0; i < rooms.length; i++) {
      for (int j = 0; j < rooms[i].length; j++) {
        if (rooms[i][j] == 0)
          spread(rooms, i, j);
      }
    }
  }

  public void spread(int[][] rooms, int i, int j) {
    int step = rooms[i][j];

    if (i > 0 && needUpdate(rooms[i - 1][j], step + 1)) {
      rooms[i - 1][j] = step + 1;
      spread(rooms, i - 1, j);
    }
    if (i < (rooms.length - 1) && needUpdate(rooms[i + 1][j], step)) {
      rooms[i + 1][j] = step + 1;
      spread(rooms, i + 1, j);
    }
    if (j > 0 && needUpdate(rooms[i][j - 1], step)) {
      rooms[i][j - 1] = step + 1;
      spread(rooms, i, j - 1);
    }
    if (j < (rooms[i].length - 1) && needUpdate(rooms[i][j + 1], step)) {
      rooms[i][j + 1] = step + 1;
      spread(rooms, i, j + 1);
    }
  }
}
