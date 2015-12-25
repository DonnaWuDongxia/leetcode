public int minDistance(String word1, String word2) {       
  // dp[i][j]:minimum steps to convert i long word1 to j long word2   
  int dp[][] = new int[word1.length()+1][word2.length()+1];   
  for (int i=0; i <= word1.length();i++) {
    dp[i][0]=i;
  }
  for (int j=0; j <= word2.length();j++) {
    dp[0][j]=j;
  }   
  for (int i=1;i <= word1.length();i++) {
    for (int j=1; j<= word2.length();j++) {
      if (word1.charAt(i-1) == word2.charAt(j-1)) {
        dp[i][j] = dp[i-1][j-1];
      } else {
        // dp[i-1][j-1]:replace word1(i) with word2(j), because word1(0, i-1) == word2(0, j-1);
        // dp[i][j-1]:insert into word(i) with word2(j), because word1(0, i) == word2(0, j-1);
        // dp[i-1][j]:delete word(i), because word1(0, i-1) == word2(0, j).
        dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j])) + 1; 
      }
    }
  }
  return dp[word1.length()][word2.length()];
}


public class Solution {
  public int minDistance(String word1, String word2) {
    int[] prev = new int[word2.length()+1];
    int[] curr = new int[word2.length()+1];
    for(int i= 1; i<= word2.length(); i++){
      prev[i] = i;
    }
    for(int i=1; i <= word1.length(); i++){
      curr[0] = i;
      for(int j=1; j <= word2.length(); j++){
        if(word1.charAt(i-1) == word2.charAt(j-1)){
          curr[j] = prev[j-1];
        }
        else{
          curr[j] = 1 + Math.min(prev[j-1],Math.min(curr[j-1],prev[j]));
        }
      }
      prev = Arrays.copyOf(curr,curr.length);
    }
    return prev[word2.length()];
  }
}
