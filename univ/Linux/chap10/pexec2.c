#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <stdlib.h>

#define MAXLINE 100
/* Receives and prints the output of commands executed 
in the child using the popen() function. */

int main(int argc, char* argv[])
{
    char line[MAXLINE];
    FILE *fpin;
    if((fpin = popen(argv[1], "r")) == NULL) {
	perror("ERROR : popen \n");
	return 1;
    }
    printf("Result received from child process\n");
    while(fgets(line, MAXLINE, fpin))
	fputs(line, stdout);
    pclose(fpin);
    return 0;
}
