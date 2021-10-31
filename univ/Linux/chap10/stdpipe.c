#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

#define MAXLINE 100

/* print Entered entered that execed command 
    in child through pipe */
int main(int argc, char* argv[])
{
    int n, pid, fd[2];
    char line[MAXLINE];

    pipe(fd); /* create pipe */

    if((pid=fork())==0) { // child process
	close(fd[0]);
	dup2(fd[1], 1);
	close(fd[1]);
	printf("Hello! pipe \n");
	printf("Bye! pipe \n");
    } else { // parent process
	close(fd[1]);
	printf("OUTPUT : in child process \n");
	while((n =read(fd[0], line, MAXLINE)) > 0)
	    write(STDOUT_FILENO, line, n);
    }
    exit(0);
}
