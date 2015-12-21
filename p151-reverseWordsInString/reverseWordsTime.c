#include <stdio.h>
#include <string.h>
#include <stdlib.h>
void reverseWords(char *s) {
  int originLen = strlen(s);
  char *words = malloc(originLen);
  int currentLen = 0;
  int i, bIndex;
  int wLen = 0;

  char *word = strtok(s, " ");
  while(word) {
    wLen = strlen(word);
    if (currentLen > 0) {
      words[originLen - currentLen - 1] = ' ';
      currentLen++;
    }
    bIndex = originLen - currentLen - wLen;
    for (i = 0; i < wLen; i++) {
      words[bIndex + i] = word[i];
      currentLen++;
    }
    word = strtok(NULL, " ");
  }
  for(i = 0; i < currentLen; i++) {
    s[i] = words[originLen - currentLen + i];
  }
  if (currentLen < originLen) s[currentLen] = '\0';

  free(words);
}

int main() {
  char str[] = "  aa  sdga  ";
  printf("%s\n", str);
  reverseWords(str);
  printf("%s\n", str);
  printf("%i\n", (int)(strlen(str)));
  return 0;
}
