#include <stdio.h>
#include <string.h>
#include <stdlib.h>
void reverseWords(char *s) {
  int originLen = strlen(s);
  char ** words = (char**)malloc(originLen + 1);
  int wordLen, i, j= 0;
  char *word = strtok(s, " ");
  while(word) {
	  printf("%s\n", word);
	  for (j = 0; j < strlen(word); j++) {
		  words[i][j] = word[j];
	  }
	  word = strtok(NULL, " ");
	  i++;
  }
  wordLen = i;
  s[0] = '\0';
  for (i = wordLen - 1; i >= 0; i--) {
	  if (i < (wordLen - 1)) strcat(s, " ");
	  strcat(s, words[i]);
  }
  free(words);
}
int main() {
  char str[] = "1sdg    bs    sdga    ";
  printf("%s\n", str);
  reverseWords(str);
  printf("%s\n", str);
  printf("%i\n", (int)(strlen(str)));
  return 0;
}
