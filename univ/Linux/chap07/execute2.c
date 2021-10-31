#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

/* fork three child process */
int main()
{
    printf("parent process start \n");

    if(fork() == 0){ // fork [1]
	execl("/bin/echo", "echo", "hello", NULL);
	fprintf(stderr, "first fail");
	exit(1);
    }

    if(fork() == 0) { // fork[2]
	execl("/bin/date", "date", NULL);
	fprintf(stderr, "second fail");
	exit(2);
    }

    if(fork() == 0) { // fork[3]
	execl("/bin/ls", "ls", "-l", NULL);
	fprintf(stderr, "third fail");
	exit(3);
    }
    printf("parent process end\n");
}
